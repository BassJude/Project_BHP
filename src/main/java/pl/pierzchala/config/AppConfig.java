package pl.pierzchala.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableWebMvc
@ComponentScan("pl.pierzchala")
@EnableTransactionManagement
@EnableJpaRepositories("pl.pierzchala.repository")
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("bhpPersistenceUnit");
        return emfb;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager(emf);
        return tm;
    }
////////////////////////////////////// konwertery !!!!!!!!
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//
//        registry.addConverter(getPublisherConverter());
//        registry.addConverter(getAuthorConverter());
//        registry.addConverter(getCategoryConverter());
//    }
//
//    @Bean
//    public PublisherConverter getPublisherConverter() {
//        return new PublisherConverter();
//    }
//
//
//    @Bean
//    public AuthorConverter getAuthorConverter() {
//        return new AuthorConverter();
//    }
//
//    @Bean
//    public CategoryConverter getCategoryConverter() {
//        return new CategoryConverter();
//    }

    /////////////////////////////////////////////

    // niepotrzebne , to do pliku tłumaczeń
//    @Bean(name = "localeResolver")
//    public LocaleContextResolver getLocaleContextResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(new Locale("pl", "PL"));
//        return localeResolver;
//    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }


}

