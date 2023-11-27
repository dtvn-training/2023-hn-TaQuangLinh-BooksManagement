package dev.dactech.booksmanagement.domain.book.service;

import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.service.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification implements Specification<Book> {
    private SearchCriteria criteria;
    public BookSpecification(SearchCriteria criteria){
        this.criteria = criteria;
    }
    public BookSpecification(String key, String operation, Object value){
        this.criteria = new SearchCriteria(key, operation, value);
    }
    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperation()){
            case ":":
                if (criteria.getValue().getClass() == String.class){
                    return criteriaBuilder.like(root.get(criteria.getKey()), criteria.getValue().toString());
                }else{
                    return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            case "<":
                return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case ">":
                return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case "<=":
                return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case ">=":
                return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
//            case "is":
//                return criteriaBuilder.isMember(root.get(criteria.getKey()), criteria.getValue().toString());
//            case "isnot":
//                return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            default:
                throw new ApiException();

        }
    }
}
