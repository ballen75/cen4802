package com.example.handlingformsubmission;

/*
This class stores the data entered by the user in the form. It will store student ID, date, and the message.
Spring boot will automatically bind the form fields to the object.
This method uses a Model object to expose a new Greeting to the view template.
The implementation of the method body performs server-side rendering of the HTML by converting the view name greeting into a template to render.
 */

public class Greeting {

    private String id; //Student ID entered by the user

    //Month, day, and year values entered by the user.
    private Integer month;
    private Integer day;
    private Integer year;

    //Message the user typed into the form
    private String content;

    // Getters and setters
    //This will allow Spring and Thymeleaf to access and update the fields.
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Integer getDay() { return day; }
    public void setDay(Integer day) { this.day = day; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
