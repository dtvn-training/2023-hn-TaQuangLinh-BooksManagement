package dev.dactech.booksmanagement.domain.book.repository.impl;

import dev.dactech.booksmanagement.domain.book.dto.excel.ExportInventoryExcel;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.repository.BookRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> getInventoryBook() {
        StringBuilder sql = new StringBuilder("select books.id as id, title, book_category.name as category, authors,(quantity - a.count) as quantity, image, date(date_added) " +
                "from " +
                "books, book_category, " +
                "(select book_id as id, count(book_id) as count from book_student, books where " +
                "books.id = book_student.book_id and end_date is null " +
                "group by book_id order by book_id) " +
                "as a " +
                "where " +
                "book_category.id = books.category_id and " +
                "a.id = books.id;");

        Query query = entityManager.createNativeQuery(sql.toString());

        List<Object[]> result = query.getResultList();

        return result;
    }

    @Override
    public List<Object[]> getOverdue(){
        StringBuilder sql = new StringBuilder("" +
                "select book_student.id as id, code, books.id, title, start_date, DATE(DATE_ADD(start_date, interval books.limit_date day)) as expired_date " +
                "from " +
                "    book_student, students, books " +
                "where " +
                "    book_student.book_id = books.id and " +
                "book_student.student_id = students.id and " +
                "end_date is null " +
                "having " +
                "expired_date <= DATE(NOW())");

        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> result = query.getResultList();

        return result;
    }
}
