package org.example.controller.menu;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controllers.menu.GameOverScreenController;
import org.example.gui.GUI;
import org.example.model.game.arena.Arena;
import org.example.model.menu.Menu;
import org.example.states.GameState;
import org.example.states.MenuState;
import org.example.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameOverScreenControllerTest {
    private GameOverScreenController gameOverScreenController;
    private Menu menu;

    @BeforeEach
    void setup() throws IOException {
        List<String> GameOverScreenEntries = Arrays.asList("New Highscore!", "Score: 0" );
        menu = new Menu(GameOverScreenEntries);
        gameOverScreenController = new GameOverScreenController(menu);
    }

    @Test
    void testStep() throws IOException {
        //arrange
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getState()).thenReturn(new GameState(Mockito.mock(Arena.class), Mockito.mock(Music.class)));

        //act
        gameOverScreenController.step(game, GUI.ACTION.SELECT, 0);

        //assert
        Mockito.verify(game, Mockito.times(1)).readHighscore();
        Mockito.verify(game, Mockito.times(2)).getState();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(MenuState.class));


    }
}
