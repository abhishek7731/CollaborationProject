package com.niit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;



@Configuration
@EnableWebSocketMessageBroker  // enable broker based stomp messaging
@ComponentScan(basePackages="com.niit")
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer
{

	

	@Override
	public void configureMessageBroker(MessageBrokerRegistry configurer) {
		System.out.println("CONFIGURE MESSAGE BROKER REGISTRY");
		configurer.enableSimpleBroker("/topic/", "/queue/"); //here topic is the username
		configurer.setApplicationDestinationPrefixes("/app");
		
	}

	

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("REGISTER STOMP ENDPOINTS...");
		registry.addEndpoint("/chatmodule").withSockJS();
		
	}


}