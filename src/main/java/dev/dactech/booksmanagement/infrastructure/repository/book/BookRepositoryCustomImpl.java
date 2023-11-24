package dev.dactech.booksmanagement.infrastructure.repository.book;

import dev.dactech.booksmanagement.domain.book.repository.BookRepositoryCustom;
import dev.dactech.booksmanagement.infrastructure.dto.response.BooksResDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.dactech.booksmanagement.infrastructure.utilies.Utility.formatToDate;
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public List<BooksResDTO> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy) {
//        StringBuilder sql = new StringBuilder("select books.id as id, title, book_category.name, authors, quantity, image from books, book_category where books.category_id = book_category.id ");
//        Map<String, Object> params = new HashMap<>();
//
//        if (title != null){
//            sql.append("and title LIKE :title ");
//            params.put("title", "%"+title+"%s");
//        }
//        if (categoryId != null){
//            sql.append("and category_id = :category_id ");
//            params.put("category_id", categoryId);
//        }
//        if (authors != null){
//            sql.append("and authors LIKE :authors ");
//            params.put("authors", "%"+authors+"%");
//        }
//        if (dateAdded != null){
//            sql.append("and date_added = :date_added ");
//            params.put("date_added", formatToDate(dateAdded, null));
//        }
//        if (librarianId != null){
//            sql.append("and librarian_id = :librarian_id ");
//            params.put("librarian_id", librarianId);
//        }
//        if (deleted != null){
//            if (deleted == 0){
//                sql.append("and deleted_at is null ");
//            }else if (deleted == 1){
//                sql.append("and deleted_at is not null ");
//            }
//        }
//        if (sortBy != null){
//            String type = sortBy.substring(sortBy.charAt(':'));
//            sql.append("order by date_added :type");
//            params.put(":type", type);
//        }
//        Query query = entityManager.createQuery(sql.toString(), BooksResDTO.class);
//        params.forEach(query::setParameter);
//
//        return query.getResultList();
//    }
}
