package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.library.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /** 機能⑥：会員一覧 */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members/list";
    }

    // TODO 課題A-3：会員登録の画面表示と登録処理を追加する
    //
    //   (1) GET  /members/new  … 空の MemberForm を "memberForm" という名前で Model に入れて
    //                            members/form.html を表示する
    //   (2) POST /members/new  … 入力チェック（@Validated + BindingResult）
    //                            ・エラーがあれば members/form.html を再表示
    //                            ・memberService.register() で登録
    //                              BusinessException が出たら "errorMessage" を Model に入れて再表示
    //                            ・成功したら「会員を登録しました」をフラッシュメッセージ（"message"）に入れて
    //                              /members にリダイレクト
    //
    //   ヒント：BookController の newForm / create がほぼ同じ形です
}
