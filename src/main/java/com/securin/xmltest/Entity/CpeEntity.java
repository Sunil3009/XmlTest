package com.securin.xmltest.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CpeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String cpeTitle;

    @Column(columnDefinition = "TEXT")
    private String cpe22Uri;
    @Column(columnDefinition = "TEXT")
    private String cpe23Uri;
    @Column(columnDefinition = "TEXT")
    private String referenceLink;
    private LocalDateTime cpe23Date;
    private LocalDateTime cpe22Date;
}
