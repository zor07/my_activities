package com.javarush.myactivities.repositorytests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Sql({"/test-schema.sql"})
@SpringBootTest
public abstract class BasicRepositoryTest {

    public abstract void createTest();
    public abstract void readTest();
    public abstract void updateTest();
    public abstract void deleteTest();

}
