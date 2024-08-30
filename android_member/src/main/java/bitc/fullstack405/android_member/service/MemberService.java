package bitc.fullstack405.android_member.service;

import bitc.fullstack405.android_member.entity.Member;
import bitc.fullstack405.android_member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;


    public List<Member> selectAll() {
        return memberRepository.findAll();
    }

    public Member save(Member member){
        return memberRepository.save(member);
    }

    @Transactional
    public Member update(Long id, Member member) {
        var chageValue = memberRepository.findById(id).get();
        chageValue.setName(member.getName());
        chageValue.setPhone(member.getPhone());
        chageValue.setEmail(member.getEmail());
        return chageValue;
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

}
