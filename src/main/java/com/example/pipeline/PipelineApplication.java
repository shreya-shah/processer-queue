package com.example.pipeline;

import com.example.pipeline.crawler.processor.CrawlerProcessorImpl;
import com.example.pipeline.libs.RabbitMqConfig;
import com.example.pipeline.libs.sender.Sender;

import com.example.pipeline.model.DocumentContent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PipelineApplication {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(PipelineApplication.class);
		CrawlerProcessorImpl crawlerProcessor = context.getBean(CrawlerProcessorImpl.class);
		DocumentContent documentContent = context.getBean(DocumentContent.class);

		documentContent.setDocumentUrl("https://medium.com");
		documentContent.setStatus("to crawler..\n");

		crawlerProcessor.processQueueMessage(documentContent);
	}

}
