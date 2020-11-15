package org.example.TeremHMS.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Пожулуйста, составте текст заявления")
    @Length(max = 2048, message = "Текст заявления превыает допустимое количество символов (2048)")
    private String text;
    @NotBlank(message = "Пожулуйста, выберите тэг заявления")
    @Length(max = 255, message = "Тэг заявления превыает допустимое количество символов (255)")
    private String tag;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {

    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}