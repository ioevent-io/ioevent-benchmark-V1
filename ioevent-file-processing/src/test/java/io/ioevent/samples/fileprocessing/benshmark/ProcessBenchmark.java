package io.ioevent.samples.fileprocessing.benshmark;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.opencsv.bean.CsvToBeanBuilder;

import io.ioevent.samples.fileprocessing.domain.Product;
import io.ioevent.samples.fileprocessing.service.FileProcessingService;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@RunWith(SpringRunner.class)
public class ProcessBenchmark extends AbstractBenchmark{//extends AbstractBenchmark {
	private static FileProcessingService fileProcessingService;
	
	@Autowired 
	public void setFileProcessService(FileProcessingService fileProcessingService) {
		ProcessBenchmark.fileProcessingService = fileProcessingService;
	    }
	
	
	private static final Logger log = LoggerFactory.getLogger(ProcessBenchmark.class);

	@Benchmark
	public void ioeventBenchmarkMethod(Blackhole blackhole) throws IllegalStateException, FileNotFoundException {
		log.info("Benchmark start");

		List<Product> beans = new CsvToBeanBuilder<Product>(new FileReader("src/main/resources/assests/csv/products.csv"))
				.withType(Product.class).build().parse();

		beans.forEach(product -> {
			new Thread(() -> {
				fileProcessingService.captureProduct(product);
			}).start();
		});
	}
	}

