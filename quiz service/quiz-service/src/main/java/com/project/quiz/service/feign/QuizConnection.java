package com.project.quiz.service.feign;
import com.project.quiz.service.model.QuestionWrapper;
import com.project.quiz.service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizConnection {
    @GetMapping("Question/generate")
    public ResponseEntity<List<Integer>> getQuestionIDsForQuiz(@RequestParam String category, @RequestParam Integer numOfQuestions);
    @PostMapping("Question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    @PostMapping("Question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> answers);
    @GetMapping("Question/level")
    public ResponseEntity<List<Integer>> getQuestionsByLevel(@RequestParam String level, @RequestParam Integer numOfQuestions);
}
