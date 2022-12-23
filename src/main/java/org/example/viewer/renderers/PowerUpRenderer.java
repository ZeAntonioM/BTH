package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Entity;
import org.example.model.game.entities.powerups.PowerUp;
import org.example.viewer.renderers.Renderer;

public class PowerUpRenderer implements Renderer<PowerUp> {

    @Override
    public void draw(PowerUp powerUp, GUI gui){
        gui.drawPowerUp(powerUp.getPosition(), powerUp.getAnimation(), powerUp.getColor());
    }
}
