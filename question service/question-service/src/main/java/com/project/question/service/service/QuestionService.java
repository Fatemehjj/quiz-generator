package com.project.question.service.service;


import com.project.question.service.dao.QuestionDao;
import com.project.question.service.model.Question;
import com.project.question.service.model.QuestionWrapper;
import com.project.question.service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Component
public class QuestionService {
   @Autowired
   QuestionDao questionDao;

    public ResponseEntity<List<Question>>getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestion(Question question) {
         try {
             questionDao.save(question);
             return new ResponseEntity<>("success", HttpStatus.CREATED);
         }catch (Exception e){
             e.printStackTrace();
         }
         return new ResponseEntity<>("Bad Request !", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionByDifficultyLevel(String difficultyLevel) {
        try {
            return new ResponseEntity<>(questionDao.findByDifficultyLevel(difficultyLevel), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> updateQuestion(Question question, int id) {
        Optional<Question> q = questionDao.findById(id);
        try {
            q.get().setQuestionTitle(question.getQuestionTitle());
            q.get().setOption1(question.getOption1());
            q.get().setOption2(question.getOption2());
            q.get().setOption3(question.getOption3());
            q.get().setOption4(question.getOption4());
            q.get().setRightAnswer(question.getRightAnswer());
            q.get().setDifficultyLevel(question.getDifficultyLevel());
            q.get().setCategory(question.getCategory());

            questionDao.save(q.get());
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
        }
       return new ResponseEntity<>("id not found", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Integer>> getQuestionIDsForQuiz(String category,Integer numOfQuestions) {

        List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, numOfQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id: questionIds){
            questions.add(questionDao.findById(id).get());
        }
        for (Question question : questions){
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestion_title(question.getQuestionTitle());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());

            wrapper.add(questionWrapper);
        }
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }
    public ResponseEntity<Integer> getScore(List<Response> answers) {
        try {
            int rightAnswer=0;

            for (Response response : answers){
                Question question = questionDao.findById(response.getId()).get();
                if (response.getResponse().equals(question.getRightAnswer()))
                    rightAnswer++;
            }
            return new ResponseEntity<>(rightAnswer, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsByLevel(String level, Integer numOfQuestions) {
        List<Integer> questionIDs = questionDao.findRandomQuestionsByLevel(level, numOfQuestions);

        return new ResponseEntity<>(questionIDs, HttpStatus.OK);
    }
}
