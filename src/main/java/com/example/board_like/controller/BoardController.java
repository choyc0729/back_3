/*package com.example.board_like.controller;

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

    // Landing page
    @GetMapping("/")
    public String landing() {
        return "landing";  // Serve landing.html
    }

    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "list";  // Serve list.html
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
        return "redirect:/boards";
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
        return "redirect:/boards";
    }

    // 좋아요 처리
    @PostMapping("/{id}/like")
    public String likeBoard(@PathVariable Long id) {
        boardService.likeBoard(id);
        return "redirect:/boards";
    }

    // 싫어요 처리
    @PostMapping("/{id}/dislike")
    public String dislikeBoard(@PathVariable Long id) {
        boardService.dislikeBoard(id);
        return "redirect:/boards";
    }

    // API: Get filtered boards
    @GetMapping("/boards/api")
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
    @GetMapping("/index")
    public String indexPage() {
        return "index";  // This will serve index.html
    }
}*/
package com.example.board_like.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.board_like.model.Board;
import com.example.board_like.service.BoardService;
import com.example.board_like.service.CategoryService;

@Controller
@ServerEndpoint("/websocket")
public class BoardController {

    private final BoardService boardService;
    private final CategoryService categoryService;
    private static final List<Session> sessionList = new ArrayList<>();

    public BoardController(BoardService boardService, CategoryService categoryService) {
        this.boardService = boardService;
        this.categoryService = categoryService;
    }

    // Landing page
    @GetMapping("/")
    public String landing() {
        return "landing";  // Serve landing.html
    }

    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "list";  // Serve list.html
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
        return "redirect:/boards";
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
        return "redirect:/boards";
    }

    // 좋아요 처리
    @PostMapping("/{id}/like")
    public String likeBoard(@PathVariable Long id) {
        boardService.likeBoard(id);
        return "redirect:/boards";
    }

    // 싫어요 처리
    @PostMapping("/{id}/dislike")
    public String dislikeBoard(@PathVariable Long id) {
        boardService.dislikeBoard(id);
        return "redirect:/boards";
    }

    // API: Get filtered boards
    @GetMapping("/boards/api")
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

    @GetMapping("/index")
    public String indexPage() {
        return "index";  // This will serve index.html
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";  // chat.html 파일을 반환
    }

    // WebSocket methods
    @OnOpen
    public void open(Session newUser) {
        System.out.println("connected");
        sessionList.add(newUser);
        System.out.println("현재 접속중인 유저 수 : " + sessionList.size());
    }

    /*@OnMessage
    public void getMsg(Session receiveSession, String msg) {
        for (Session session : sessionList) {
            try {
                if (!receiveSession.getId().equals(session.getId())) {
                    session.getBasicRemote().sendText("유저" + (Integer.parseInt(session.getId()) + 1) + " : " + msg);
                } else {
                    session.getBasicRemote().sendText("나 : " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "* * * * * *")
    private void isSessionClosed() {
        if (!sessionList.isEmpty()) {
            sessionList.removeIf(session -> !session.isOpen());
        }
    }*/
}

