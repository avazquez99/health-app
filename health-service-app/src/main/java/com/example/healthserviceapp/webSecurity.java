
package com.example.healthserviceapp;

import com.example.healthserviceapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class webSecurity extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UsuarioService usuarioServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()                 
                    .antMatchers("/css/*","/js/*","/img/*", "/**")
                    .permitAll()
                    .antMatchers("/paciente/*").hasRole("PACIENTE")
                    .antMatchers("/profesional/*").hasRole("PROFESIONAL")
                    .antMatchers("/admin/*").hasRole("ADMIN")              
                .and()
                   .formLogin()
                   .loginPage("/login")
                   .loginProcessingUrl("/login")
                   .usernameParameter("email")
                   .passwordParameter("pass")
                   .defaultSuccessUrl("/index")
                   .permitAll()
                .and()
                   .logout()
                   .logoutUrl("/logout")
                   .logoutSuccessUrl("/index")
                   .permitAll()
                .and()
                   .csrf()
                   .disable();
                        
        
    }
    
    
}
