package edu.miu.shoppingcartsystem.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

/*
    public static final String QUEUE = "bd71_queue";
    public static final String EXCHANGE = "bd71_exchange";
    public static final String ROUTING = "bd71_routing_key";
*/

    @Value("${rabbitmq.queuename}")
    private String rabbitQueue;

    @Value("${rabbitmq.exchangename}")
    private String rabbitExchange;

    @Value("${rabbitmq.routingname}")
    private String rabbitRouting;


    @Bean
    public Queue queue(){
        return new Queue(rabbitQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(rabbitExchange);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(rabbitRouting);

    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory factory(){
        return new CachingConnectionFactory();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
