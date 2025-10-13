package com.devit.quizapp.service;

import com.devit.quizapp.entity.Question;
import com.devit.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category.toLowerCase());
    }

    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "Question added successfully";
    }

    public String deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
        return "Question deleted successfully";
    }

    public String updateQuestion(Integer id, Question question) {
        question.setId(id);
        questionRepository.save(question);
        return "Question updated successfully";
    }
}
