package com.game;

import com.game.Reward;

public class Vaccine extends Reward {
  public void increaseVaccine(MainCharacter m){
    m.setVaccines(m.getVaccines()+1);
  }
}
