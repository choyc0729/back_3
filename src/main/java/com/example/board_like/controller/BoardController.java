package com.example.board_like.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.board_like.model.Board;
import com.example.board_like.service.BoardService;
import com.example.board_like.service.CategoryService;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final CategoryService categoryService;

    public BoardController(BoardService boardService, CategoryService categoryService) {
        this.boardService = boardService;
        this.categoryService = categoryService;
    }

    // 게시물 목록
    @GetMapping("/")
    public String list(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "landing";
    }

    // 게시물 등록 페이지
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("board", new Board());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "create";
    }

    // 게시물 등록 처리
    @PostMapping("/")
    public String create(@ModelAttribute Board board) {
        boardService.saveBoard(board);
        return "redirect:/";
    }

    // 게시물 수정 페이지
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit";
    }

    // 게시물 수정 처리
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Board board) {
        boardService.updateBoard(id, board);
        return "redirect:/";
    }

    // 좋아요 처리
    @PostMapping("/{id}/like")
    public String likeBoard(@PathVariable Long id) {
        boardService.likeBoard(id);
        return "redirect:/";
    }

    // 싫어요 처리
    @PostMapping("/{id}/dislike")
    public String dislikeBoard(@PathVariable Long id) {
        boardService.dislikeBoard(id);
        return "redirect:/";
    }

    // API: Get filtered boards
    @GetMapping("/boards")
    @ResponseBody
    public List<Board> getFilteredBoards(@RequestParam String sort) {
        switch (sort) {
            case "likeCount":
                return boardService.getBoardsByLikeCount();
            case "score":
                return boardService.getBoardsByUserScore();
            case "point":
                return boardService.getBoardsByPoint();
            default:
                throw new IllegalArgumentException("Invalid sort option");
        }
    }
}
