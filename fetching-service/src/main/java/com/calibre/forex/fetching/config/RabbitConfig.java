package com.calibre.forex.fetching.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String FOREX_RATE_QUEUE = "forex.rate.quee";
    public static final String FOREX_RATE_EXCHANGE = "forex.rate.exchange";
    public static final String FOREX_RATE_ROUTING_KEY = "forex.rate.changed";

    @Bean
    public Queue forexRateQueue(){
        return new Queue(FOREX_RATE_QUEUE);
    }

    @Bean
    public TopicExchange forexExchange(){
        return new TopicExchange(FOREX_RATE_EXCHANGE);
    }

    @Bean
    public Binding bindingForexRateQuee(Queue forexRateQueue, TopicExchange forexExchange){
        return BindingBuilder.bind(forexRateQueue).to(forexExchange).with(FOREX_RATE_ROUTING_KEY);
    }



}
