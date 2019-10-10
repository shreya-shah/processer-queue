package com.example.pipeline.analyzer;


import com.example.pipeline.analyzer.processor.IAnalyzerProcessor;
import com.example.pipeline.analyzer.producer.IAnalyzerProducer;
import com.example.pipeline.analyzer.tasks.IAnalyzerTask;
import com.example.pipeline.model.DocumentContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.example.pipeline.analyzer")
public class AnalyzerProcessorImpl implements IAnalyzerProcessor {

    @Autowired
    @Qualifier("analyzerTask1")
    IAnalyzerTask task1;

    @Autowired
    @Qualifier("analyzerTask2")
    IAnalyzerTask task2;

    @Autowired
    @Qualifier("analyzerProducer")
    IAnalyzerProducer analyzerProducer;

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