package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByOrderByIdAsc();

    // TODO 課題A-3：指定したメールアドレスの会員が既に存在するかを判定するメソッドを追加する
}
