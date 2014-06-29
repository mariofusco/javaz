package org.javaz.monad;

import java.util.*;

public class OptionalMain {

    public static void main(String... args) {

    }

    public static class Person {
        private Optional<Car> car;
        public Optional<Car> getCar() { return car; }
    }

    public static class Car {
        private Optional<Insurance> insurance;
        public Optional<Insurance> getInsurance() { return insurance; }
    }

    public static class Insurance {
        private String name;
        public String getName() { return name; }
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
    }
}
