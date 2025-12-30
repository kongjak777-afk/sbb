package com.mysite.sbb.user;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUserName(username);
        user.setEmail(email);

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // 패스워드가 저장되기 전에 암호화
        user.setPassword(passwordEncoder.encode(password));
//        user.setPassword(password);
        this.userRepository.save(user);

    }



    public SiteUser getUser(String username){
        Optional<SiteUser> siteUser = userRepository.findByUserName(username);
        if(siteUser.isPresent()){
            return siteUser.get();
        }else {
            throw new DataNotFoundException("사이트 유저가 존재하지 않습니다.");
        }

    }



}
