package org.example.controller.game;

import org.example.Game;
import org.example.controller.Controllers.game.BombermanController;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.UnbreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BombermanControllerTest {
    private BombermanController bombermanController;
    private Bomberman bomberman;
    private Arena arena;
    private Game game;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10,10);

        bomberman = new Bomberman(new Position(5,5));
        arena.setBomberman(bomberman);

        arena.setUnbreakableWalls(Arrays.asList());
        arena.setMonsters(Arrays.asList());

        bombermanController = new BombermanController(arena);

        game = Mockito.mock(Game.class);
    }

    @Test
    void moveBombermanRightEmpty(){
        bombermanController.step(game, GUI.ACTION.RIGHT, 10);
        assertEquals(new Position(6,5), bomberman.getPosition());
    }

    @Test
    void moveBombermanRightNotEmpty(){
        arena.setUnbreakableWalls(Arrays.asList(new UnbreakableWall(new Position(6,5))));

        bombermanController.step(game, GUI.ACTION.RIGHT, 10);
        assertEquals(new Position(5,5), bomberman.getPosition());
    }

    @Test
    void moveBombermanLeftEmpty(){
        bombermanController.step(game, GUI.ACTION.LEFT, 10);
        assertEquals(new Position(4,5), bomberman.getPosition());
    }

    @Test
    void moveBombermanLeftNotEmpty(){
        arena.setUnbreakableWalls(Arrays.asList(new UnbreakableWall(new Position(4,5))));

        bombermanController.step(game, GUI.ACTION.LEFT, 10);
        assertEquals(new Position(5,5), bomberman.getPosition());
    }

    @Test
    void moveBombermanUpEmpty(){
        bombermanController.step(game, GUI.ACTION.UP, 10);
        assertEquals(new Position(5,4), bomberman.getPosition());
    }

    @Test
    void moveBombermanUpNotEmpty(){
        arena.setUnbreakableWalls(Arrays.asList(new UnbreakableWall(new Position(5,4))));

        bombermanController.step(game, GUI.ACTION.UP, 10);
        assertEquals(new Position(5,5), bomberman.getPosition());
    }

    @Test
    void moveBombermanDownEmpty(){
        bombermanController.step(game, GUI.ACTION.DOWN, 10);
        assertEquals(new Position(5,6), bomberman.getPosition());
    }

    @Test
    void moveBombermanDownNotEmpty(){
        arena.setUnbreakableWalls(Arrays.asList(new UnbreakableWall(new Position(5,6))));

        bombermanController.step(game, GUI.ACTION.DOWN, 10);
        assertEquals(new Position(5,5), bomberman.getPosition());
    }

    @Test
    void nextAnimation(){
        //arrange

        //act
        bombermanController.step(game, GUI.ACTION.NONE, 600);

        //assert
        assertEquals('ṙ', bomberman.getAnimation());

        //act
        bombermanController.step(game, GUI.ACTION.NONE, 1200);

        //assert
        assertEquals('ṗ', bomberman.getAnimation());
    }

    @Test
    void moveToMonsterTest(){
        //arrange
        arena.setMonsters(Arrays.asList(new Monster(new Position(6,5))));

        //act
        bombermanController.step(game, GUI.ACTION.RIGHT, 10);

        //assert
        assertEquals(new Position(1,2), bomberman.getPosition());
        assertEquals(bomberman.getLives(), 2);

    }

    @Test
    void placeBomb(){

        //assert
        assertEquals(1, bomberman.getBombQuantity());

        //act
        bombermanController.step(game, GUI.ACTION.SPACE, 10);

        //assert
        assertEquals(1, arena.getBombs().size());
        assertEquals(0, bomberman.getBombQuantity());
    }
}
