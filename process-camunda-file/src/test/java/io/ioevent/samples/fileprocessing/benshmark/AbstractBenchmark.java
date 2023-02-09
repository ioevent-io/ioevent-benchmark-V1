package io.ioevent.samples.fileprocessing.benshmark;



abstract public class AbstractBenchmark {

	private final static Integer MEASUREMENT_ITERATIONS = 1;
	private final static Integer WARMUP_ITERATIONS = 1;

	@org.junit.jupiter.api.Test
	public void executeJmhRunner()  {
		/*
		 * Options opt = new OptionsBuilder() // set the class name regex for benchmarks
		 * to search for to the current class .include(this.getClass().getSimpleName())
		 * .warmupIterations(WARMUP_ITERATIONS)
		 * .measurementIterations(MEASUREMENT_ITERATIONS) // do not use forking or the
		 * benchmark methods will not see references stored within its class .forks(0)
		 * .result("C:/Users/Ahmed Matt/Desktop/jmhh") // do not use multiple threads
		 * .threads(1) .shouldDoGC(true) .shouldFailOnError(true)
		 * .resultFormat(ResultFormatType.JSON) .shouldFailOnError(true)
		 * .jvmArgs("-server") .build();
		 */
		/*Options opt = new OptionsBuilder()//
				.include(this.getClass().getSimpleName()).warmupIterations(WARMUP_ITERATIONS)
				.measurementIterations(MEASUREMENT_ITERATIONS)
				// do not use forking or the benchmark methods will not see references stored
				// within its class
				.forks(0)
				// do not use multiple threads
				.threads(1).shouldDoGC(true).shouldFailOnError(true).resultFormat(ResultFormatType.JSON)
				.result("output/" + this.getClass().getSimpleName() + ".json").shouldFailOnError(true)
				.jvmArgs("-server").build();
		new Runner(opt).run();*/
	}
}