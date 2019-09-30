package com.example.pipeline.crawler.processor;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.IProcessor;
import com.example.pipeline.processor.IProducer;
import com.example.pipeline.processor.ITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CrawlerProcessorImpl implements IProcessor {

  @Autowired
  @Qualifier("crawlerTask1")
  ITask task1;

  @Autowired
  @Qualifier("crawlerTask2")
  ITask task2;

  @Autowired
  @Qualifier("crawlerProducer")
  IProducer crawlerProducer;

  public String processQueueMessage(DocumentContent documentContent){
    System.out.println("Processing message in crawler: " + documentContent.getStatus());
    DocumentContent content  = task2.performTask(task1.performTask(documentContent));
    this.crawlerProducer.produceMessage(content);
    return content.getStatus();
  }

  @Override
  public void receiveMessage(DocumentContent documentContent) {
  }
}