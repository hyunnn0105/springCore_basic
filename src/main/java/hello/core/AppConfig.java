package hello.core;

import hello.core.discount.DiscountPolicy;
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
    
    // 역할과 구현을 분리함
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    // orderService 조회시 orderServiceImpl, MemoryMemberRepository, FixdiscountPolicy 조회함
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    // new MemoryMemberRepository() 중복을 제거함 => 변경시 한 부분만 변경할 수 있음
    // 역할과 구현클래스가 한눈에 들어와 애플리케이션의 전체구성이 어떻게 되어있는지 빠르게 판단할 수 있음
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixdiscountPolicy();
    }


}
