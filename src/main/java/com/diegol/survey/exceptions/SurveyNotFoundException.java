package com.diegol.survey.exceptions;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException(Long id) {
      super("Could not find employee " + id);
    }
  }