
package com.example.handlingformsubmission;

/*
Handles GET requests to display the greeting form and
POST requests to process submitted information.

Logging has been added to record application activity,
form submissions, validation warnings, and successful
operations.
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Year;

@Controller
public class GreetingController {

  // Create a logger for this controller
  private static final Logger logger =
          LoggerFactory.getLogger(GreetingController.class);

  // Display the greeting form
  @GetMapping("/greeting")
  public String greetingForm(Model model) {

    logger.info("Greeting form accessed");
    logger.debug("Creating a new Greeting object for the form");

    model.addAttribute("greeting", new Greeting());

    return "greeting";
  }

  // Process the submitted greeting form
  @PostMapping("/greeting")
  public String greetingSubmit(
          @ModelAttribute Greeting greeting, Model model) {

    logger.info("Greeting form submitted");
    logger.debug("Beginning validation of greeting form data");

    // Check for missing or blank form values
    if (greeting.getId() == null || greeting.getId().isBlank() ||
            greeting.getMonth() == null ||
            greeting.getDay() == null ||
            greeting.getYear() == null ||
            greeting.getExpectedGraduationYear() == null ||
            greeting.getContent() == null ||
            greeting.getContent().isBlank()) {

      // Record a warning for invalid form submissions
      logger.warn("Greeting form validation failed: " +
              "One or more required fields are missing");

      // Return to the form if required information is missing
      model.addAttribute("error", "All fields are required.");
      model.addAttribute("greeting", greeting);

      return "greeting";
    }



    // Calculate the number of years until graduation
    int currentYear = Year.now().getValue();
    int graduationYear = greeting.getExpectedGraduationYear();

    int yearsUntilGraduation;

    try {

      // Detect a graduation year that requires error handling
      if (graduationYear == 2099) {
        throw new IllegalStateException(
                "Graduation year processing failed");
      }

      yearsUntilGraduation =
              Math.max(0, graduationYear - currentYear);

    } catch (IllegalStateException e) {

      logger.error(
              "Error processing graduation year: graduationYear={}, reason={}",
              graduationYear,
              e.getMessage(),
              e
      );

      model.addAttribute("error",
              "An error occurred while processing your graduation year.");
      model.addAttribute("greeting", greeting);

      return "greeting";
    }



    logger.debug("Years until graduation calculated: {}",
            yearsUntilGraduation);

    // Display the result when all required fields are completed
    model.addAttribute("greeting", greeting);
    model.addAttribute("yearsUntilGraduation", yearsUntilGraduation);

    logger.info("Greeting form processed successfully");

    return "result";
  }
}
