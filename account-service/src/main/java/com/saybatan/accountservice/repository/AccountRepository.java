package com.saybatan.accountservice.repository;

import com.saybatan.accountservice.entity.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CassandraRepository<Account, String> {

    @Query("SELECT * FROM accounts WHERE uname = :username")
    Account getByUsername(String username);
}
