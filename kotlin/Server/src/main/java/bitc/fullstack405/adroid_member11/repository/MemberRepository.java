package bitc.fullstack405.adroid_member11.repository;

import bitc.fullstack405.adroid_member11.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
