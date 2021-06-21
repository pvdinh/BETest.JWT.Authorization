package com.example.BETest.repository;

import com.example.BETest.object.MajorsClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MajorsClassRepository extends MongoRepository<MajorsClass,String> {
}
