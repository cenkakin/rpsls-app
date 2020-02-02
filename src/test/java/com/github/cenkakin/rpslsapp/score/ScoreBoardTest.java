package com.github.cenkakin.rpslsapp.score;

import com.github.cenkakin.rpslsapp.game.domain.Game;
import com.github.cenkakin.rpslsapp.game.event.GamePlayedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.github.cenkakin.rpslsapp.game.domain.Hand.ComputerHand;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.UserHand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreBoardTest {

  private ScoreBoard scoreBoard;

  @BeforeEach
  void beforeEach() {
    scoreBoard = new ScoreBoard();
  }

  @Test
  void shouldThrowExceptionWhenGameIsNullInGamePlayedEvent() {
    //given
    GamePlayedEvent event = new GamePlayedEvent(null);

    //when-then
    Exception exception = assertThrows(IllegalArgumentException.class, () -> scoreBoard.record(event));
    assertEquals("Game should be present in the GamePlayedEvent!", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenResultIsNullInGamePlayedEvent() {
    //given
    Game mockGame = generateGame();
    ReflectionTestUtils.setField(mockGame, "result", null);
    GamePlayedEvent event = new GamePlayedEvent(mockGame);

    //when-then
    Exception exception = assertThrows(IllegalArgumentException.class, () -> scoreBoard.record(event));
    assertEquals("Result should be present in GamePlayedEvent!", exception.getMessage());
  }

  @Test
  void shouldUserWinBeOneWhenResultIsUserWinInGamePlayedEvent() {
    //given
    Game mockGame = generateGame();
    ReflectionTestUtils.setField(mockGame, "result", Game.Result.USER_WIN);
    GamePlayedEvent event = new GamePlayedEvent(mockGame);

    //when
    scoreBoard.record(event);

    //then
    assertEquals(scoreBoard.getScore().getUserWinCount(), 1);
    assertEquals(scoreBoard.getScore().getDrawCount(), 0);
    assertEquals(scoreBoard.getScore().getUserLostCount(), 0);
  }

  @Test
  void shouldUserLostBeOneWhenResultIsUserLostInGamePlayedEvent() {
    //given
    Game mockGame = generateGame();
    ReflectionTestUtils.setField(mockGame, "result", Game.Result.USER_LOST);
    GamePlayedEvent event = new GamePlayedEvent(mockGame);

    //when
    scoreBoard.record(event);

    //then
    assertEquals(scoreBoard.getScore().getUserWinCount(), 0);
    assertEquals(scoreBoard.getScore().getDrawCount(), 0);
    assertEquals(scoreBoard.getScore().getUserLostCount(), 1);
  }

  @Test
  void shouldDrawBeOneWhenResultIsDrawInGamePlayedEvent() {
    //given
    Game mockGame = generateGame();
    ReflectionTestUtils.setField(mockGame, "result", Game.Result.DRAW);
    GamePlayedEvent event = new GamePlayedEvent(mockGame);

    //when
    scoreBoard.record(event);

    //then
    assertEquals(scoreBoard.getScore().getUserWinCount(), 0);
    assertEquals(scoreBoard.getScore().getDrawCount(), 1);
    assertEquals(scoreBoard.getScore().getUserLostCount(), 0);
  }

  private Game generateGame() {
    return Game.play(UserHand.of(HandType.LIZARD), ComputerHand.create());
  }
}