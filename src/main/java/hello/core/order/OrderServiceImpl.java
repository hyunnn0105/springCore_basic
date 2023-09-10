package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixdiscountPolicy();
    
    // 변동금액으로 변경하기 ==> OCP, DIP 위반
    // 추상 인터페이스 + 구현 클래스에도 의존하고 있음 (DIP 위반)
    // interface만 변경하도록 구조를 변경해야 한다.
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // ==> interface에만 의존하는 코드로 변경 but 구현체가 없음
    // 해결방안 : 클라이언트의 OrderServiceImpl에  discountPolicy의 구현 객체를 대신 생성하고 주입해야 한다.
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberID, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberID);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종생성된 주문반환
        return new Order(memberID, itemName, itemPrice, discountPrice);
    }
}
