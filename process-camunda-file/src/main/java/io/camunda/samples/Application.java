package io.camunda.samples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.dto.runtime.modification.StartAfterInstructionDto;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.hibernate.validator.constraints.Mod11Check.ProcessingDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.opencsv.bean.CsvToBeanBuilder;

import io.camunda.samples.domain.Product;

@EnableProcessApplication
@SpringBootApplication
public class Application {

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private RuntimeService runtimeService;

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@EventListener
	private void processPostDeploy(PostDeployEvent event) throws IllegalStateException, FileNotFoundException {
		runProcess();

	}

	public void runProcess() throws IllegalStateException, FileNotFoundException {
		List<Product> beans = new CsvToBeanBuilder<Product>(
				new FileReader("./products10k.csv")).withType(Product.class).build().parse();
		log.info("Start File Processing camunda ");
		beans.forEach(product -> {
			CompletableFuture.runAsync(() -> {
				Map<String, Object> variables = new HashMap<>();
				variables.put("product", product);
				runtimeService.createProcessInstanceByKey("ProcessFile").setVariables(variables)
						.executeWithVariablesInReturn();
			});
		});
	}
}