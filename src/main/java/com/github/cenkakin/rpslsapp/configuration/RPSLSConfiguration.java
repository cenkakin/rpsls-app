package com.github.cenkakin.rpslsapp.configuration;

import com.github.cenkakin.rpslsapp.game.GameProcessor;
import com.github.cenkakin.rpslsapp.score.ScoreBoard;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class RPSLSConfiguration {

  @Bean
  public GameProcessor gameProcessor(ApplicationEventPublisher publisher) {
    return new GameProcessor(publisher);
  }

  @Bean
  public ScoreBoard scoreBoard() {
    return new ScoreBoard();
  }
}
