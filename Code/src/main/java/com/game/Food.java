package com.game;

public class Food extends Reward {
  private int HP_increase;

  public void increaseHP(Maincharacter m){
    m.setHP(m.getHP()+1);
  }
}
