package com.diegol.survey.controller;

import java.util.List;

import com.diegol.survey.entities.Survey;
import com.diegol.survey.exceptions.SurveyNotFoundException;
import com.diegol.survey.repositories.SurveyRepository;

import org.hibernate.internal.util.StringHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("api/surveys")
class SurveyController {

  private final SurveyRepository repository;

  public SurveyController(SurveyRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  List<Survey> all(@RequestParam(required = false) String userId) {

    if(StringHelper.isBlank(userId)){
        return repository.findAll();
    } else {
        return repository.findUserSurvey(userId);
    }
    
  }

  @PostMapping("")
  Survey newEmployee(@RequestBody Survey newSurvey) {
    return repository.save(newSurvey);
  }

  @GetMapping("/{id}")
  Survey one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new SurveyNotFoundException(id));
  }

  @PutMapping("/{id}")
  Survey replaceEmployee(@RequestBody Survey newEmployee, @PathVariable Long id) {

    return repository.findById(id)
      .map(employee -> {
        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}