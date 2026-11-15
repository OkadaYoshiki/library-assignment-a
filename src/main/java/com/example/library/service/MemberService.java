package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.entity.Member;
import com.example.library.form.MemberForm;
import com.example.library.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAllByOrderByIdAsc();
    }

    /**
     * 機能⑦：会員登録
     *
     * TODO 課題A-3：会員登録処理を実装する
     *   1. 同じメールアドレスの会員が既にいたら BusinessException を投げる
     *      メッセージ：「このメールアドレスは既に登録されています」
     *   2. Member を new して、フォームの値を詰める
     *   3. memberRepository.save() で保存し、保存した Member を返す
     *   ヒント：BookService#register が参考になります
     */
    @Transactional
    public Member register(MemberForm form) {
        throw new UnsupportedOperationException("課題A-3：未実装です");
    }
}
