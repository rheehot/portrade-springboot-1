package com.portrade.www.portradespringboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// User의 CRUD를 관리
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);   //소셜 로그인으로 반환되는 값 중, email을 이용하여 기가입자인지 판단
}


