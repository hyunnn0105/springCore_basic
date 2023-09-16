package hello.core.beenFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 조회")
    void findBeanByNames(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberService.class);
        /*
            memberService = hello.core.member.MemberServiceImpl@2c282004
            memberService.getClass() = class hello.core.member.MemberServiceImpl
         */
    }    
    
    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    // 구현(Impl)에 의존하는 코드
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByNames2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNamesX(){
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class
                ,() -> ac.getBean("xxxx", MemberService.class));
    }

}
