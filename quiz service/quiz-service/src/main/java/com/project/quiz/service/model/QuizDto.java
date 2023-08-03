package com.project.quiz.service.model;
import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numOfQuestions;
    String title;
}
