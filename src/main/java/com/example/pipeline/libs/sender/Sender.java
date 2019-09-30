package com.example.pipeline.libs.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class Sender {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @Value("${direct.exchange.name}")
  private String exchange;

  public void sendMessage(String routingKey, String message) {
    System.out.println("Sending messsage" + message + "to: " + routingKey);
    this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
  }
}