package com.nexdew.wallet.entity;

import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@Data
public abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column
    private LocalDate created_date=LocalDate.now() ;

    @Column
    private LocalDate updated_date= LocalDate.now();

    @Column
    private Boolean status = true;

//    @Column
//    private T created_by;
//
//    @Column
//    private T updated_by;
}
