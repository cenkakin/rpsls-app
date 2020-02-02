package com.github.cenkakin.rpslsapp.score.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Score {

  private final AtomicInteger userWinCount;

  private final AtomicInteger userLostCount;

  private final AtomicInteger drawCount;

  public Score() {
    this.userWinCount = new AtomicInteger();
    this.userLostCount = new AtomicInteger();
    this.drawCount = new AtomicInteger();
  }

  public void userWon() {
    userWinCount.incrementAndGet();
  }

  public void userLost() {
    userLostCount.incrementAndGet();
  }

  public void draw() {
    drawCount.incrementAndGet();
  }

  public Integer getUserWinCount() {
    return userWinCount.get();
  }

  public Integer getUserLostCount() {
    return userLostCount.get();
  }

  public Integer getDrawCount() {
    return drawCount.get();
  }
}
