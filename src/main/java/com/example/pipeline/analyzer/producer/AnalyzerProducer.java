package com.example.pipeline.analyzer.producer;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.IProducer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AnalyzerProducer implements IAnalyzerProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${indexer.routing.key}")
    private String indexerRoutingKey;

    @Autowired
    DirectExchange exchange;

    @Override
    public void produceMessage(DocumentContent documentContent) {
        this.rabbitTemplate.convertAndSend(exchange.getName(),indexerRoutingKey, documentContent);
    }
}
