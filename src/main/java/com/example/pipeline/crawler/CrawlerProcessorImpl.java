package com.example.pipeline.crawler;

import com.example.pipeline.crawler.processor.ICrawlerProcessor;
import com.example.pipeline.crawler.producer.ICrawlerProducer;
import com.example.pipeline.crawler.tasks.ICrawlerTask;
import com.example.pipeline.model.DocumentContent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class CrawlerProcessorImpl implements ICrawlerProcessor {

  @Autowired
  @Qualifier("crawlerTask1")
  ICrawlerTask task1;

  @Autowired
  @Qualifier("crawlerTask2")
  ICrawlerTask task2;

  @Autowired
  @Qualifier("crawlerProducer")
  ICrawlerProducer crawlerProducer;

  public String processQueueMessage(DocumentContent documentContent){
    System.out.println("Processing message in crawler: " + documentContent.getStatus());
    System.out.println("task 1...." + task1);
    System.out.println("task 2...." + task2);
    DocumentContent content  = task2.performTask(task1.performTask(documentContent));
    this.crawlerProducer.produceMessage(content);
    return content.getStatus();
  }

  @Override
  @RabbitListener(queues = "${crawler.queue.name}")
  public void receiveMessage(DocumentContent documentContent) {
    System.out.println("Message received in indexer is: " + documentContent.getStatus());
    this.processQueueMessage(documentContent);
  }
}