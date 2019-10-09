package com.example.pipeline.crawler.tasks;

import com.example.pipeline.model.DocumentContent;
import org.springframework.stereotype.Component;

@Component
public interface ICrawlerTask {
   DocumentContent performTask(DocumentContent documentContent);
}

