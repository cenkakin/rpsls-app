package com.github.cenkakin.rpslsapp.game.controller;

import com.github.cenkakin.rpslsapp.game.GameProcessor;
import com.github.cenkakin.rpslsapp.game.dto.GameResultDto;
import com.github.cenkakin.rpslsapp.game.request.UserHandRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.cenkakin.rpslsapp.game.domain.Hand.HandType;
import static com.github.cenkakin.rpslsapp.game.domain.Hand.UserHand;

@RestController
@RequestMapping
@AllArgsConstructor
public class GameController {

  private final GameProcessor gameProcessor;

  @PostMapping(value = "/game")
  public ResponseEntity<GameResultDto> play(@RequestBody UserHandRequest request) {
    return HandType.fromTextValue(request.getHand())
        .map(UserHand::of)
        .map(gameProcessor::run)
        .map(GameResultDto::resolve)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.ok(GameResultDto.unknown()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException() {
    return ResponseEntity.badRequest().body("Request body is not valid!");
  }
}
