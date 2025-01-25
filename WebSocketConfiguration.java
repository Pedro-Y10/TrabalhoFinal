package br.edu.iffarroupilha.sigachat.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;
import br.edu.iffarroupilha.sigachat.modelos.repositorios.UsuarioRepositorio;
import br.edu.iffarroupilha.sigachat.services.JWTService;

/**
 * <p>
 * Classe que define as configurações do servidor websocket
 * </p>
 * 
 * @author Professor
 * @since Jan 10, 2025 8:08:32 PM
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private JWTService jwtService;
	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
		
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		 registry.addEndpoint("/ws")
         .setAllowedOriginPatterns("*") // Ou especificar padrões específicos
         .setAllowedOrigins("http://localhost:8000") // Especifique origens se precisar permitir credenciais
         .withSockJS();
		
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				System.out.println("Headers: {}" + accessor);

				assert accessor != null;
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {

					String authorizationHeader = accessor.getFirstNativeHeader("Authorization");
					String token = authorizationHeader.substring(7);
					if(token != null) {
						String email = jwtService.decriptaToken(token);
						Usuario usu = repositorio.findByEmail(email);		
								
						var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								usu, null, usu.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						
						accessor.setUser(usernamePasswordAuthenticationToken);
					}

					
				}

				return message;
			}

		});
	}
}
