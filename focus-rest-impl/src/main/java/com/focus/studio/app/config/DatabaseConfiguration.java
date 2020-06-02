package com.focus.studio.app.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.focus.studio.app.config.rest","com.focus.studio.app.service.impl"})
public class DatabaseConfiguration {

}
