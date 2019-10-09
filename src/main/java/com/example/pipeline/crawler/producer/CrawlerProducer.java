package com.example.pipeline.crawler.producer;

import com.example.pipeline.model.DocumentContent;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrawlerProducer implements ICrawlerProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${analyzer.routing.key}")
    private String analyzerRoutingKey;

    @Autowired
    DirectExchange exchange;

    @Override
    public void produceMessage(DocumentContent documentContent) {
        System.out.println("in crawler producer..." + analyzerRoutingKey);
        this.rabbitTemplate.convertAndSend(exchange.getName(), analyzerRoutingKey, documentContent);
    }

}
