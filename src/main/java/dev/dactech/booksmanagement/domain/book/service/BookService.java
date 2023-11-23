package dev.dactech.booksmanagement.domain.book.service;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.repository.BookRepository;
import dev.dactech.booksmanagement.domain.book_category.entity.BookCategory;
import dev.dactech.booksmanagement.domain.book_category.repository.BookCategoryRepository;
import dev.dactech.booksmanagement.infrastructure.dto.response.BooksResDTO;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Transactional(rollbackOn = {Exception.class})
    public MessageCode add(BookCreationReq req) throws ApiException {
        if (req.getCategoryId() == null && req.getCategoryName() == null) return MessageCode.MISSING_CATEGORY_FIELD;
        if (req.getQuantity() == null) return MessageCode.MISSING_QUANTITY_FIELD;
        if (req.getTitle() == null) return MessageCode.MISSING_TITLE_FIELD;
        if (req.getLibrarianId() == null) return MessageCode.MISSING_LIBRARIAN_FIELD;
        if (req.getLimitDate() == null) return MessageCode.MISSING_LIMIT_DATE_FIELD;


        if (req.getCategoryId() == null) {
            BookCategory bookCategory = BookCategory.builder()
                    .name(req.getCategoryName())
                    .build();
            try {
                bookCategory = bookCategoryRepository.save(bookCategory);
                req.setCategoryId(bookCategory.getId());
            }catch (Exception e){
                throw new ApiException(MessageCode.FAIL);
            }
        }

        Book book = Book.builder()
                .title(req.getTitle())
                .categoryId(req.getCategoryId())
                .authors(req.getAuthors())
                .publishingDate(req.getPublishingDate())
                .quantity(req.getQuantity())
                .librarianId(req.getLibrarianId())
                .image(req.getImage())
                .limitDate(req.getLimitDate())
                .build();
        try {
            bookRepository.save(book);
            return MessageCode.SUCCESS;
        } catch (Exception e) {
            throw new ApiException(MessageCode.ERROR_BOOK_CREATION);
        }
    }
    public List<Book> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy) {
        List<BooksResDTO> booksResDTO = bookRepository.getAll(title, categoryId, authors, dateAdded, librarianId,deleted, sortBy);
        System.out.println(booksResDTO);
        return null;
    }
}
