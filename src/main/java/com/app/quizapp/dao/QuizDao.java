package com.app.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.quizapp.entity.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
