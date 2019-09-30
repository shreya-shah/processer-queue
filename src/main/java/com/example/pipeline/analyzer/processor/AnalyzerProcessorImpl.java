package com.example.pipeline.analyzer.processor;


import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.IProcessor;
import com.example.pipeline.processor.IProducer;
import com.example.pipeline.processor.ITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnalyzerProcessorImpl implements IProcessor {

    @Autowired
    @Qualifier("analyzerTask1")
    ITask task1;

    @Autowired
    @Qualifier("analyzerTask2")
    ITask task2;

    @Autowired
    @Qualifier("analyzerProducer")
    IProducer analyzerProducer;

    public String processQueueMessage(DocumentContent documentContent) {
        System.out.println("Processing message in analyzer: " + documentContent);
        DocumentContent content  = task2.performTask(task1.performTask(documentContent));
        this.analyzerProducer.produceMessage(content);
        return content.getStatus();
    }

    @Override
    @RabbitListener(queues = "${analyzer.queue.name}")
    public void receiveMessage(DocumentContent documentContent) {
        System.out.println("Message received in analyzer is: " + documentContent.getStatus());
        this.processQueueMessage(documentContent);
    }
}