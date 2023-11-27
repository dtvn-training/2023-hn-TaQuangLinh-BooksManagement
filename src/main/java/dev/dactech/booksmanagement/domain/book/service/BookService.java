package dev.dactech.booksmanagement.domain.book.service;

import dev.dactech.booksmanagement.domain.book.dto.excel.ExportInventoryExcel;
import dev.dactech.booksmanagement.domain.book.dto.excel.ExportOverdueExcel;
import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.request.BookUpdateReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BookDetailsRes;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.domain.book.dto.response.ExportRes;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.repository.BookRepository;
import dev.dactech.booksmanagement.domain.book_category.entity.BookCategory;
import dev.dactech.booksmanagement.domain.book_category.repository.BookCategoryRepository;
import dev.dactech.booksmanagement.domain.librarian.entity.Librarian;
import dev.dactech.booksmanagement.domain.librarian.repository.LibrarianRepository;
import dev.dactech.booksmanagement.infrastructure.excel.Excel;
import dev.dactech.booksmanagement.infrastructure.excel.Header;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import dev.dactech.booksmanagement.infrastructure.utilies.Utility;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static dev.dactech.booksmanagement.infrastructure.excel.Excel.*;
import static dev.dactech.booksmanagement.infrastructure.utilies.Utility.*;

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
        Librarian librarian = librarianRepository.getById(req.getLibrarianId());
        Book book = Book.builder()
                .title(req.getTitle())
                .authors(req.getAuthors())
                .publishingDate(req.getPublishingDate())
                .quantity(req.getQuantity())
                .dateAdded(LocalDateTime.now())
                .librarian(librarian)
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

    public List<BooksRes> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy, Integer page, Integer size) {
        if (authors != null)authors = '%'+authors+'%';
        if (title != null)title = '%'+title+'%';
        LocalDate date = null;
        if (dateAdded != null)date = Utility.formatToDate(dateAdded, null);
        if (page == null)page = 0;
        if (size == null)size = 10;

        Pageable pageable = paginationAndSorting(page, size, sortBy);

        try {
            List<Book> books = bookRepository.getAll(title, categoryId, authors, date, librarianId, deleted, pageable);

            List<BooksRes> response = new ArrayList<>();
            for (Book item : books){
                BooksRes booksRes = BooksRes.builder()
                        .id(item.getId())
                        .title(item.getTitle())
                        .authors(item.getAuthors())
                        .category(item.getCategory().getName())
                        .image(item.getImage())
                        .quantity(item.getQuantity())
                        .build();
                response.add(booksRes);
            }
            return response;
        }catch (Exception e){
            throw new ApiException(MessageCode.FAIL);
        }
    }

    public BookDetailsRes getBookDetails(Integer id) {
        Optional<Book> bookOptional;
        try{
            bookOptional = bookRepository.findById(id);
        }catch (Exception e){
            throw new ApiException(MessageCode.FAIL);
        }
        if(!bookOptional.isPresent()){
            throw new ApiException(MessageCode.NOT_FOUND_ID, "id = " + id);
        }
        else {
            Book book = bookOptional.get();
            return BookDetailsRes.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .category(book.getCategory().getName())
                    .authors(book.getAuthors())
                    .publishingDate(formatDateTimeToString(book.getPublishingDate(), null))
                    .quantity(book.getQuantity())
                    .librarian(book.getLibrarian().getName())
                    .image(book.getImage())
                    .limitDate(book.getLimitDate())
                    .deletedAt(formatDateTimeToString(book.getDeletedAt(), null))
                    .dateAdded(formatDateTimeToString(book.getDateAdded().toLocalDate(), null))
                    .build();
        }
    }

    public MessageCode updateBook(BookUpdateReq req) {
        Optional<Book> bookOptional = bookRepository.findById(req.getId());
        if (!bookOptional.isPresent()){
            throw new ApiException(MessageCode.NOT_FOUND_ID);
        }else{
            Book book = bookOptional.get();
            if (req.getTitle() != null) {
                book.setTitle(req.getTitle());
            }
            if (req.getCategoryId() != null){
                BookCategory bookCategory = bookCategoryRepository.findById(req.getCategoryId()).orElse(null);
                if (bookCategory == null)throw new ApiException(MessageCode.NOT_FOUND_ID);
                book.setCategory(bookCategory);
            }
            if (req.getAuthors() != null){
                book.setAuthors(req.getAuthors());
            }
            if (req.getPublishingDate() != null){
                book.setPublishingDate(req.getPublishingDate());
            }
            if (req.getQuantity() != null){
                book.setQuantity(req.getQuantity());
            }
            if (req.getLibrarianId() != null){
                Librarian librarian = librarianRepository.findById(req.getLibrarianId()).orElse(null);
                if (librarian == null) throw new ApiException(MessageCode.NOT_FOUND_ID);
                book.setLibrarian(librarian);
            }
            if (req.getLimitDate() != null){
                book.setLimitDate(req.getLimitDate());
            }
            if (req.getImage() != null){
                book.setImage(req.getImage());
            }
            try {
                bookRepository.save(book);
                return MessageCode.SUCCESS;
            }catch (Exception e){
                throw new ApiException(MessageCode.FAIL);
            }
        }
    }

    public MessageCode deleteBook(Integer id) {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book == null) throw new ApiException(MessageCode.NOT_FOUND_ID);
            bookRepository.delete(book);
            return MessageCode.SUCCESS;
        }catch (Exception e){
            throw new ApiException(MessageCode.FAIL);
        }
    }

    public ExportRes exportInventory() throws IOException {
        List<Object[]> bookInventoryList = bookRepository.getInventoryBook();
        List<ExportInventoryExcel> data = new ArrayList<>();
        for (Object[] item : bookInventoryList){
            ExportInventoryExcel dataItem = ExportInventoryExcel.builder()
                    .id((int)item[0])
                    .title(String.valueOf(item[1]))
                    .category(String.valueOf(item[2]))
                    .authors(String.valueOf(item[3]))
                    .quantity((long)item[4])
                    .image(String.valueOf(item[5]))
                    .dateAdded(String.valueOf(item[6]))
                    .build();
            data.add(dataItem);
        }

        Header header = new Header();
        header.getTitle().put(0, "ID");
        header.getTitle().put(1, "Title");
        header.getTitle().put(2, "Category");
        header.getTitle().put(3, "Authors");
        header.getTitle().put(4, "Quantity");
        header.getTitle().put(5, "Date added");
        header.getTitle().put(6, "Image");

        try {
            String outputPath = writeInventoryBookToExcel(header, data);
            return ExportRes.builder()
                    .link(outputPath)
                    .build();
        }catch (Exception e){
            throw new ApiException(MessageCode.ERROR_WRITE_EXCEL_FILE);
        }
    }

    public ExportRes exportOverdue() throws IOException {
        List<Object[]> overdueList = bookRepository.getOverdue();
        List<ExportOverdueExcel> data = new ArrayList<>();
        for (Object[] item : overdueList){
            ExportOverdueExcel dataItem = ExportOverdueExcel.builder()
                    .id((long)item[0])
                    .studentCode(String.valueOf(item[1]))
                    .bookId((int)item[2])
                    .bookName(String.valueOf(item[3]))
                    .startTime(formatToDateTime(String.valueOf(item[4]), "yyyy-MM-dd HH:mm:ss.S"))
                    .expiredDate(formatToDate(String.valueOf(item[5]), "yyyy-MM-dd"))
                            .build();
            dataItem.setNumOfDayOverdue(ChronoUnit.DAYS.between(dataItem.getStartTime().toLocalDate(), dataItem.getExpiredDate()));
            data.add(dataItem);
        }

        Header header = new Header();
        header.getTitle().put(0, "ID");
        header.getTitle().put(1, "Student code");
        header.getTitle().put(2, "Book id");
        header.getTitle().put(3, "Book Name");
        header.getTitle().put(4, "Start time");
        header.getTitle().put(5, "Expired date");
        header.getTitle().put(6, "Number of day overdue");

        try{
            String outputPath = writeOverdueToExcel(header, data);
            return ExportRes.builder()
                    .link(outputPath)
                    .build();
        }catch (Exception e){
            throw new ApiException(MessageCode.ERROR_WRITE_EXCEL_FILE);
        }
    }
}
