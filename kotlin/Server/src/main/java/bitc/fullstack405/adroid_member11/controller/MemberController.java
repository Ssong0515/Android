package bitc.fullstack405.adroid_member11.controller;

import bitc.fullstack405.adroid_member11.model.Member;
import bitc.fullstack405.adroid_member11.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public List<Member> list() {
        return memberService.findAll();
    }

    @PostMapping("/insert")
    public Member insert(@RequestBody Member member) {
        return memberService.save(member);
    }

    @PutMapping("/update/{id}")
    public Member update(@PathVariable("id") Long id, @RequestBody Member member) {
        return memberService.update(id, member);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        memberService.delete(id);
    }


}
