package hello.core.scope;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("singelton bean1: " + bean1);
        System.out.println("singelton bean2: " + bean2);
        assertThat(bean1).isSameAs(bean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean destroy");
        }

    }
}

