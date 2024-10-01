package com.example.board_like.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 프로젝트에 필요한 클래스들도 함께 추가하세요.
import com.example.board_like.model.Board;
import com.example.board_like.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    public void updateBoard(Long id, Board updatedBoard) {
        Board board = getBoardById(id);
        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());
        board.setPoint(updatedBoard.getPoint());
        board.setCategory(updatedBoard.getCategory());
        boardRepository.save(board);
    }

    public void likeBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setLikeCount(board.getLikeCount() + 1);
        boardRepository.save(board);
    }

    public void dislikeBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setDislikeCount(board.getDislikeCount() + 1);
        boardRepository.save(board);
    }
}

