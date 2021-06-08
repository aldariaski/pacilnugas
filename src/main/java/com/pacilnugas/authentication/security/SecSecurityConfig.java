package com.pacilnugas.authentication.security;
//import com.pacilnugas.authentication.core.Account;
//import com.pacilnugas.authentication.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

//import java.util.List;

@Configuration
@EnableWebSecurity
//@ImportResource({ "classpath:webSecurityConfig.xml" })
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserService userService;

    public SecSecurityConfig() {
        super();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        List<Account> allAccounts = userService.getAllUser();
//        UserDetailsManagerConfigurer udmc = auth.inMemoryAuthentication();
//        for(Account account : allAccounts){
//            udmc = udmc.withUser(account.getUsername()).password(passwordEncoder().encode(account.getPassword())).roles(account.getType()).and();
//        }
//        udmc.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER");
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginMenu")
                .loginProcessingUrl("/loginAccount")
                .defaultSuccessUrl("/accountList", true)
                .failureUrl("/authentication/loginPage.html?error=true")
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler());
    }
}