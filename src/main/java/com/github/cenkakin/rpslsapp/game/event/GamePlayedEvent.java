package com.github.cenkakin.rpslsapp.game.event;

import com.github.cenkakin.rpslsapp.game.domain.Game;
import lombok.Value;

@Value
public class GamePlayedEvent {

  private final Game game;
}
