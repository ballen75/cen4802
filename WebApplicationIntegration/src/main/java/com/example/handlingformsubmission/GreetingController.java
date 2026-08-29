package com.example.handlingformsubmission;

/*
Handles the GET requests to greeting by returning the name of the view.
This method will display the form page when the user visits the URL.
HTTP requests are handled by the controller.

The POST method also validates the submitted form to make sure
that all required fields contain a value before displaying the result.
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

  @GetMapping("/greeting")
  public String greetingForm(Model model) {
    model.addAttribute("greeting", new Greeting());
    return "greeting";
  }

  @PostMapping("/greeting")
  public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {

    // Check for missing or blank form values
    if (greeting.getId() == null || greeting.getId().isBlank() ||
            greeting.getMonth() == null ||
            greeting.getDay() == null ||
            greeting.getYear() == null ||
            greeting.getContent() == null || greeting.getContent().isBlank()) {

      // Return to the form if required information is missing
      model.addAttribute("error", "All fields are required.");
      model.addAttribute("greeting", greeting);
      return "greeting";
    }

    // Display the result when all required fields are completed
    model.addAttribute("greeting", greeting);
    return "result";
  }
}