package com.app.quizapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.quizapp.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question>findByCategory(String category);

	Optional<Question> findById(int id);

	@Query(value= "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT:numQ", nativeQuery= true)
	List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);
}