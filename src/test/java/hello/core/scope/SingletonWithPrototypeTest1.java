package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isSameAs(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isSameAs(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        assertThat(count1).isSameAs(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isSameAs(2);

    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init: " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy: ");
        }
    }



}
