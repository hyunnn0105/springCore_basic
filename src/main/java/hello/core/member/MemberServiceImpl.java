package hello.core.member;

// 구현체 만들어봄
public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberID) {
        return memberRepository.findById(memberID);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
