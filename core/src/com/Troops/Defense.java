package com.Troops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.TDGame;

import java.util.ArrayList;

public class Defense {
    public Rectangle hitbox = new Rectangle();
    private Texture texture;
    private boolean running = false;
    public Defense(float x, float y) {
        hitbox.set(x,y,1,1);
        texture = TDGame.assets.lawn;
    }
    public void render() {
        TDGame.batch.draw(texture, hitbox.x, hitbox.y, 1.5f, 1.5f);
    }
    public void instakill(ArrayList<BaseTroop> tempArr, ArrayList<BaseTroop> troopArr) {
        if (!running) {
            for (BaseTroop troop : troopArr) {
                if (troop instanceof Boulder&&troop.hitbox.overlaps(hitbox)) {
                    running = true;
                }
            }
        }
        if (running) {
            hitbox.x += .1f;
            for (BaseTroop troop : troopArr) {
                if (troop instanceof Boulder&&troop.hitbox.overlaps(hitbox)) {
                    troop.takeDamage(999, tempArr);
                }
            }
            if (hitbox.x > Gdx.graphics.getWidth()) {
                running = false;
            }
        }
    }

}
