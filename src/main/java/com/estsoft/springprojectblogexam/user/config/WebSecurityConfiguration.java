package com.estsoft.springprojectblogexam.user.config;

import com.estsoft.springprojectblogexam.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 스프링 시큐리티 설정
@Configuration
public class WebSecurityConfiguration {

    private final UserDetailService userDetailService;

    WebSecurityConfiguration(UserDetailService service) {
        this.userDetailService = service;
    }


    // ignore - 스프링 시큐리티를 해제하는 상황에 대한 추가.
    // 특정 요청은 스프링 시큐리티 설정을 타지 않도록 설정
    @Bean
    public WebSecurityCustomizer ignore() {
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers(toH2Console())  // /h2-console 요청에 대해서는 login 화면 나오지 않도록 함.
                .requestMatchers("/static/**");
    }

    // 패스워드 인코더로 사용할 빈을 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        /* 메서드 체인 형태는 예전 버전이다.-> 람다 표현식으로 바뀜
        return httpSecurity.authorizeHttpRequests()    // 3) 인증, 인가 설정
                //특정 요청에 대해서는 인증을 하지 않겠다.
                .requestMatchers("/login", "/signup", "/user").permitAll()
                //특정 요청에 대해서 인가에 대한 처리도 넣을 수 있다.
                .requestMatchers("/api/external").hasRole("admin")
                // 앞에서 정의한 요청 외에는 인증을 하겠다. 여기까지 한 묶음으로 설정 됨.
                .anyRequest().authenticated()
                .and()
                .formLogin()        //4) 폼 기반 로그인 설정
                .loginPage("/login")
                .defaultSuccessUrl("/articles")  // 로그인 성공했을 경우 리디렉션 URL - main 또는 index page(view)
                .and()
                .logout()       // 5) 로그아웃 설정
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)  // 로그아웃 할 때 해당 세션을 제거하겠는가? true = 제거하겠다.
                .and()
                .csrf().disable()       // 6) csrf 비활성화 -> default 값은 활성(enable)이다.
                .build();
         */

        httpSecurity.authorizeHttpRequests(auth ->              // 인증, 인가 설정
                        // login, signup, user 는 누구나 접근 가능
                        auth.requestMatchers("/login", "/signup", "/user").permitAll()
                                // articlse/** 에 접근하려면 ROLE_ADMIN 권한이 있어야 됨 - 기본 user권한으로는 403 Error 발생
                                .requestMatchers("/articles/**").hasRole("ADMIN")   //hasRole 은 ADMIN 앞에 ROLE_(ROLE_ADMIN) 프리픽스를 붙임
                                .requestMatchers("/articles/**").hasAuthority("user") // user Role 가진 user
                                .anyRequest().authenticated())
                        .formLogin(auth -> auth.loginPage("/login")     // 폼 기반 로그인 설정
                                .defaultSuccessUrl("/articles",true)) // login 이후 queryparameter 에 ?continue가 붙는거 방지
                        .logout(auth -> auth.logoutSuccessUrl("/login") // 로그아웃 설정
                                .invalidateHttpSession(true)); // 세션을 모두 삭제(로그아웃 할 때)
                        //.csrf(auth -> auth.disable());                  // csrf 비활성화
        return httpSecurity.build();

    }
/*  // AuthenticationManger -> 인증에 필요한 정보들을 가지고 있다.
    // userDetailService를 구현해놨고 bCryptPasswordEncoder도 등록해놨기 때문에 없어도 됨.

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)  // 8) 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
*/

}
