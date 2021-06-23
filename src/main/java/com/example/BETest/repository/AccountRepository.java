package com.example.BETest.repository;

import com.example.BETest.object.AccountCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountCredentials,String> {
    AccountCredentials findAccountCredentialsByUsername(String username);
}
