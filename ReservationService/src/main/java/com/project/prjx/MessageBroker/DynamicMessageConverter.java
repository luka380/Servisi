package com.project.prjx.MessageBroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.lang.NonNull;

public class DynamicMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @NonNull
    public Object fromMessage(@NonNull Message message) {
        if (message.getBody() == null) {
            throw new MessageConversionException("Message body is null");
        }

        try {
            return objectMapper.readValue(message.getBody(), NotificationData.class);
        } catch (Exception e) {
            throw new MessageConversionException("Failed to convert message payload to MessageWrapper", e);
        }
    }

    @Override
    @NonNull
    public Message toMessage(@NonNull Object object, @NonNull MessageProperties messageProperties) {
        try {
            byte[] body = objectMapper.writeValueAsBytes(object);
            return new Message(body, messageProperties);
        } catch (Exception e) {
            throw new MessageConversionException("Failed to convert object to message", e);
        }
    }
}
