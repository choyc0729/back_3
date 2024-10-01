package com.example.board_like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.board_like.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}

