package com.example.pipeline.crawler.tasks;

import com.example.pipeline.model.DocumentContent;
import com.example.pipeline.processor.ITask;
import org.springframework.stereotype.Component;

@Component
public class CrawlerTask1 implements ICrawlerTask {
    public DocumentContent performTask(DocumentContent documentContent) {
        String output = documentContent.getStatus() + " some action by task1 of crawler \n";
        documentContent.setStatus(output);
        return documentContent;
    }
}
