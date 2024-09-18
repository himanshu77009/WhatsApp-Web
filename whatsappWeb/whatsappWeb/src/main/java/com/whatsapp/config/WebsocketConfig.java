package com.whatsapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig extends WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/group","/user");
        registry.setUserDestinationPrefix("/user");
    }


}
