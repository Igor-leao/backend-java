package com.algaworks.example.auth.server.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class AuthSecurityConfig {
    //classe de configuração para o OUATH2
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
           // configuração padrão para o auth2, seguindo a documentação
               // verifica e permite os endpoints publicos e não publicos de acordo com o http que vc passa

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.formLogin(Customizer.withDefaults()).build();
    }


    @Bean
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        /*configurações personalizadas do OAuth2, criadas pelo usuaŕio*/
       http.authorizeRequests().anyRequest().authenticated();
        /*leia a linha de cima, ela é bem explicativa*/
        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
        /*Aqui onde o clientRepository e criado e armazeno os dados em um banco memória, futuramente redis
        * também é possível criar um ClienteRegistered personalisado, assim como o jpa (igorFOOD)
        * olhar o RegisteredCLienteRepository
        * aqui onde faz o registro pra o inicio da geração do token*/
        RegisteredClient awuserClient = RegisteredClient
                .withId("1")
                .clientId("awuser")
                .clientSecret(passwordEncoder.encode("123456"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("users:read")
                .scope("users:write")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(5))
                .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false).build())
                .build();
        return new InMemoryRegisteredClientRepository(
               // aqui onde vai ficar o banco redis
               // é bom ser um banco em memoria
                Arrays.asList(awuserClient)

        );
    }

    @Bean
    public ProviderSettings providerSettings(AuthProperties authProperties) {

           /*quem assinou e que forma proveu o token e qual é a forma de asinar
           * pode ser customizado */

        return ProviderSettings.builder()
                .issuer(authProperties.getProviderUri())
                .build();
    }

    @Bean
    public JWKSet jwkSet(AuthProperties authProperties) throws Exception {
        /*Configugurar a leitura do par de chaves para fazer a assinatura
        e prover a chave para os resourceServers*/
        final var jksProperties = authProperties.getJks();
        final String jksPath = jksProperties.getPath();
        final InputStream inputStream = new ClassPathResource(jksPath).getInputStream();

        final KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, jksProperties.getStorepass().toCharArray());

        RSAKey rsaKey= RSAKey.load(keyStore,
                jksProperties.getAlias(),
                jksProperties.getKeypass().toCharArray());

        return new JWKSet(rsaKey);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(JWKSet jwkSet) {
        /*vai ajudar a gerenciar o jwkset a configurar a chave correta, pois podem ter várias*/
        return ((jwkSelector, context) -> jwkSelector.select(jwkSet));
    }


    /**/
    @Bean
    public JwtEncoder jwtEncoder (JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

}
