package com.example.pipeline.analyzer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// import org.springframework.amqp.core.Binding;
// import org.springframework.amqp.core.BindingBuilder;
// import org.springframework.amqp.core.Queue;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitMqConfig {

   @Value("${crawler.queue.name}")
   private String crawlerQueue;

  @Value("${analyzer.queue.name}")
  private String ANALYZER_QUEUE;

  @Value("${indexer.queue.name}")
  private String INDEXER_QUEUE;

  @Value("${indexer.routing.key}")
  private String INDEXER_ROUTING_KEY;

  @Value("${direct.exchange.name}")
  private String EXCHANGE_NAME;

  @Value("${analyzer.routing.key}")
  private String ANALYZER_ROUTING_KEY;

  @Value("${crawler.routing.key}")
  private String CRAWLER_ROUTING_KEY;

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(EXCHANGE_NAME);
  }

   @Bean
   public Binding binding1(DirectExchange exchange, Queue queue1) {
   return BindingBuilder.bind(queue1).to(exchange).with(CRAWLER_ROUTING_KEY);
   }

  @Bean
  public Binding binding2(DirectExchange exchange, Queue queue2) {
    return BindingBuilder.bind(queue2).to(exchange).with(ANALYZER_ROUTING_KEY);
  }

  @Bean
  public Binding binding3(DirectExchange exchange, Queue queue3) {
    return BindingBuilder.bind(queue3).to(exchange).with(INDEXER_ROUTING_KEY);
  }

   @Bean
   public Queue queue1() {
   return new Queue(crawlerQueue);
   }

  @Bean
  public Queue queue2() {
    return new Queue(ANALYZER_QUEUE);
  }

  @Bean
  public Queue queue3() {
    return new Queue(INDEXER_QUEUE);
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}