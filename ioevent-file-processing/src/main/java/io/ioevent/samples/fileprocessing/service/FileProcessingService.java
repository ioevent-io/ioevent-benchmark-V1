package io.ioevent.samples.fileprocessing.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.ioevent.starter.annotations.EndEvent;
import com.ioevent.starter.annotations.GatewayOutputEvent;
import com.ioevent.starter.annotations.IOEvent;
import com.ioevent.starter.annotations.IOFlow;
import com.ioevent.starter.annotations.IOResponse;
import com.ioevent.starter.annotations.InputEvent;
import com.ioevent.starter.annotations.OutputEvent;

import io.ioevent.samples.fileprocessing.domain.Product;
import io.ioevent.samples.fileprocessing.enums.ProductState;

@Service
@IOFlow(name = "File Processing")
public class FileProcessingService {

	private static final Logger log = LoggerFactory.getLogger(FileProcessingService.class);

	@Autowired
	ResourceLoader resourceLoader;

	@IOEvent(key = "capture product", topic = "file-processing-topic", //
			output = @OutputEvent(key = "product captured"))
	public Product captureProduct(Product product) {
		product.setState(ProductState.CREATED);
		return product;
	}

	@IOEvent(key = "check product validation", topic = "file-processing-topic", //
			input = @InputEvent(key = "product captured"), //
			gatewayOutput = @GatewayOutputEvent(exclusive = true, output = { @OutputEvent(key = "valid product"),
					@OutputEvent(key = "invalid product") }))
	public IOResponse<Product> checkValidation(Product product) {
		if (productIsValid(product)) {
			log.info("product {} is valid", product.getId());
			product.setState(ProductState.ACCEPTED);
			return new IOResponse<>("valid product", product);
		}
		log.info("product {} is invalid", product.getId());
		product.setState(ProductState.REJECTED);

		return new IOResponse<>("invalid product", product);
	}

	private boolean productIsValid(Product product) {
		return !(StringUtils.isBlank(product.getColor()) || StringUtils.isBlank(product.getName())
				|| StringUtils.isBlank(product.getManufacture()) || StringUtils.isBlank(product.getQuantity()));
	}

	@IOEvent(key = "save product", topic = "file-processing-topic", //
			input = @InputEvent(key = "valid product"))
	public Product saveProduct(Product product) {
		log.info("product saved: {}", product.getId());
		product.setState(ProductState.CLOSED);
		return product;
	}

	@IOEvent(key = "cancel product", topic = "file-processing-topic", //
			input = @InputEvent(key = "invalid product"), //
			output = @OutputEvent(key = "product canceled"))
	public Product cancelProduct(Product product) {
		log.info("sending invalid product {} to invalid topic", product.getId());
		product.setState(ProductState.CANCELED);
		return product;
	}

	@IOEvent(key = "reject end", topic = "rejected-file-processing-topic", //
			input = @InputEvent(key = "product canceled", topic = "file-processing-topic"), //
			endEvent = @EndEvent(key = "File Processing"))
	public Product rejectProduct(Product product) {
		log.info("sending invalid product {} to invalid topic", product.getId());
		product.setState(ProductState.ERROR);
		return product;
	}

}
