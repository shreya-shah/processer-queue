package com.example.pipeline;

import com.example.pipeline.analyzer.AnalyzerProcessorImpl;
import com.example.pipeline.crawler.CrawlerProcessorImpl;
import com.example.pipeline.indexer.IndexerProcessorImpl;

import com.example.pipeline.model.DocumentContent;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class PipelineApplication {
	public static void main(String[] args) {
		System.out.println("args..." + args[0]);
		if(args[0].equals("crawler")) {
			SpringApplication.run(CrawlerProcessorImpl.class, args);
		} else if (args[0].equals("analyzer")) {
			SpringApplication.run(AnalyzerProcessorImpl.class, args);
		} else if (args[0].equals("indexer")) {
			SpringApplication.run(IndexerProcessorImpl.class, args);
		}
	}

}
