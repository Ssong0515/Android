package bitc.fullstack405.adroid_member11.service;

import bitc.fullstack405.adroid_member11.model.Member;
import bitc.fullstack405.adroid_member11.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }


    @Transactional
    public Member update(Long id, Member member) {
        val value = memberRepository.findById(id).get();
        value.setEmail(member.getEmail());
        value.setName(member.getName());
        value.setPhone(member.getPhone());
        return value;
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
