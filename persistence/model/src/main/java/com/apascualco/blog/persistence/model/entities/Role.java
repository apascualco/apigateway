package com.apascualco.blog.persistence.model.entities;

import com.apascualco.blog.persistence.model.Audit;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="role")
public class Role extends Audit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;

}
