package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    
    @Test
    @DisplayName("spirng이 없는 순수한 DI 컨테이너")
    void pureContainer(){

        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        
        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberservice1 ======== " + memberService1); //2c4d1ac
        System.out.println("memberservice2 ======== " + memberService2); //ab7395e

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest(){
        
        // 1. priveate로 new 키워드를 막아둠
        // 'SingletonService()' has private access in 'hello.core.singleton.SingletonService'
        // new SingletonService();

        // 1. 조회 : 호출할 때마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();

        // 2. 조회 : 호출할 때마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 호출 시 같은 객체 인스턴스를 반환함
        System.out.println("singletonService1 ======== " + singletonService1); // @ab7395e
        System.out.println("singletonService2 ======== " + singletonService2);  // @ab7395e

        assertThat(singletonService1).isSameAs(singletonService2);
        
        singletonService1.logic();
        // 싱글톤 객체 로직 호출

    }
}
