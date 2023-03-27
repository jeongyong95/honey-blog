package com.joojeongyong.honey.blog.persistence.post.reply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReplyService {

  private final ReplyRepository replyRepository;
}
