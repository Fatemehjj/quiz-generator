package com.project.quiz.service.dao;;
import com.project.quiz.service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Quiz findByTitle(String title);
}
