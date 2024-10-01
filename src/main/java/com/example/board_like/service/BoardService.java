package com.example.board_like.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.board_like.model.Board;
import com.example.board_like.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // Get all boards
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // Save a new board
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    // Get a board by its ID
    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    // Update an existing board
    public void updateBoard(Long id, Board updatedBoard) {
        Board board = getBoardById(id);
        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());
        board.setPoint(updatedBoard.getPoint());
        board.setCategory(updatedBoard.getCategory());
        boardRepository.save(board);
    }

    // Like a board
    public void likeBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setLikeCount(board.getLikeCount() + 1);
        boardRepository.save(board);
    }

    // Dislike a board
    public void dislikeBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setDislikeCount(board.getDislikeCount() + 1);
        boardRepository.save(board);
    }

    // Get boards sorted by like count
    public List<Board> getBoardsByLikeCount() {
        return boardRepository.findAllByOrderByLikeCountDesc();
    }

    // Get boards sorted by points
    public List<Board> getBoardsByPoint() {
        return boardRepository.findAllByOrderByPointAsc();
    }

    // Get boards sorted by user score
    public List<Board> getBoardsByUserScore() {
        return boardRepository.findAllByUserScoreDesc();
    }
}
