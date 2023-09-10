package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // 직접 할인 정책을 선택해서 구현함 (다양한 책임을 가지게됨) ==> 관심사의 분리가 필요함
    // 책임을 분산해야 한다.
    // private final DiscountPolicy discountPolicy = new FixdiscountPolicy();
    
    // 변동금액으로 변경하기 ==> OCP, DIP 위반
    // 추상 인터페이스 + 구현 클래스에도 의존하고 있음 (DIP 위반)
    // interface만 변경하도록 구조를 변경해야 한다.
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // ==> interface에만 의존하는 코드로 변경 but 구현체가 없음
    // 해결방안 : 클라이언트의 OrderServiceImpl에  discountPolicy의 구현 객체를 대신 생성하고 주입해야 한다.

    // 생성자 주입
    // OrderServiceImpl 입장에서 생성자를 통해 어떤 객체가 주입될지는 미지수이다.
    // 객체의 의존관계가 주입된다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberID, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberID);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종생성된 주문반환
        return new Order(memberID, itemName, itemPrice, discountPrice);
    }
}
