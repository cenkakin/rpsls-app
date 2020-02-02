package com.github.cenkakin.rpslsapp.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.cenkakin.rpslsapp.game.domain.Game;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType;

@Data
@JsonInclude(Include.NON_NULL)
public class GameResultDto {

  private String result;

  private String computerHand;

  private GameResultDto(String result) {
    this(result, null);
  }

  private GameResultDto(String result, String computerHand) {
    this.result = result;
    this.computerHand = computerHand;
  }

  public static GameResultDto resolve(Game game) {
    final HandType computerHandType = game.getComputerHand().get();
    final String result = ResultTypeDto.fromGameResult(game.getResult()).getResult();
    return new GameResultDto(result, computerHandType.getValue());
  }

  public static GameResultDto unknown() {
    return new GameResultDto(ResultTypeDto.UNKNOWN.getResult());
  }

  public enum ResultTypeDto {
    WIN("WIN"), DRAW("DRAW"), LOST("LOST"), UNKNOWN("UNKNOWN");

    private final String result;

    ResultTypeDto(String result) {
      this.result = result;
    }

    public static ResultTypeDto fromGameResult(Game.Result gameStatus) {
      return switch (gameStatus) {
        case DRAW -> DRAW;
        case USER_WIN -> WIN;
        case USER_LOST -> LOST;
      };
    }

    public String getResult() {
      return result;
    }
  }
}
