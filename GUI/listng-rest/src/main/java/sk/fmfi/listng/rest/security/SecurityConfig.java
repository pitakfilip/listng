package sk.fmfi.listng.rest.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sk.fmfi.listng.rest.security.auth.AppUserDetailsService;
import sk.fmfi.listng.rest.security.auth.AuthTokenFilter;
import sk.fmfi.listng.rest.security.utils.UnauthorizedEntryPoint;


@EnableWebSecurity
@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Autowired
    UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    AppUserDetailsService userDetailsService;
    
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("security config tu som");
        http
                .cors().and().csrf().disable()  // VYMAZAT
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/api/actuator/**").permitAll()
                .antMatchers("/api/swagger-ui/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/**").authenticated()
//                .anyRequest().authenticated()
                
                .and().addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//                .authorizeRequests()
//                .and().authenticationProvider(authenticationProvider());

        return http.build();
    }
}
