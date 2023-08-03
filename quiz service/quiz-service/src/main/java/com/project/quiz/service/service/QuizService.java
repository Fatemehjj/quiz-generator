package com.project.quiz.service.service;
import com.project.quiz.service.dao.QuizDao;
import com.project.quiz.service.feign.QuizConnection;
import com.project.quiz.service.model.QuestionWrapper;
import com.project.quiz.service.model.Quiz;
import com.project.quiz.service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizConnection quizConnection;

    public ResponseEntity<String> createQuiz(String category, int numOfQ, String title) {
        try {
            List<Integer> questions = quizConnection.getQuestionIDsForQuiz(category, numOfQ).getBody();
             Quiz quiz = new Quiz();
             quiz.setTitle(title);
             quiz.setQuestionIds(questions);

             quizDao.save(quiz);
             
            return new ResponseEntity<>("success", HttpStatus.CREATED);
       }catch (Exception e){
            e.printStackTrace();
       }
        return new ResponseEntity<>("can't create the quiz", HttpStatus.BAD_REQUEST);
     }
    public ResponseEntity<String> createByLevel(String difficultyLevel, String title,Integer numOfQues) {

       try {
          List<Integer> getQuesByLevel = quizConnection.getQuestionsByLevel(difficultyLevel, numOfQues).getBody();
           Quiz quiz = new Quiz();
           quiz.setTitle(title);
           quiz.setQuestionIds(getQuesByLevel);

           quizDao.save(quiz);
           return new ResponseEntity<>("success", HttpStatus.CREATED);
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>("can't create the quiz", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

       try {
           Optional<Quiz> quiz = quizDao.findById(id);
           List<Integer> getIds =  quiz.get().getQuestionIds();
           ResponseEntity<List<QuestionWrapper>> qes = quizConnection.getQuestionsFromId(getIds);

           return qes;
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Integer> getScore(Integer id, List<Response> responses) {

        try {
            ResponseEntity<Integer> score = quizConnection.getScore(responses);
           return score;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByTitle(String name) {
        try {
            Quiz quiz = quizDao.findByTitle(name);
            List<Integer> getIds =  quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> qes = quizConnection.getQuestionsFromId(getIds);

            return qes;

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
