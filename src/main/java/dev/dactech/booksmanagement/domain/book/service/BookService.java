package dev.dactech.booksmanagement.domain.book.service;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.repository.BookRepository;
import dev.dactech.booksmanagement.domain.book_category.entity.BookCategory;
import dev.dactech.booksmanagement.domain.book_category.repository.BookCategoryRepository;
import dev.dactech.booksmanagement.domain.librarian.entity.Librarian;
import dev.dactech.booksmanagement.domain.librarian.repository.LibrarianRepository;
import dev.dactech.booksmanagement.infrastructure.dto.response.BooksResDTO;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Transactional(rollbackOn = {Exception.class})
    public MessageCode add(BookCreationReq req) throws ApiException {
        if (req.getCategoryId() == null && req.getCategoryName() == null) return MessageCode.MISSING_CATEGORY_FIELD;
        if (req.getQuantity() == null) return MessageCode.MISSING_QUANTITY_FIELD;
        if (req.getTitle() == null) return MessageCode.MISSING_TITLE_FIELD;
        if (req.getLibrarianId() == null) return MessageCode.MISSING_LIBRARIAN_FIELD;
        if (req.getLimitDate() == null) return MessageCode.MISSING_LIMIT_DATE_FIELD;

        BookCategory bookCategory;
        if (req.getCategoryId() == null){
            bookCategory = BookCategory.builder().name(req.getCategoryName()).build();
            bookCategory = bookCategoryRepository.save(bookCategory);
        }else{
            bookCategory = bookCategoryRepository.getById(req.getCategoryId());
        }
        Book book = Book.builder()
                .title(req.getTitle())
                .authors(req.getAuthors())
                .publishingDate(req.getPublishingDate())
                .quantity(req.getQuantity())
                .dateAdded(LocalDateTime.now())
                .librarian(librarianRepository.getById(req.getLibrarianId()))
                .category(bookCategory)
                .image(req.getImage())
                .limitDate(req.getLimitDate())
                .build();
        try {
            bookRepository.save(book);
            return MessageCode.SUCCESS;
        }catch (Exception e){
            throw new ApiException(MessageCode.FAIL);
        }
    }
    public List<Book> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy) {
//        List<BooksResDTO> booksResDTO = bookRepository.getAll(title, categoryId, authors, dateAdded, librarianId,deleted, sortBy);
//        System.out.println(booksResDTO);
        return null;
    }
}
