package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 한개만 생성함
    // SingletonService의 객체 instance를 private static final 키워드로 선언함
    // private static final
    // 1. 변수의 재할당 불가능
    // 2. 메모리에 한 번 올라가면 같은 값을 클래스 내부의 전체 필드, 매서드에서 공유함

    // private final
    // 선언한 변수 재할당 불가능
    // 해당 필드, 메서드 별로 호출할 때 마다 값을 새롭게 할당(인스턴스화) 한다.
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용함
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하도록 막음
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
