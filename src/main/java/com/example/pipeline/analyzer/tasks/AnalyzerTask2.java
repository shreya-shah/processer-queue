package com.example.pipeline.analyzer.tasks;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.ITask;
import org.springframework.stereotype.Component;

@Component
public class AnalyzerTask2 implements IAnalyzerTask {
    @Override
    public DocumentContent performTask(DocumentContent documentContent) {
        String output = documentContent.getStatus() + " some action by task2 of analyzer \n";
        documentContent.setStatus(output);
        return documentContent;
    }
}
