package com.joojeongyong.honey.blog.persistence.post.reply;

import com.joojeongyong.honey.blog.persistence.BaseEntity;
import com.joojeongyong.honey.blog.persistence.post.Post;
import com.joojeongyong.honey.blog.persistence.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply")
@Entity
public class Reply extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reply_id")
  private Long replyId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false,
      foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
  )
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false,
      foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
  )
  private Post post;

  @Column(name = "comment", nullable = false)
  private String comment;
}
