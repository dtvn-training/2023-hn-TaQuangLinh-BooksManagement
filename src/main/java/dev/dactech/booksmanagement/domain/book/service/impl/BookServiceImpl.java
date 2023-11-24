package dev.dactech.booksmanagement.domain.book.service.impl;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.repository.BookRepository;
import dev.dactech.booksmanagement.domain.book.service.BookService;
import dev.dactech.booksmanagement.domain.book.service.BookSpecification;
import dev.dactech.booksmanagement.domain.book_category.entity.BookCategory;
import dev.dactech.booksmanagement.domain.book_category.repository.BookCategoryRepository;
import dev.dactech.booksmanagement.domain.librarian.repository.LibrarianRepository;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
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

    @Override
    public List<BooksRes> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy) {
        bookRepository.findByTitleAndCategoryAndAuthorsAndDateAdded(title,categoryId, authors, dateAdded);




        Specification<Book> specification = null;
        if (title != null){
            BookSpecification bookSpecification = new BookSpecification("title", ":", title);
            if (specification == null)specification = Specification.where(bookSpecification);
            else specification = specification.and(bookSpecification);
        }
        if (categoryId != null){
            BookSpecification bookSpecification = new BookSpecification("category", ":", categoryId);
            if (specification == null)specification = Specification.where(bookSpecification);
            else specification = specification.and(bookSpecification);
        }
        if (authors != null){
            BookSpecification bookSpecification = new BookSpecification("authors", ":", "%"+authors+"%");
            if (specification == null)specification = Specification.where(bookSpecification);
            else specification = specification.and(bookSpecification);
        }
        if (dateAdded != null){
            BookSpecification bookSpecification = new BookSpecification("dateAdded", ":", dateAdded);
            if (specification == null)specification = Specification.where(bookSpecification);
            else specification = specification.and(bookSpecification);
        }
        if (librarianId != null){
            BookSpecification bookSpecification = new BookSpecification("librarian", ":", "%"+authors+"%");
            if (specification == null)specification = Specification.where(bookSpecification);
            else specification = specification.and(bookSpecification);
        }
        if (deleted == 0){
            BookSpecification bookSpecification = new BookSpecification("deletedAt", ":", "%"+authors+"%");
            if (specification == null)specification = Specification.where(bookSpecification);
            else specification = specification.and(bookSpecification);
        }else if (deleted == 1){

        }

        return null;
    }
}
