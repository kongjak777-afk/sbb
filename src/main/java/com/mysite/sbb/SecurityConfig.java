package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {           //CSRF 예외사항 등록
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll())   // permitAll() 아무나 접근가능하게 풀어놓음
                .csrf((csrf) -> csrf                //h2-console 예외처리
                        .ignoringRequestMatchers(("/h2-console/**")))
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))) ;

        return http.build();   // 보안 설정을 마칠 때 사용하는 최종 빌드 메서드  http 는 아무이름이나 위에서 선언한것
    }
    // 스프링 컨테이너가 관리 하도록 빈을 올림
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
