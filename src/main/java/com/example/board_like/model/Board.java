package com.example.board_like.model;

import jakarta.persistence.*;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;
    private String content;
    private int point;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    @Column(name = "userId")
    private Long userId; // User의 ID를 저장 (연관 관계를 사용하지 않는 경우)

    private int likeCount;
    private int dislikeCount;

    // 기본 생성자, Getter와 Setter 메서드 추가
    public Board() {}

    // Getter와 Setter 메서드
    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Category getCategory() { // 카테고리 getter
        return category;
    }

    public void setCategory(Category category) { // 카테고리 setter
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
}
