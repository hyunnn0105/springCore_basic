package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
    
    // 필드주입 - 외부에서 변경이 불가능함
    /*
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
    */
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 수정자 주입
    /*
    @Autowired
    public void setDiscountPolicy(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void getDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */

    // 일반 메서트 주입 - 생성자주입과 수정자 주입을 하는 단계에서 가능하기 때문에 사용 X
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
