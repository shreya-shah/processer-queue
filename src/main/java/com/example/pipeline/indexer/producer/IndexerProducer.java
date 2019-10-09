package com.example.pipeline.indexer.producer;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.IProducer;
import org.springframework.stereotype.Component;

@Component
public class IndexerProducer implements IIndexerProducer {
    @Override
    public void produceMessage(DocumentContent documentContent) {

    }
}
