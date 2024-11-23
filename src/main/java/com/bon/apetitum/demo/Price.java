package com.bon.apetitum.demo;

public class Price {
    private PersonPrice students;
    private PersonPrice staff;
    private PersonPrice guests;

    public Price(PersonPrice students, PersonPrice staff, PersonPrice guests) {
        this.students = students;
        this.staff = staff;
        this.guests = guests;
    }

    public PersonPrice getStudents() {
        return students;
    }

    public void setStudents(PersonPrice students) {
        this.students = students;
    }

    public PersonPrice getStaff() {
        return staff;
    }

    public void setStaff(PersonPrice staff) {
        this.staff = staff;
    }

    public PersonPrice getGuests() {
        return guests;
    }

    public void setGuests(PersonPrice guests) {
        this.guests = guests;
    }

    public Price() {
    }
}
