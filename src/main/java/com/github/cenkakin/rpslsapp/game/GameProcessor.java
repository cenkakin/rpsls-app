package com.github.cenkakin.rpslsapp.game;

import com.github.cenkakin.rpslsapp.game.domain.Game;
import com.github.cenkakin.rpslsapp.game.event.GamePlayedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import static com.github.cenkakin.rpslsapp.game.domain.Hand.ComputerHand;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.UserHand;

@AllArgsConstructor
public class GameProcessor {

  private final ApplicationEventPublisher eventPublisher;

  public Game run(UserHand userHand) {
    final ComputerHand computerHand = ComputerHand.create();
    final Game game = Game.play(userHand, computerHand);
    eventPublisher.publishEvent(new GamePlayedEvent(game));
    return game;
  }
}
