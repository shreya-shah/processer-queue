package com.example.pipeline.indexer.processor;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.IProcessor;
import com.example.pipeline.processor.ITask;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IndexerProcessorImpl implements IProcessor {
  @Autowired
  @Qualifier("indexerTask1")
  ITask task1;

  @Autowired
  @Qualifier("indexerTask2")
  ITask task2;

  public String processQueueMessage(DocumentContent documentContent) {
    System.out.println("Processing message in indexer: " + documentContent.getStatus());
    DocumentContent content = task2.performTask(task1.performTask(documentContent));
    System.out.println("~~~~~~~~~~~~  FINAL OUTPUT OF PROCESSING ~~~~~~~~~~~ \n\n" + content.getStatus());
    return content.getStatus();
  }

  @Override
  @RabbitListener(queues = "${indexer.queue.name}")
  public void receiveMessage(DocumentContent documentContent) {
    System.out.println("Message received in indexer is: " + documentContent.getStatus());
    this.processQueueMessage(documentContent);
  }
}