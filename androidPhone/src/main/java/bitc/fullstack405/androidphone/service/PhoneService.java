package bitc.fullstack405.androidphone.service;

import bitc.fullstack405.androidphone.model.Phone;
import bitc.fullstack405.androidphone.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    public PhoneRepository phoneRepository;

    // 전체보기
    public List<Phone> list() {
        return phoneRepository.findAll();
    }

    // 추가
    public Phone insert(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Transactional
    public Phone update(Long id, Phone phone) {
        var oriValue = phoneRepository.findById(id).get();
        oriValue.setName(phone.getName());
        oriValue.setPhone(phone.getPhone());
        return oriValue;
    }

    public void delete(Long id) {
        phoneRepository.deleteById(id);
    }
}
