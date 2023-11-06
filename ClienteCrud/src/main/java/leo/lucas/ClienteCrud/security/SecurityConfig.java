package leo.lucas.ClienteCrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe de configuração de segurança para autenticação e autorização.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
    @Override
    protected void configure(HttpSecurity http) throws Exception{
       // http.cors().and().csrf().disable();
    	http
        .csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .httpBasic();
    }
    
    /**
     * Configura o codificador de senhas utilizado para autenticação.
     *
     * @return O codificador de senhas.
     */
    public PasswordEncoder passwordEncoder() {
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    /**
     * Configura o sistema de autenticação com um usuário em memória.
     *
     * @param auth O construtor de autenticação.
     * @throws Exception Em caso de erro na configuração de autenticação.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("leo")
            .password("{noop}lucas") // {noop} é usado para indicar que a senha não está codificada
            .roles("USER");
    }
}