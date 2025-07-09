package com.securin.xmltest.Repository;

import com.securin.xmltest.Entity.CpeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpeRepository extends JpaRepository<CpeEntity, Integer> {
}
