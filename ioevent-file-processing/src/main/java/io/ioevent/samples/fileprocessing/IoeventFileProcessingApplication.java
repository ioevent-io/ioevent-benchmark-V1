package io.ioevent.samples.fileprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.ioevent.starter.annotations.EnableIOEvent;
import com.opencsv.bean.CsvToBeanBuilder;

import io.ioevent.samples.fileprocessing.domain.Product;
import io.ioevent.samples.fileprocessing.service.FileProcessingService;

@EnableIOEvent
@SpringBootApplication
public class IoeventFileProcessingApplication {

private static final Logger log = LoggerFactory.getLogger(IoeventFileProcessingApplication.class);

	@Autowired
	FileProcessingService fileProcessingService;

	public static void main(String[] args) {
		SpringApplication.run(IoeventFileProcessingApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() throws IllegalStateException, FileNotFoundException {
		log.info("start process");

		List<Product> beans = new CsvToBeanBuilder<Product>(new FileReader("./products10k.csv"))
				.withType(Product.class).build().parse();

		beans.forEach(product -> 
			CompletableFuture.runAsync(() -> 
				fileProcessingService.captureProduct(product))
		);
	}

}




