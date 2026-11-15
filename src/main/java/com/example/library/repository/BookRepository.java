package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Book;
import com.example.library.entity.BookStatus;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByOrderByIdAsc();

    // TODO 課題A-1：タイトルまたは著者名にキーワードを「部分一致」で含む書籍を、ID順で検索するメソッドを追加する
    //   ヒント：Spring Data JPA は「メソッド名」から SQL を自動で作ってくれます（クエリメソッド）
    //          Containing / Or / OrderBy...Asc を組み合わせてみましょう

    List<Book> findByStatusOrderByIdAsc(BookStatus status);

    boolean existsByIsbn(String isbn);

    boolean existsByIsbnAndIdNot(String isbn, Long id);
}
