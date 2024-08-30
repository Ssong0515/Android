package bitc.fullstack405.android_member.controller;

import bitc.fullstack405.android_member.entity.Member;
import bitc.fullstack405.android_member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/select")
    public List<Member> select(){
        return memberService.selectAll();
    }

    @PostMapping("/insert")
    public Member insert(@RequestBody Member member){
        return memberService.save(member);
    }

    @PutMapping("/update{id}")
    public Member update(@PathVariable("id") Long id, @RequestBody Member member) {
        return memberService.update(id, member);
    }

    @DeleteMapping("/delete{id}")
    public void delete(@PathVariable("id") Long id) {
        memberService.delete(id);
    }

}
