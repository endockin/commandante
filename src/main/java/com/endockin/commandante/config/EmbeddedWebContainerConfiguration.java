package com.endockin.commandante.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedWebContainerConfiguration implements EmbeddedServletContainerCustomizer {

    private static final int PORT = 8081;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(PORT);
    }

}
