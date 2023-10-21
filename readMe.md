      
회원    
- 회원 가입     
- 회원 등급   
- 자체  DB, 외부시스템과 연동     
     
주문과 할인 정책     
- 회원은 상품 주문 O  
- 회원 등급에 따라 할인 적용  
- 할인정책은 모든 VIP 1000원 할인 - 나중에 변경가능   
- 할인정책 변경가능성 high   
   
인터페이스 만들고 구현체를 설계함   
   
클라이언트 -> 회원서비스 -> 메모리 회원 저장소   
   
클래스다이어그램 동적 /   
  
미확정인 할인정책   
클라이언트 --> 회원ID, 상품명, 가격 --> 주문서비스 -> 회원등듭을 가져옴 -> 할인여부 할인정책 역할조회     
=> 클라이언트에게 주문결과 반환      
     
도메인을 역할/ 역할 + 구현으로 그림 나눠그릴 수 있음 
할인정책 유연하게 그려야함 (바꿔끼울 수 있음)  
   
- 주문도메인 클래스 다이어그램   
   
 *역할들의 협력관계를 그대로 재사용할 수 있다.*     

## section 3. 스프링 핵심원리 이해2 - 객체 지향 원리 적용

### 관심사의 분리 
- appconfig를 통해 관심사를 확실하게 분리함
- appconfig는 구현클래스를 선택하고 에플리케이션의 동작에 대한 전체구성을 책임진다.
- serviceImpl은 *기능을 실행하는 책임*만 지면된다. 
- 구현 객체를 생성하고 연결하는 책임

### AppConfig 리팩토링
- new MemoryMemberRepository() 중복을 제거함 => 변경시 한 부분만 변경할 수 있음   
- 역할과 구현클래스가 한눈에 들어와 애플리케이션의 전체구성이 어떻게 되어있는지 빠르게 판단할 수 있음   
- 구성 정보에서 역활과 구현을 명확하게 분리  
- 역활이 잘 드러남
- 중복제거

### 새로운 구조와 할인정책 적용
- AppConfig의 등장으로 애플리케이션을 크게 사용영역과 객체를 생성하고 구성(Configuration)하는 영역으로 분리   
- 구성영역(AppConfig)의 코드만 수정하면 클라이언트 코드인 OrderServiceImpl을 포함한 사용영역의 코드를 변경할 필요없이 할인정책을 변경할 수 있다.

### 좋은 객체지향 설계의 5가지 원칙   
- SRP, DIP, OCP 적용
   
#### SRP 단일책임원칙
- Appconfig 구현을 통해 구현 객체를 생성하고 연결함   
- 클라이언트는 객체를 실행하는 역할만 담당함   

#### DIP 의존관계 역전 원칙
- 추상화에 의존해야지 구체화에 의존해서는 안된다.
- Appconfig가 FixDiscountpolicy 객체 인스턴스를 클라이언트 코드 대신 생성해서 의존관계를 주입해서 DIP 원칙을 지키게됨

#### OCP 개방 패쇄의 원칙   
- 다형성 사용하고 클라이언트가 DIP를 지킴   
- 소프트웨어 요소를 새롭게 확장해도 사용영역의 변경에는 닫혀있다.  

### IoC, DI, 그리고 컨테이너

#### 제어의 역전 IoC   
- 기존에는 구현객체가 프로그램의 제어흐름을 스스로 조종했다.
- AppConfig 등장이후 구현객체는 자신의 로직을 실행하는 역할만 담당한다. 제어의 흐름은 AppConfig가 가져간다.
- 프로그램에 대한 제어릐 흐름에 대한 권한을 AppConfig가 가져간다.   
- 프로그램 제어의 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 **제어의 역전**이라고 한다.   

#### 의존관계 주입 DI
- OrderServiceImpl은 DiscountPolicy 인터페이스에 의존한다
- => 실제 어떤 구현 객체가 사용될지 모른다. 
- 의존관계는 정적인 클래스 관계와 실행시점에 결정되는 동적인 객체 의존관계들을 분리해서 생각해야 함   

#### 정적인 클래스 의존관계   
- import 코드만 보고 의존관계를 쉽게 확인할 수 있음    
- 애플리케이션을 실행하지 않고도 의존관계를 판단할 수 있다.
- but 의존관계만으로 실제 의존관계를 파악할 수 없다.

#### 동적인 클래스 의존관계   
- 애플리케이션 실행 시점에 실제 생성한 객체 인스턴스의 참조가 연결된 의존관계이다.   
- 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.
- 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고 동적인 객체 인스턴스 의존관계를 변경할 수 있다.


