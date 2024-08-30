package bitc.fullstack405.android_member.repository;

import bitc.fullstack405.android_member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
