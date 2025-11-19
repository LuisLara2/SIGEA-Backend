package com.zentry.sigea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zentry.sigea.security.CustomAccessDeniedHandler;
import com.zentry.sigea.security.CustomAuthenticationEntryPoint;
import com.zentry.sigea.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
        JwtAuthenticationFilter jwtAuthFilter , 
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
        CustomAccessDeniedHandler customAccessDeniedHandler
    ){
        this.jwtAuthFilter = jwtAuthFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos para autenticación
                        .requestMatchers("/api/{version}/usuarios/auth/**", "/").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/webjars/**").permitAll()
                        // Endpoints de notificaciones - temporalmente abiertos para pruebas
                        .requestMatchers("/api/v1/notificaciones/**", "/api/{version}/notificaciones/**").permitAll()
                        .requestMatchers("/api/v1/estados-notificacion/**", "/api/{version}/estados-notificacion/**").permitAll()
                        .requestMatchers("/api/v1/tipos-notificacion/**", "/api/{version}/tipos-notificacion/**").permitAll()
                        // Endpoints de inscripciones - temporalmente abiertos para pruebas
                        .requestMatchers("/api/v1/inscripciones/**", "/api/{version}/inscripciones/**").permitAll()
                        .requestMatchers("/api/v1/estados-inscripcion/**", "/api/{version}/estados-inscripcion/**").permitAll()
                        // Endpoints de actividades - temporalmente abiertos para pruebas
                        .requestMatchers("/api/v1/actividades/**", "/api/{version}/actividades/**").permitAll()
                        // Endpoints de sesiones - temporalmente abiertos para pruebas
                        .requestMatchers("/api/v1/sesiones/**", "/api/{version}/sesiones/**").permitAll()
                        // Endpoints de certificaciones - temporalmente abiertos para pruebas
                        .requestMatchers("/api/v1/certificaciones/**", "/api/{version}/certificaciones/**").permitAll()
                        // Endpoints protegidos por rol
                        .requestMatchers("/api/{version}/usuarios/administrador/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/{version}/usuarios/organizador/**").hasRole("ORGANIZADOR")
                        .requestMatchers("/api/{version}/usuarios/participante/**").hasRole("PARTICIPANTE")
                        // .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
