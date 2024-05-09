package pl.edu.wszib.book.store.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.filters.AdminFilter;
import pl.edu.wszib.book.store.services.IBookService;
import pl.edu.wszib.book.store.services.impl.BookService;

@Configuration
@ComponentScan("pl.edu.wszib.book.store")
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/book/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
