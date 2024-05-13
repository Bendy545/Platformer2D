package unitTests;

import game.MethodsForCollisionDetection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

import static game.MethodsForCollisionDetection.GetWallEntity;
import static org.junit.jupiter.api.Assertions.*;

class MethodsForCollisionDetectionTest {

    @Test
    public void testIsOnFloorTrue() {
        int[][] lvlData = {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}};
        Rectangle2D.Float hitbox = new Rectangle2D.Float(0, 15, 10, 10);
        Assertions.assertFalse(MethodsForCollisionDetection.IsOnFloor(hitbox, lvlData));
    }
    @Test
    public void testIsOnFloorFalse() {
        int[][] lvlData = {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}};
        Rectangle2D.Float hitbox = new Rectangle2D.Float(0, 25, 10, 10);
        assertFalse(MethodsForCollisionDetection.IsOnFloor(hitbox, lvlData));
    }


}