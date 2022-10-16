package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),

})
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Setter를 전체 클래스가 아니라 각 필드에 적용하는 이유: 사용자가 특정 필드에 접근하여 세팅을 하지 못하게 하기 위해

    @Setter @Column(nullable = false) private String title;       // 제목
    @Setter @Column(nullable = false, length = 10000) private String content;     // 본문

    @Setter private String hashtag;     // 해시태그

    @CreatedBy @Column(nullable = false) private LocalDateTime createdAt;    // 생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createBy;            // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedat;   // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;          // 수정자

    protected Article() {

    }
    public Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
