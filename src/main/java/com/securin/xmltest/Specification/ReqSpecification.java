package com.securin.xmltest.Specification;

import com.securin.xmltest.Entity.CpeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ReqSpecification {

    public static Specification<CpeEntity> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpeTitle"), "%" + title.toLowerCase() + "%");
    }

    public static Specification<CpeEntity> withCpe22uri(String cpe22uri) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpe22Uri"), "%" + cpe22uri + "%");
    }

    public static Specification<CpeEntity> withCpe23uri(String cpe23uri) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpe23Uri"), "%" + cpe23uri + "%");
    }

    public static Specification<CpeEntity> deprecatedBefore(LocalDateTime date) {
        return (root, query, builder) -> builder.or(
                builder.lessThan(root.get("cpe22Date"), date),
                builder.lessThan(root.get("cpe23Date"), date)
        );
    }

    public static Specification<CpeEntity> hasAtLeastOneDate() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.isNotNull(root.get("cpe22Date")),
                criteriaBuilder.isNotNull(root.get("cpe23Date"))
        );
    }



}

