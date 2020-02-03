package com.github.cenkakin.rpslsapp.game.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Hand {

  private final HandType handType;

  Hand(HandType handType) {
    this.handType = handType;
  }

  public HandType get() {
    return this.handType;
  }

  public static class UserHand extends Hand {

    UserHand(HandType handType) {
      super(handType);
    }

    public static UserHand of(HandType handType) {
      return new UserHand(handType);
    }
  }

  public static class ComputerHand extends Hand {

    private static final Random RANDOM = new Random();

    private static final HandType[] HAND_TYPES = HandType.values();

    ComputerHand(HandType handType) {
      super(handType);
    }

    public static ComputerHand create() {
      return new ComputerHand(randomHandType());
    }

    private static HandType randomHandType() {
      return HAND_TYPES[RANDOM.nextInt(HAND_TYPES.length)];
    }
  }

  public enum HandType {

    ROCK("ROCK"),
    PAPER("PAPER"),
    SCISSORS("SCISSORS"),
    LIZARD("LIZARD"),
    SPOCK("SPOCK");

    static {
      ROCK.canBeatList = List.of(SCISSORS, LIZARD);
      PAPER.canBeatList = List.of(ROCK, SPOCK);
      SCISSORS.canBeatList = List.of(PAPER, LIZARD);
      LIZARD.canBeatList = List.of(SPOCK, PAPER);
      SPOCK.canBeatList = List.of(SCISSORS, ROCK);
    }

    private final String value;
    private List<HandType> canBeatList;

    HandType(String value) {
      this.value = value;
    }

    public static Optional<HandType> fromTextValue(String text) {
      return Arrays.stream(HandType.values())
          .filter(hand -> hand.getValue().equals(text))
          .findFirst();
    }

    public Optional<Boolean> beats(HandType other) {
      if (other == this) {
        return Optional.empty();
      }
      return Optional.of(this.canBeatList.contains(other));
    }

    public String getValue() {
      return value;
    }
  }
}