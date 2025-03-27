package org.ldamler.example.configs.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "example", ignoreInvalidFields = true)
@Data
public class ExampleProperties implements InitializingBean {

    private Client dummy;


    @Override
    public void afterPropertiesSet() throws Exception {
        // noop
    }

    public record Client(String route,
                         long connectionTimeout,
                         long readTimeout) {
    }
}
