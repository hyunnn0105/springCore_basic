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
}