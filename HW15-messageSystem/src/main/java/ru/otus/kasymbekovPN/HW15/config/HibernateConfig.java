package ru.otus.kasymbekovPN.HW15.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.db.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW15.db.hibernate.sessionManager.SessionManagerHibernate;

@Configuration
@ComponentScan
@RequiredArgsConstructor
public class HibernateConfig {
    private final ApplicationContext applicationContext;

    @Bean
    public SessionManagerHibernate sessionManagerHibernate(){
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                OnlineUser.class);
        return new SessionManagerHibernate(sessionFactory);
    }
}
