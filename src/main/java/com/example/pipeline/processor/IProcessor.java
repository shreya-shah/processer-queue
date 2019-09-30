package com.example.pipeline.processor;

import com.example.pipeline.model.DocumentContent;

public interface IProcessor {
  String processQueueMessage(DocumentContent documentContent);

  void receiveMessage(DocumentContent documentContent);
}