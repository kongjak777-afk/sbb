package com.mysite.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {             //Long 은 id
    Optional<SiteUser> findByUserName(String userName);     //Optional 쓰는 이유는 있을수도 없을 수도 있을 때 쓴다.

}
