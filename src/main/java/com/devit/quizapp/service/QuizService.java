package com.devit.quizapp.service;

import com.devit.quizapp.entity.Question;
import com.devit.quizapp.entity.Quiz;
import com.devit.quizapp.repository.QuestionRepository;
import com.devit.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizRepository quizRepository;

    public String createQuiz(String category, Integer numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return "Quiz created successfully";
    }
}
