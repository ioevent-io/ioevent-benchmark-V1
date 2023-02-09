package io.camunda.samples.service;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.samples.domain.Product;
import io.camunda.samples.enums.ProductState;

public class CheckValidationService implements JavaDelegate {

	private static final Logger log = LoggerFactory.getLogger(CheckValidationService.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Product product = (Product) execution.getVariable("product");
		if (productIsValid(product)) {
			log.info("product {} is valid", product.getId());
			product.setState(ProductState.ACCEPTED);

		} else {
			log.info("product {} is invalid", product.getId());
			product.setState(ProductState.REJECTED);
		}
		log.info(product.toString());
		execution.setVariable("product", product);

	}

	private boolean productIsValid(Product product) {
		return !(StringUtils.isBlank(product.getColor()) || StringUtils.isBlank(product.getName())
				|| StringUtils.isBlank(product.getManufacture()) || StringUtils.isBlank(product.getQuantity()));
	}
}
