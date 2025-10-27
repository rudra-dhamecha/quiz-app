package com.devit.quizapp.service;

import com.devit.quizapp.dto.QuestionDTO;
import com.devit.quizapp.entity.Question;
import com.devit.quizapp.entity.Quiz;
import com.devit.quizapp.repository.QuestionRepository;
import com.devit.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<QuestionDTO> getQuiz(Integer id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questions = quiz.getQuestions();

        return questions.stream()
                .map(q -> new QuestionDTO(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()))
                .collect(Collectors.toList());
    }

    public String submitQuiz(Integer id, List<Question> questionsFromUser) {

        if (questionsFromUser.get(0).getRightAnswer() == null) {
            throw new IllegalArgumentException("Wrong Quiz submit format");
        }

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questionsFromDB = quiz.getQuestions();

        int score = 0;

        for (Question q : questionsFromUser){
            String userAnswer = q.getRightAnswer();
            String dbAnswer = questionsFromDB.stream()
                    .filter(q1 -> q1.getId().equals(q.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Question not found"))
                    .getRightAnswer();
            if (userAnswer.equals(dbAnswer)) {
                score++;
            }
        }

        return "Your score is " + score + " out of " + questionsFromUser.size();
    }
}
