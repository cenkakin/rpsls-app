package com.github.cenkakin.rpslsapp.score.dto;

import com.github.cenkakin.rpslsapp.score.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreDto {
  private Integer win;

  private Integer lost;

  private Integer draw;

  public static ScoreDto of(Score score) {
    return new ScoreDto(score.getUserWinCount(), score.getUserLostCount(), score.getDrawCount());
  }
}
