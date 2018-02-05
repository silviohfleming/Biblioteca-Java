package br.biblioteca.livros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/usuarios/login").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/autentication").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/register").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/register").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/registration").permitAll()
                //.antMatchers(HttpMethod.GET, "/usuarios/listadmin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/usuarios/login").permitAll()
                .and().logout().permitAll();
    }


}

