package com.cq.edu.securityconfig;

import com.cq.edu.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //配置类开启权限控制注解
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //开启请求访问控制
        http.authorizeRequests()
                //共有
                .antMatchers("/login/**", "/noFound/**", "/main/**","/votePage/**",
                        "/user/errors", "/user/doRegister", "/user/isRegister",
                        "/user/register", "/admin/arrangeMatch").permitAll()

                //共有
                .antMatchers("/user/main", "/vote/**", "/admin/rankings",
                                        "/user/uploadHeadImg", "/user/logout","/common/**","/admin/showVote",
                                        "/admin/votePage/**")
                .hasAnyAuthority("ROLE_admin", "ROLE_event_admin", "ROLE_viewer","ROLE_judge")

                //活动管理员
                .antMatchers("/admin/**","/eventadmin/deleteSinger").hasAnyAuthority("ROLE_admin", "ROLE_event_admin")

                //评委
                .antMatchers("/judge/**").hasAnyAuthority("ROLE_judge", "ROLE_admin")

                //观众
                .antMatchers("/viewer/addVoteNum").hasAnyAuthority("ROLE_viewer", "ROLE_admin")

                .anyRequest().authenticated()
                .and().csrf().disable();

        //开启用户登录配置
        http.formLogin()
                .loginPage("/user/login").permitAll()
                .loginProcessingUrl("/user/doLogin").permitAll()
                .defaultSuccessUrl("/user/index")
                .failureUrl("/user/errors")
                .usernameParameter("userAccount")
                .passwordParameter("userPassword");

        //开启退出配置
        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true);

        //开启记住我配置,并设置一周过期
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .tokenRepository(tokenRepository());

        //设置错误时响应的页面
        http.exceptionHandling()
                .accessDeniedPage("/noauth");
        return http.build();
    }

    /**
     * 持久化Token存储
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        jr.setCreateTableOnStartup(false);
        return jr;
    }

    // 获取AuthenticationManager（认证管理器），登录时认证使用。
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();

        return authenticationManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
