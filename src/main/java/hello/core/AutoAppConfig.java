package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",//basePackage는 해당 패키지에서만 컴포넌트 스캔을 하여 필요 클래스만 빈을 생성할 수 있다 쓸데 없는 곳까지 스캔 할 필요가 없어 성능이 좋아짐
        //만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다. 결국은 그냥 패키지만 스캔할 수 있게 됨 디폴트로 나둬도 좋을 듯 
        
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class )
)//@Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에 앞서 만든 AppConfig를 제외하기위해 일부러 이렇게 설정 한 것, 보통은 이렇게 작성하진 않는다 @ComponentScan만 작성한다.
//@ComponentScan은 @Component이노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다. @Configuration도 @Component를 갖고 있어 스캔이 되므로 위에 처럼 제외시킨 것.
public class AutoAppConfig {
}
