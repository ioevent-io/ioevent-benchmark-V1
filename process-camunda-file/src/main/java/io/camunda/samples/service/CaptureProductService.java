package io.camunda.samples.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.samples.domain.Product;
import io.camunda.samples.enums.ProductState;

public class CaptureProductService implements JavaDelegate {

	private static final Logger log = LoggerFactory.getLogger(CaptureProductService.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("CaptureProduct task");
		Product product = (Product) execution.getVariable("product");
		product.setState(ProductState.CREATED);
		execution.setVariable("product", product);
	}

}
