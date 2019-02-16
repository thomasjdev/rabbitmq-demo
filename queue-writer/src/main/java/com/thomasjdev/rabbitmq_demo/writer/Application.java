package com.thomasjdev.rabbitmq_demo.writer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private final RabbitTemplate rabbitTemplate;
    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public Application(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String args[]) {
        SpringApplication.run(Application.class).close();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            log.info("Ready to read");
            rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");

        };
    }
}
