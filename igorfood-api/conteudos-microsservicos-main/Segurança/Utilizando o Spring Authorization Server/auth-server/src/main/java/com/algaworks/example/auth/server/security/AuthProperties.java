package com.algaworks.example.auth.server.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Validated
@Getter
@Setter
@ConfigurationProperties("aw.auth")
/*olhar a configuração do aw no aplic.yml*/
public class AuthProperties {

    @NotBlank
    private String providerUri;

    @NotNull
    private JksProperties jks;

    @Data
    static class JksProperties {
        @NotBlank
        private String keypass;

        @NotBlank
        private String storepass;

        @NotBlank
        private String alias;

        @NotBlank
        private String path;
    }
}
