package com.devit.quizapp.controller;

import com.devit.quizapp.entity.Question;
import com.devit.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }
    
    @PutMapping("updateQuestion/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return new ResponseEntity<>(questionService.updateQuestion(id, question), HttpStatus.OK);
    }
    
    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return new ResponseEntity<>(questionService.deleteQuestion(id), HttpStatus.OK);
    }
}
