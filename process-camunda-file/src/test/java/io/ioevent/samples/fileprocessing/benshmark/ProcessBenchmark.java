package io.ioevent.samples.fileprocessing.benshmark;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.RuntimeService;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.opencsv.bean.CsvToBeanBuilder;

import io.camunda.samples.domain.Product;



@SpringBootTest
//@State(Scope.Benchmark)
//@BenchmarkMode(Mode.Throughput)
//@OutputTimeUnit(TimeUnit.MICROSECONDS)
@RunWith(SpringRunner.class)
public class ProcessBenchmark extends AbstractBenchmark {
	private static RuntimeService runtimeService;
	@Autowired 
	public void setFileProcessService(RuntimeService runtimeService) {
		ProcessBenchmark.runtimeService = runtimeService;
	    }
	
	
	private static final Logger log = LoggerFactory.getLogger(ProcessBenchmark.class);

/*	@Benchmark
	public void someBenchmarkMethod(Blackhole blackhole) throws IllegalStateException, FileNotFoundException {
		log.info("Benchmark start");
		List<Product> beans = new CsvToBeanBuilder<Product>(
				new FileReader("src/main/resources/assests/csv/products.csv")).withType(Product.class).build().parse();
		beans.forEach(product -> {
			new Thread(() -> {
				Map<String, Object> variables = new HashMap<>();
				variables.put("product", product);
				// runtimeService.setVariable("valid", "valid", "valid");
				runtimeService.createProcessInstanceByKey("ProcessFile").setVariables(variables)
						.executeWithVariablesInReturn();
			}).start();
		});
	}*/
	}

