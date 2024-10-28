package com.bytebard.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(value = {"com.bytebard"})
@EnableWebMvc
public class DefaultConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfig.class);

}
