package com.github.cenkakin.rpslsapp.game.domain;

import org.junit.jupiter.api.Test;

import static com.github.cenkakin.rpslsapp.game.domain.Hand.ComputerHand;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType.LIZARD;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType.PAPER;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType.ROCK;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType.SCISSORS;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType.SPOCK;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.UserHand;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

  @Test
  void shouldBeDrawWhenUserHandAndComputerHandPaper() {
    UserHand userHand = UserHand.of(PAPER);
    MockComputerHand mockComputerHand = new MockComputerHand(PAPER);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.DRAW);
  }

  @Test
  void shouldBeDrawWhenUserHandAndComputerHandScissors() {
    UserHand userHand = UserHand.of(SCISSORS);
    MockComputerHand mockComputerHand = new MockComputerHand(SCISSORS);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.DRAW);
  }

  @Test
  void shouldBeDrawWhenUserHandAndComputerHandRock() {
    UserHand userHand = UserHand.of(ROCK);
    MockComputerHand mockComputerHand = new MockComputerHand(ROCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.DRAW);
  }

  @Test
  void shouldBeDrawWhenUserHandAndComputerHandSpock() {
    UserHand userHand = UserHand.of(SPOCK);
    MockComputerHand mockComputerHand = new MockComputerHand(SPOCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.DRAW);
  }

  @Test
  void shouldBeDrawWhenUserHandAndComputerHandLizard() {
    UserHand userHand = UserHand.of(LIZARD);
    MockComputerHand mockComputerHand = new MockComputerHand(LIZARD);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.DRAW);
  }

  @Test
  void shouldUserWinWhenUserHandRockAndComputerHandLizard() {
    UserHand userHand = UserHand.of(ROCK);
    MockComputerHand mockComputerHand = new MockComputerHand(LIZARD);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserWinWhenUserHandRockAndComputerHandScissors() {
    UserHand userHand = UserHand.of(ROCK);
    MockComputerHand mockComputerHand = new MockComputerHand(SCISSORS);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserLostWhenUserHandRockAndComputerHandSpock() {
    UserHand userHand = UserHand.of(ROCK);
    MockComputerHand mockComputerHand = new MockComputerHand(SPOCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserLostWhenUserHandRockAndComputerHandPaper() {
    UserHand userHand = UserHand.of(ROCK);
    MockComputerHand mockComputerHand = new MockComputerHand(PAPER);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserWinWhenUserHandPaperAndComputerHandRock() {
    UserHand userHand = UserHand.of(PAPER);
    MockComputerHand mockComputerHand = new MockComputerHand(ROCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserWinWhenUserHandPaperAndComputerHandSpock() {
    UserHand userHand = UserHand.of(PAPER);
    MockComputerHand mockComputerHand = new MockComputerHand(SPOCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserLostWhenUserHandPaperAndComputerHandLizard() {
    UserHand userHand = UserHand.of(PAPER);
    MockComputerHand mockComputerHand = new MockComputerHand(LIZARD);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserLostWhenUserHandPaperAndComputerHandScissors() {
    UserHand userHand = UserHand.of(PAPER);
    MockComputerHand mockComputerHand = new MockComputerHand(SCISSORS);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserWinWhenUserHandScissorsAndComputerHandPaper() {
    UserHand userHand = UserHand.of(SCISSORS);
    MockComputerHand mockComputerHand = new MockComputerHand(PAPER);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserWinWhenUserHandScissorsAndComputerHandLizard() {
    UserHand userHand = UserHand.of(SCISSORS);
    MockComputerHand mockComputerHand = new MockComputerHand(LIZARD);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserLostWhenUserHandScissorsAndComputerHandRock() {
    UserHand userHand = UserHand.of(SCISSORS);
    MockComputerHand mockComputerHand = new MockComputerHand(ROCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserLostWhenUserHandScissorsAndComputerHandSpock() {
    UserHand userHand = UserHand.of(SCISSORS);
    MockComputerHand mockComputerHand = new MockComputerHand(SPOCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserWinWhenUserHandLizardAndComputerHandPaper() {
    UserHand userHand = UserHand.of(LIZARD);
    MockComputerHand mockComputerHand = new MockComputerHand(PAPER);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserWinWhenUserHandLizardAndComputerHandSpock() {
    UserHand userHand = UserHand.of(LIZARD);
    MockComputerHand mockComputerHand = new MockComputerHand(SPOCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserLostWhenUserHandLizardAndComputerHandRock() {
    UserHand userHand = UserHand.of(LIZARD);
    MockComputerHand mockComputerHand = new MockComputerHand(ROCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserLostWhenUserHandLizardAndComputerHandScissors() {
    UserHand userHand = UserHand.of(LIZARD);
    MockComputerHand mockComputerHand = new MockComputerHand(SCISSORS);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserWinWhenUserHandSpockAndComputerHandScissors() {
    UserHand userHand = UserHand.of(SPOCK);
    MockComputerHand mockComputerHand = new MockComputerHand(SCISSORS);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserWinWhenUserHandSpockAndComputerHandRock() {
    UserHand userHand = UserHand.of(SPOCK);
    MockComputerHand mockComputerHand = new MockComputerHand(ROCK);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_WIN);
  }

  @Test
  void shouldUserLostWhenUserHandSpockAndComputerHandPaper() {
    UserHand userHand = UserHand.of(SPOCK);
    MockComputerHand mockComputerHand = new MockComputerHand(PAPER);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  @Test
  void shouldUserLostWhenUserHandSpockAndComputerHandLizard() {
    UserHand userHand = UserHand.of(SPOCK);
    MockComputerHand mockComputerHand = new MockComputerHand(LIZARD);
    Game game = Game.play(userHand, mockComputerHand);
    assertEquals(game.getResult(), Game.Result.USER_LOST);
  }

  static class MockComputerHand extends ComputerHand {

    MockComputerHand(HandType handType) {
      super(handType);
    }
  }
}