package com.api.banco;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")

public class HelloController {
    @Get
    public String  hello() {
        return "hello World";
    }
}
