import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {
  private GameObject gameObject;
  private MainCharacter a;
  private GameFrame gameFrame;
  private inputKey key;
  private Random r = new Random();

  @BeforeEach
  void setUp() throws IOException {
    gameFrame = new GameFrame(r.nextInt(500),r.nextInt(500),r.nextInt(500));
    key = null;
    a = new MainCharacter(gameFrame,key);
    gameObject = new Food(gameFrame,0,0);
  }

  @Test
  void getCollisionTest() {
    boolean original = r.nextBoolean();
    gameObject.setCollision(original);
    assertEquals(original,gameObject.getCollision());
  }

  @Test
  void setCollisionTest() {
    boolean original = r.nextBoolean();
    gameObject.setCollision(original);
    assertEquals(original,gameObject.getCollision());
  }

  @Test
  void getImage() {
    gameObject.image = new BufferedImage(r.nextInt(10),r.nextInt(10),1);
    assertEquals(gameObject.image,gameObject.getImage());
  }

  @Test
  void getXTest() {
    assertEquals(gameObject.x,gameObject.getX());
  }

  @Test
  void getYTest() {
    assertEquals(gameObject.y,gameObject.getY());
  }

  @Test
  void getWidthTest() {
    assertEquals(gameObject.width,gameObject.getWidth());
  }

  @Test
  void getHeightTest() {
    assertEquals(gameObject.height,gameObject.getHeight());
  }

  @Test
  void setFrameTest() throws IOException {
    gameObject.setFrame(gameFrame);
    assertEquals(gameFrame,gameObject.getFrame());
  }
  @Test
  void getFrameTest() throws IOException {
    gameObject.setFrame(gameFrame);
    assertEquals(gameFrame,gameObject.getFrame());
  }
}