#### IoC 컨테이너, DI 컨테이너
- AppConfig처럼 객체를 생성하고 연결해주는 것을 IoC 컨테이너 또는 _**DI 컨테이너**_ 라고 한다.   
- 최근에는 주로 DI 컨테이너라고 말한다.   

### 스프링 컨테이너
- Context를 스프링 컨테이너라고 한다.
- 기존에는 개발자가 AppConfig 를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다. 
- 스프링 컨테이너는 @Configuration 이 붙은 AppConfig 를 설정(구성) 정보로 사용한다. 여기서 @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다. 
- 스프링 빈은 @Bean 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다. ( memberService , orderService )
- 이전에는 개발자가 필요한 객체를 AppConfig 를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다. 스프링 빈은 Context.getBean() 메서드를 사용해서 찾을 수 있다. 
- 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 



## section 4. 스프링 컨테이너와 스프링 빈

### 스프링 컨테이너 생성 
- ApplicationContext를 스프링 컨테이너라고 한다.
- 스프링 컨테이너는 xml 기반과 에노테이션 기반의 자바 설정 클래스로 만들 수 있다.

### 스프링 컨테이너의 생성 과정 
1. 스프링 컨테이너 생성
- 스프링 컨테이너를 생성할 시 구성정보를 지정해주어야 한다.
2. 스프링 빈 등록
- 스프링 컨테이너에 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다.
- 빈은 주로 메서드 이름을 사용하며 항상 다른이름을 부여해야한다.
3. 스프링 빈 의존관계 설정
- 스프링 빈을 생성하고, 의존정보를 주입하는 단계가 나누어져 있다. 
- 자바코드로 스프링빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다.


### BeanFactory와 ApplicationContext
- BeanFactory는 스프링 컨테이너의 최상위 인터페이스로 스프링 빈을 관리하고 조회하는 역할을 함 
- ApplicationContext는 BeanFactory 기능을 상속받는다
- ApplicationContext는 빈 관리기능 + 편리한 부가기능을 제공한다.
- BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext를 사용한다.
- BeanFactory나 ApplicationContext를 스프링 컨테이너라고 한다. 


### 다양한 설정 형식 지원 - 자바코드, XML
- 어노테이션 기반 설정
- AnotaionConfigApplicationcontext 클래스를 사용하면서 자바코드 설정정보를 넘긴다.

### 스프링 빈 설정 메타 정보 - BeanDefinition
- BeanDefinition = Bean설정 메타정보
- 

## section5. 싱글톤 컨테이너

### 웹 어플리케이션과 싱글톤
- 웹 어플리케이션은 보통 여러 사람이 동시에 요청을 함     
-> 고객이 요청 할 때마다 객채가 새롭게 생성되면 메모리 낭비가 심하다. 
(객체 생성시 변수는 스택 메모리 / 인스턴스는 힙메모리에 나누어서 저장됨 https://pathas.tistory.com/123) 
- 메모리 낭비를 줄이기 위해서 해당 객체가 딱 한기자만 생성되고 공유하고록 설계하면 됨
==> 싱글톤 패턴을 적용하면 해결할 수 있음 

### 싱글톤 패턴
- 클래스의 인스턴스가 딱 한개만 생성하는 것을 보장함    
-> 그래서 객체 인스턴스를 2개이상 생성하지 못하도록 막아야함   
-> private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야함

* 싱클톤 패턴을 구현하는 다른 방법
- https://readystory.tistory.com/116

1. Eager Initialization
싱글톤 클래스의 인스턴스를 클래스 로딩 단계에서 생성함

```java
    public class Singleton {
    
        private static final Singleton instance = new Singleton();
        
        // private constructor to avoid client applications to use constructor
        private Singleton(){}
     
        public static Singleton getInstance(){
            return instance;
        }
    }
```

2. Static Block Initialization
```java
public class Singleton {
 
    private static Singleton instance;
    
    private Singleton(){}
    
    //static block initialization for exception handling
    static{
        try{
            instance = new Singleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static Singleton getInstance(){
        return instance;
    }
}
```
- static block을 통해서 인스턴스 생성시 발생할 수 있는 예외에 대한 처리 가능 


3. Lazy Initialization
```java
public class Singleton {
 
    private static Singleton instance;
    
    private Singleton(){}
    
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}

```
- getInstance() 메서드 호출 시 인스턴스가 없다면 생성됨
-> 사용하지 않을 경우 발생하는 인스턴스 낭비 해결
- multi-thread 환경에서 동기화 문제 발생
-> 셍성되지 않은 시점에서 여러 쓰레드가 메서드를 호출한다면 단 하나의 인스턴스를 생성하는 싱글톤 패턴을 위반하는 문제 발생

4. Thread Safe Singleton
5. Bill Pugh Singleton Implementaion
6. Enum Singleton


