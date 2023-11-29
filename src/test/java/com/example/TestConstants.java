package com.example;

import com.example.entities.Person;

@SuppressWarnings("unused")
public class TestConstants {
    public static final String EXPECTED_JSON = """
[{"id":10,"content":"First test message"},{"id":20,"content":"Second test message"},{"id":30,"content":"Third test message"},{"id":40,"content":"Fourth test message"}]""";

    public static final Person BILL = new Person("Bill", "bill@gmail.com");
    public static final Person RUI = new Person("Rui", "rui@gmail.com");
}
