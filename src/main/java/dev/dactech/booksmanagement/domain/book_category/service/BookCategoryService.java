package dev.dactech.booksmanagement.domain.book_category.service;

import dev.dactech.booksmanagement.domain.book_category.repository.BookCategoryRepository;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryService {
   @Autowired
   private BookCategoryRepository bookCategoryRepository;

}
