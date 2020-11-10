package com.diegol.survey.repositories;

import java.util.List;

import com.diegol.survey.entities.Survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("SELECT u FROM tm_survey u WHERE u.userId = :userId")
    List<Survey> findUserSurvey(@Param("userId") String userId);

}

