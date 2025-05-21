package com.saybatan.accountservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(value = "accounts")
public class Account implements Serializable {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Column(value = "uname")
    private String username;

    @Column(value = "email")
    private String email;

    @Column(value = "pwd")
    private String password;

    @Column(value = "created_at")
    private Date createdAt = new Date();

    @Column(value = "is_active")
    private Boolean isActive = true;

}
