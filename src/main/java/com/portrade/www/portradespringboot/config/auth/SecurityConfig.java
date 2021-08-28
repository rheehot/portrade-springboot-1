package com.portrade.www.portradespringboot.config.auth;

import com.portrade.www.portradespringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()     //h2-console화면을 사용하기 위한 옵션
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()  //권한설정 -> 전체 열람 권한
                .antMatchers("/api/sample").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())    //권한 설정 -> user권한을 가진사람만 가능
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")  //logout 성공 시 / 주소로 이동
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
