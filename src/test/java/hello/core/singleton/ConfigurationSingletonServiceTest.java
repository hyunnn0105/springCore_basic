package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonServiceTest {

    @Test
    void configrationTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository  = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository " + memberRepository);                 // @10ad20cb
        System.out.println("memberService  memberRepository " + memberRepository1); // @10ad20cb
        System.out.println("orderService  memberRepository1 " + memberRepository2); // @10ad20cb

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        // memberRepository를 새롭게 생성했다고 생각했지만

    }

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // -> memberRepository가 3번 호출될 것으로 예상했는데 한번씩 호출됨
    // --> @configration

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("Bean = " + bean.getClass());
        // 순수한 class일 경우 hello.core.AppConfig가 출력되어야 함
        // Bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$13481f13
        // 지금 출력된 class는 스프링에서 *CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고
        // 그 상속박은 클래스를 스프링 빈으로 등록했다.
        // *CGLIB : Code Generator Library 의 약자로, 클래스의 바이트 코드를 조작하여 프록시 객체를 생성해 주는 라이브러리
        // @Bean이 붙은 메서트마다 이미 스프링빈이 존해자는 경우 존재하는 빈을 반환하고, 스프링빈이 없으면 생성해서 스프링빈으로 등록하고 반환하는 코드가 동적으로 생성됨
        // => 이미 등록이 된 빈을 반환해주기 때문에 싱글톤이 보장됨
        // => @configration anotation을 삭제할 경우
        // -  memberRepository가 3번 호출되면서 싱글톤이 깨짐
        // - 의존관계 주입을 위해 메서드를 직접 호출할 경우 싱글톤을 보장하지 않는다.

    }
    
}