package hello.core.singletonTest;

public class SingletonService {

    private static final SingletonService instance
            = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {}//생성자를 프라이빗으로 해서 외부에서 접근을 막는다

    public void logic(){
        System.out.println("실글톤 객체 logic 호출");
    }
}
