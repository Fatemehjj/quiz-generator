package com.project.question.service.controller;
import com.project.question.service.model.Question;
import com.project.question.service.model.QuestionWrapper;
import com.project.question.service.model.Response;
import com.project.question.service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    //@Autowired
   // Environment environment;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestion();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String>addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @GetMapping("level/{difficultyLevel}")
    public ResponseEntity<List<Question>> getQuestionByDifficulty_level(@PathVariable String difficultyLevel){
        return questionService.getQuestionByDifficultyLevel(difficultyLevel);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question,@PathVariable int id){
        return questionService.updateQuestion(question, id);
    }
     @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionIDsForQuiz(@RequestParam String category, @RequestParam Integer numOfQuestions){
        return questionService.getQuestionIDsForQuiz(category, numOfQuestions);
     }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        //System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> answers){
        return questionService.getScore(answers);
    }
    @GetMapping("level")
    public ResponseEntity<List<Integer>> getQuestionsByLevel(@RequestParam String level, @RequestParam Integer numOfQuestions){
        return questionService.getQuestionsByLevel(level, numOfQuestions);
    }
}
