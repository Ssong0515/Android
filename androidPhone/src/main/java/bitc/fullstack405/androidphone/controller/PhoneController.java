package bitc.fullstack405.androidphone.controller;

import bitc.fullstack405.androidphone.model.Phone;
import bitc.fullstack405.androidphone.service.PhoneService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    // 전체보기
    @GetMapping("/list")
    public List<Phone> list(){
        return phoneService.list();
    }

    // 추가
    @PostMapping("/insert")
    public Phone insert(@RequestBody Phone phone){
        return phoneService.insert(phone);
    }

    // 수정
    @PutMapping("/update/{id}")
    public Phone update(@PathVariable("id") Long id, @RequestBody Phone phone){
        return phoneService.update(id, phone);
    }
    
    // 삭제
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        phoneService.delete(id);
    }

}
