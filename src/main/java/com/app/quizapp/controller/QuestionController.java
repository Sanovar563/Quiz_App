package com.app.quizapp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quizapp.entity.Question;
import com.app.quizapp.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired QuestionService questionService;
	
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity< List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		
		return questionService.addQuestion(question);
	}
	@PutMapping("/{id}")
    public String updateQuestion(@PathVariable int id, @RequestBody Question updatedQuestion) {
        String result = questionService.updateQuestion(id, updatedQuestion);
        return result;
    }

    // Endpoint to delete a question by ID
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable int id) {
        String result = questionService.deleteQuestion(id);
        return result;
    }
}
