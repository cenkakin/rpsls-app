package com.github.cenkakin.rpslsapp.score.controller;


import com.github.cenkakin.rpslsapp.score.ScoreBoard;
import com.github.cenkakin.rpslsapp.score.domain.Score;
import com.github.cenkakin.rpslsapp.score.dto.ScoreDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class ScoreController {

  private final ScoreBoard scoreBoard;

  @GetMapping(value = "/score")
  public ResponseEntity<ScoreDto> play() {
    final Score score = scoreBoard.getScore();
    return ResponseEntity.ok(ScoreDto.of(score));
  }
}
