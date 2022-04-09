package site.metacoding.project_sh.config;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.project_sh.config.interceptor.SessionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    // registry.addInterceptor(new SessionInterceptor())
    // .addPathPatterns("/s/**"); // *, ** 별 한개일 때, 별 2개일 때도 있어서 둘 다 테스트 해보세요!
    // }

    // 쿼리문에 JsessionId 떠서 안뜨게 해줌
    @Bean
    public ServletContextInitializer clearJsession() {
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
                SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
                sessionCookieConfig.setHttpOnly(true);
            }

        };
    }
}
