package com.fiap.orderhub.adapters.infrastructure.config;

import com.fiap.orderhub.infrastructure.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RabbitMQConfigTest {
    private final RabbitMQConfig config = new RabbitMQConfig();

    @Test
    void deveRetornarJackson2JsonMessageConverter() {
        MessageConverter converter = config.jsonMessageConverter();
        assertNotNull(converter);
        assertInstanceOf(Jackson2JsonMessageConverter.class, converter);
    }

    @Test
    void deveConfigurarRabbitTemplateComMessageConverterCorreto() {
        ConnectionFactory mockConnectionFactory = mock(ConnectionFactory.class);
        MessageConverter converter = config.jsonMessageConverter(); // ou criar um mock se quiser

        RabbitTemplate rabbitTemplate = config.rabbitTemplate(mockConnectionFactory, converter);

        assertNotNull(rabbitTemplate);
        assertEquals(converter, rabbitTemplate.getMessageConverter());
        assertSame(mockConnectionFactory, rabbitTemplate.getConnectionFactory());
    }
}
