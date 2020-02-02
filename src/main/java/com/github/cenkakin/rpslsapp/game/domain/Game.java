package com.github.cenkakin.rpslsapp.game.domain;

import lombok.Getter;

import static com.github.cenkakin.rpslsapp.game.domain.Hand.ComputerHand;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.UserHand;

@Getter
public class Game {

  private final UserHand userHand;

  private final ComputerHand computerHand;

  private final Result result;

  private Game(UserHand userHand, ComputerHand computerHand, Result result) {
    this.userHand = userHand;
    this.computerHand = computerHand;
    this.result = result;
  }

  public static Game play(UserHand userHand, ComputerHand computerHand) {
    final Result result = evaluate(userHand, computerHand);
    return new Game(userHand, computerHand, result);
  }

  private static Result evaluate(UserHand userHand, ComputerHand computerHand) {
    final HandType userHandType = userHand.get();
    final HandType computerHandType = computerHand.get();
    return userHandType
        .beats(computerHandType)
        .map(beat -> beat ? Result.USER_WIN : Result.USER_LOST)
        .orElse(Result.DRAW);
  }

  public enum Result {
    USER_WIN, USER_LOST, DRAW
  }
}
