package hello.core.member;

import org.apache.commons.logging.Log;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberID);

}
