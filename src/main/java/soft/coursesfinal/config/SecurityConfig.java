package soft.coursesfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // For study projects only.
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                        "/", "/login", "/register",
                        "/api/users/register",
                        "/courses", "/courses/**",
                        "/css/**", "/js/**", "/images/**"
                ).permitAll()

                // ADMIN
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasAuthority("ROLE_ADMIN")

                // INSTRUCTOR
                .requestMatchers("/instructor/**").hasAuthority("ROLE_INSTRUCTOR")
                .requestMatchers(HttpMethod.POST, "/api/lessons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_INSTRUCTOR")
                .requestMatchers(HttpMethod.PUT, "/api/lessons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_INSTRUCTOR")
                .requestMatchers(HttpMethod.DELETE, "/api/lessons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_INSTRUCTOR")

                // STUDENT
                .requestMatchers("/my-courses/**").hasAuthority("ROLE_STUDENT")
                .requestMatchers("/api/enrollments/**").hasAuthority("ROLE_STUDENT")

                // AUTH
                .requestMatchers("/profile").authenticated()

                .anyRequest().authenticated()
        )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/entering")
                        .defaultSuccessUrl("/profile", true)
                        .failureUrl("/login?error")
                        .usernameParameter("user_email")
                        .passwordParameter("user_password")
                )
                .logout(logout -> logout
                        .logoutUrl("/sign-out")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/login?accessDenied")
                );

        return http.build();
    }
}
