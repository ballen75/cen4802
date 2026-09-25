package com.example.handlingformsubmission;

import java.time.Year;

/*
This class stores the data entered by the user in the form.
It will store student ID, date, expected graduation year, and the message.
It also determines the student's graduation status based on the
expected graduation year.

Spring Boot will automatically bind the form fields to the object.
*/

public class Greeting {

    private String id;

    private Integer month;
    private Integer day;
    private Integer year;

    private Integer expectedGraduationYear;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getExpectedGraduationYear() {
        return expectedGraduationYear;
    }

    public void setExpectedGraduationYear(Integer expectedGraduationYear) {
        this.expectedGraduationYear = expectedGraduationYear;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
     * Determines graduation status by comparing the expected
     * graduation year with the current year.
     */
    public String getGraduationStatus() {

        if (expectedGraduationYear == null) {
            return "Unknown";
        }

        int currentYear = Year.now().getValue();

        if (expectedGraduationYear < currentYear) {
            return "Graduated";
        } else if (expectedGraduationYear == currentYear) {
            return "Graduating This Year";
        } else {
            return "Upcoming Graduation";
        }
    }
}