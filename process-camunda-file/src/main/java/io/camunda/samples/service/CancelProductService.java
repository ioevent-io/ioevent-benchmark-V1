package io.camunda.samples.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.camunda.samples.domain.Product;
import io.camunda.samples.enums.ProductState;

@Service
public class CancelProductService  implements JavaDelegate {

	private static final Logger log = LoggerFactory.getLogger(CancelProductService.class);

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Product product = (Product) execution.getVariable("product");
		log.info("cancel product : {}", product.getId());
		product.setState(ProductState.CANCELED);
		execution.setVariable("product", product);
	}

}