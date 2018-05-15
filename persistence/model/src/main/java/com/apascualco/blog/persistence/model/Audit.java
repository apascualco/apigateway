package com.apascualco.blog.persistence.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@MappedSuperclass
public abstract class Audit {

    @NotNull
    @Column(name = "audit_user_update", nullable = false)
    private String auditUser;

    @NotNull
    @Column(name = "audit_last_update", nullable = false)
    private Calendar auditLastUpdate;

}
