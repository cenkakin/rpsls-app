package com.github.cenkakin.rpslsapp.score.controller;

import com.github.cenkakin.rpslsapp.game.domain.Game;
import com.github.cenkakin.rpslsapp.game.domain.Game.Result;
import com.github.cenkakin.rpslsapp.game.domain.Hand;
import com.github.cenkakin.rpslsapp.game.event.GamePlayedEvent;
import com.github.cenkakin.rpslsapp.score.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ScoreE2ETest {

  private MockMvc mockMvc;

  @Autowired
  private ScoreBoard scoreBoard;

  @BeforeEach
  void setup(WebApplicationContext context) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void shouldReturnCorrespondentResponseWhenTwoDrawsAndOneLostAndThreeWinHappened() throws Exception {
    scoreBoard.record(generateGameEventWithResult(Result.DRAW));
    scoreBoard.record(generateGameEventWithResult(Result.DRAW));
    scoreBoard.record(generateGameEventWithResult(Result.USER_WIN));
    scoreBoard.record(generateGameEventWithResult(Result.USER_WIN));
    scoreBoard.record(generateGameEventWithResult(Result.USER_LOST));
    scoreBoard.record(generateGameEventWithResult(Result.USER_WIN));

    this.mockMvc.perform(get("/score"))
        .andExpect(status().isOk())
        .andExpect(content().string("{\"win\":3,\"lost\":1,\"draw\":2}"));
  }

  private GamePlayedEvent generateGameEventWithResult(Result result) {
    Game game = Game.play(Hand.UserHand.of(Hand.HandType.LIZARD), Hand.ComputerHand.create());
    ReflectionTestUtils.setField(game, "result", result);
    return new GamePlayedEvent(game);
  }

  @TestConfiguration
  static class TaskExecutorTestConfiguration {

    //adding this bean to make com.github.cenkakin.rpslsapp.score.ScoreBoard.record sync operation
    //to have consistent tests
    @Bean
    TaskExecutor taskExecutor() {
      return new SyncTaskExecutor();
    }
  }
}