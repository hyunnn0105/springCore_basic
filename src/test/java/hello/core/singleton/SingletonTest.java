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
}
