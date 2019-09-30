package com.example.pipeline.processor;

import com.example.pipeline.model.DocumentContent;
import org.springframework.stereotype.Component;

@Component
public interface IProducer {
    void produceMessage(DocumentContent content);
}
