package com.github.cenkakin.rpslsapp.score;

import com.github.cenkakin.rpslsapp.game.event.GamePlayedEvent;
import com.github.cenkakin.rpslsapp.score.domain.Score;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class ScoreBoard {

  private final Score score;

  public ScoreBoard() {
    this.score = new Score();
  }

  public Score getScore() {
    return score;
  }

  @Async
  @EventListener
  public void record(GamePlayedEvent event) {
    validateEvent(event);
    switch (event.getGame().getResult()) {
      case DRAW -> score.draw();
      case USER_LOST -> score.userLost();
      case USER_WIN -> score.userWon();
    }
  }

  private void validateEvent(GamePlayedEvent event) {
    if (event.getGame() == null) {
      throw new IllegalArgumentException("Game should be present in the GamePlayedEvent!");
    }
    if (event.getGame().getResult() == null) {
      throw new IllegalArgumentException("Result should be present in GamePlayedEvent!");
    }
  }
}
