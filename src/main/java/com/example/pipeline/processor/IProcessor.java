package com.example.pipeline.processor;

import com.example.pipeline.model.DocumentContent;
import org.json.simple.JSONObject;

public interface IProcessor {
  String processQueueMessage(DocumentContent documentContent);

  void receiveMessage(DocumentContent documentContent);
}