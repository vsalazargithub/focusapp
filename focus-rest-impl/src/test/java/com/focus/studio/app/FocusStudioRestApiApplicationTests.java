package com.focus.studio.app;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.focus.studio.app.config.FocusStudioRestApiApplication;

@SpringBootTest(classes = FocusStudioRestApiApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class FocusStudioRestApiApplicationTests {}
