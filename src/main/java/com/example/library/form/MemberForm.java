package com.example.library.form;

/**
 * 会員登録フォーム
 *
 * TODO 課題A-3：入力チェック用のアノテーションを付ける
 *   ・氏名：必須、50文字以内
 *   ・メールアドレス：必須、メール形式、100文字以内
 *   ヒント：BookForm.java を参考にしましょう（@NotBlank / @Size / @Email）
 */
public class MemberForm {

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
