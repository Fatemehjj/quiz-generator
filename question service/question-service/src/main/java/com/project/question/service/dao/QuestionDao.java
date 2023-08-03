package com.project.question.service.dao;

import com.project.question.service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);
    List<Question> findByDifficultyLevel(String difficultyLevel);
    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numOfQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numOfQ);

    @Query(value = "SELECT q.id FROM question q Where q.difficulty_level=:level ORDER BY RAND() LIMIT :numOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByLevel(String level, int numOfQuestions);
    @Query(value = "SELECT * FROM question q WHERE q.difficulty_level=:difficultyLevel",nativeQuery = true)
    List<Question> findRandomQuestionByLevel(String difficultyLevel);
}

