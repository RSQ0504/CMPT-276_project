import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RewardTest {
  private Reward reward;
  private MainCharacter a;
  private GameFrame gameFrame;
  private inputKey key;

  @BeforeEach
  void setUp() throws IOException {
    gameFrame = new GameFrame(1,1,1);
    key = null;
    a = new MainCharacter(gameFrame,key);
    reward = new Food(gameFrame,0,0);
  }


  @Test
  void setAppearTest() {
    boolean original = true;
    reward.setAppear(original);
    assertEquals(original,reward.getAppear());
  }

  @Test
  void getAppearTest() {
    boolean original = true;
    reward.setAppear(original);
    assertEquals(original,reward.getAppear());
  }

  @Test
  void draw() {
  }

  @Test
  void check() {
    Rectangle mc = new Rectangle(a.x,a.y,a.width,a.height);
    boolean original = reward.hitAreaStatic.intersects(mc);
    assertEquals(original,reward.check(mc));
  }
}
