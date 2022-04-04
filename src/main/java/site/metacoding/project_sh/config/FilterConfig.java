package site.metacoding.project_sh.config;

import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import lombok.RequiredArgsConstructor;
import site.metacoding.project_sh.config.filter.Myfilter1;

@RequiredArgsConstructor
public class FilterConfig {
    private final HttpSession session;

    public FilterRegistrationBean<?> filter1() {
        FilterRegistrationBean<Myfilter1> bean = new FilterRegistrationBean<>(new Myfilter1(session));
        bean.addUrlPatterns("/s/*");
        bean.setOrder(1);
        return bean;
    }
}
