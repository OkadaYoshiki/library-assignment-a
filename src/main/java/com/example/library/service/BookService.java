package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.entity.Book;
import com.example.library.entity.BookStatus;
import com.example.library.exception.BusinessException;
import com.example.library.form.BookForm;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAllByOrderByIdAsc();
    }

    /**
     * 機能②：書籍検索。キーワードが空なら全件を返す
     *
     * TODO 課題A-1：キーワード検索を実装する
     *   ・keyword が null または空白だけなら findAll() の結果を返す（今はいつも全件を返している）
     *   ・それ以外は前後の空白（全角スペースも）を取り除き（strip）、BookRepository に追加した検索メソッドを呼ぶ
     */
    public List<Book> search(String keyword) {
        return findAll();
    }

    public List<Book> findAvailable() {
        return bookRepository.findByStatusOrderByIdAsc(BookStatus.AVAILABLE);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("書籍が見つかりません（ID: " + id + "）"));
    }

    /** 機能③：書籍登録 */
    @Transactional
    public Book register(BookForm form) {
        if (bookRepository.existsByIsbn(form.getIsbn())) {
            throw new BusinessException("このISBNは既に登録されています");
        }
        Book book = new Book();
        copy(form, book);
        book.setStatus(BookStatus.AVAILABLE);
        return bookRepository.save(book);
    }

    /** 機能④：書籍編集 */
    @Transactional
    public Book update(Long id, BookForm form) {
        Book book = findById(id);
        if (bookRepository.existsByIsbnAndIdNot(form.getIsbn(), id)) {
            throw new BusinessException("このISBNは既に他の書籍で登録されています");
        }
        copy(form, book);
        return bookRepository.save(book);
    }

    /**
     * 機能⑤：書籍削除。貸出履歴がある書籍は削除できない
     *
     * TODO 課題A-2：書籍削除を実装する
     *   1. findById(id) で書籍を取得する（無ければ BusinessException が投げられる）
     *   2. 貸出履歴があれば BusinessException を投げる
     *      メッセージ例：「（タイトル）」は貸出履歴があるため削除できません
     *      （LoanRepository に追加したメソッドを使う）
     *   3. bookRepository.delete() で削除する
     */
    @Transactional
    public void delete(Long id) {
        throw new UnsupportedOperationException("課題A-2：未実装です");
    }

    public BookForm toForm(Book book) {
        BookForm form = new BookForm();
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());
        form.setPublishedYear(book.getPublishedYear());
        return form;
    }

    private void copy(BookForm form, Book book) {
        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        book.setPublishedYear(form.getPublishedYear());
    }
}
