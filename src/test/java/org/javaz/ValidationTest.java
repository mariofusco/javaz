package org.javaz;

import org.junit.*;

import java.util.*;
import java.util.function.*;

import static java.util.Arrays.asList;
import static org.javaz.Validation.*;
import static org.javaz.Validator.validate;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    @Test
    public void testValidation() {
        Person person = new Person("mario", 137);
        Validation<List<Object>, Person> validatedPerson = successList(person).flatMap(ValidationTest::validAge).flatMap(ValidationTest::validName);
        System.out.println(validatedPerson);
    }

    @Test
    public void testValidator() {
        Person person = new Person("mario", 137);
        Validation<List<Object>, Person> validatedPerson = validate(person,
                new Validator<Person>(ValidationTest::isValidAge, "Age must be less than 130"),
                new Validator<Person>(ValidationTest::isValidName, "Name must start with an uppercase"));
        System.out.println(validatedPerson);
    }

    @Test
    public void testValidatorLambda() {
        Person person = new Person("mario", 137);
        Validation<List<Object>, Person> validatedPerson = validate(person,
                new Validator<Person>(p -> p.getAge() < 130, "Age must be less than 130"),
                new Validator<Person>(p -> Character.isUpperCase(p.getName().charAt(0)), "Name must start with an uppercase"));
        System.out.println(validatedPerson);
    }

    public static Validation<?, Person> validAge(Person p) {
        return isValidAge(p) ? success(p) : failure(p, "Age must be less than 130");
    }

    public static boolean isValidAge(Person p) {
        return p.getAge() < 130;
    }

    public static Validation<?, Person> validName(Person p) {
        return isValidName(p) ? success(p) :  failure(p, "Name must start with an uppercase");
    }

    public static boolean isValidName(Person p) {
        return Character.isUpperCase(p.getName().charAt(0));
    }

    public static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
}

