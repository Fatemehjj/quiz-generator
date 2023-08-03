package com.project.quiz.service.controller;
import com.project.quiz.service.model.QuestionWrapper;
import com.project.quiz.service.model.QuizDto;
import com.project.quiz.service.model.Response;
import com.project.quiz.service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz (@RequestBody QuizDto quizDto){ //data transfer obj
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumOfQuestions(), quizDto.getTitle());
    }
    @GetMapping("createByLevel")
    public ResponseEntity<String> createByLevel(@RequestParam String Level, @RequestParam String title, @RequestParam Integer numOfQuestions){
        return quizService.createByLevel(Level, title, numOfQuestions);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }
    @GetMapping("getByName/{name}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByTitle(@PathVariable String name){

        return quizService.getQuizQuestionsByTitle(name);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable Integer id, @RequestBody List<Response> responses){
           return quizService.getScore(id, responses);
    }
}
