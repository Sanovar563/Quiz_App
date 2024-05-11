package com.app.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.quizapp.dao.QuestionDao;
import com.app.quizapp.entity.Question;

@Service
public class QuestionService {
@Autowired QuestionDao questionDao;


	public ResponseEntity<List<Question>> getAllQuestions() {
		try {		
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }
	


	public ResponseEntity<String> addQuestion(Question question) {
	questionDao.save(question);
	return new ResponseEntity<>( "success", HttpStatus.CREATED);		
	}
	
    public String updateQuestion(int id, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setQuestionTitle(updatedQuestion.getQuestionTitle());
            question.setOption1(updatedQuestion.getOption1());
            question.setOption2(updatedQuestion.getOption2());
            question.setOption3(updatedQuestion.getOption3());
            question.setOption4(updatedQuestion.getOption4());
            question.setRightAnswer(updatedQuestion.getRightAnswer());
            question.setDifficultylevel(updatedQuestion.getDifficultylevel());
            question.setCategory(updatedQuestion.getCategory());
            questionDao.save(question);
            return "Question updated successfully";
        } else {
            return "Question not found with id: " + id;
        }
    }

    // Delete a question
    public String deleteQuestion(int id) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            questionDao.deleteById(id);
            return "Question deleted successfully";
        } else {
            return "Question not found with id: " + id;
        }
    }

}
