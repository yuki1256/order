package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {


    @Test
    void AutowierdOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    //아래 예제들은 없을 수도 있는 빈이 있다면 3가지 방법으로 처리할 수 있다는 것을 알려주는 거다.
    static class TestBean {

        @Autowired(required = false)//얘는 메소드 자체가 실행안된다
        public void setNoBean(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean1 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean1 = " + noBean3);
        }
    }
}
