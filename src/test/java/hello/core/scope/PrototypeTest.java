package hello.core.scope;

import hello.core.discount.DiscountPolicy;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    public void prototypeScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1: " + bean1);
        System.out.println("prototypeBean2: " + bean2);

        ac.close();

        Assertions.assertThat(bean1).isNotSameAs(bean2);

    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean destroy");
        }

    }
}
