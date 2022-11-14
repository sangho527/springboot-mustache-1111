package com.mustache.springbootmustache1111.domain.dto;
import com.mustache.springbootmustache1111.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String commentContent;
    private String user;

    public Comment toEntity() {
        return new Comment(this.id, this.articleId, this.commentContent, this.user);
    }
}