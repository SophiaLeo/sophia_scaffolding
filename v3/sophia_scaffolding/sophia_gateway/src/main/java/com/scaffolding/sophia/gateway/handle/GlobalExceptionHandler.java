package com.scaffolding.sophia.gateway.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 ResponseStatusExceptionHandler 执行
 * @author LHL
 */
@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
	private final ObjectMapper objectMapper;

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ServerHttpResponse response = exchange.getResponse();

		if (response.isCommitted()) {
			return Mono.error(ex);
		}

		// header set
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		if (ex instanceof ResponseStatusException) {
			response.setStatusCode(((ResponseStatusException) ex).getStatus());
		}

		return response
			.writeWith(Mono.fromSupplier(() -> {
				DataBufferFactory bufferFactory = response.bufferFactory();
				try {
					Map<String,Object> map = new HashMap<>();
					map.put("code",500);
					map.put("msg",ex.getMessage());
					return bufferFactory.wrap(objectMapper.writeValueAsBytes(map));
				} catch (JsonProcessingException e) {
					log.error("Error writing response", ex);
					return bufferFactory.wrap(new byte[0]);
				}
			}));
	}

}
