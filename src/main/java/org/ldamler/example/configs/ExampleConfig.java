package org.ldamler.example.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ldamler.example.clients.DummyClient;
import org.ldamler.example.configs.properties.ExampleProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@EnableConfigurationProperties({ExampleProperties.class})
@Configuration
public class ExampleConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    @ConditionalOnExpression("'${example.dummy.route}' != 'null'")
    public DummyClient dummyClient(ExampleProperties props,
                                   RequestInterceptor requestInterceptor) {
        var dummyProps = props.getDummy();

        return createClient(DummyClient.class, dummyProps, requestInterceptor);
    }

    private static <T> T createClient(Class<T> clazz,
                                      ExampleProperties.Client props,
                                      RequestInterceptor requestInterceptor) {
        var client = RestClient.builder()
                .baseUrl(props.route())
                .requestInterceptor(requestInterceptor)
                .requestFactory(ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS
                        .withConnectTimeout(Duration.ofMillis(props.connectionTimeout()))
                        .withReadTimeout(Duration.ofMillis(props.readTimeout()))
                ))
                .build();

        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(client))
                .build()
                .createClient(clazz);
    }
}
