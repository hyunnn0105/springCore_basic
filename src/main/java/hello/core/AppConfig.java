package hello.core;

import hello.core.discount.FixdiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 애플리케이션의 실제 동작에 필요한 구현객체를 생성한다.
// 생성한 객체 인스턴스의 참조를 셍성자의 주입을 통해 연결한다.
// 객체의 생성과 연결을 담당함
// ==> 객체를 생성하고 연결하는 역할과 실행하는 역할(impl)이 명확히 분리된다.
// 의존관계 주입
public class AppConfig {

    // impl을 생성하고, 생성한 impl의 사용할 repository를 지정함
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    // orderService 조회시 orderServiceImpl, MemoryMemberRepository, FixdiscountPolicy 조회함
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixdiscountPolicy());
    }

}
