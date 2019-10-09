package com.example.pipeline.indexer.tasks;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.ITask;
import org.springframework.stereotype.Component;

@Component
public class IndexerTask2 implements IIndexerTask {
    @Override
    public DocumentContent performTask(DocumentContent documentContent) {
        String output = documentContent.getStatus() + " some action by task2 of indexer\n";
        documentContent.setStatus(output);
        return documentContent;
    }
}
