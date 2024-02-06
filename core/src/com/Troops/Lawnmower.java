package com.Troops;

import com.MenuScreens.TeamScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.TDGame;

import java.util.ArrayList;

public class Lawnmower {
    public Rectangle hitbox = new Rectangle();
    private Texture texture;
    private boolean running = false;
    public Lawnmower(int x, int y) {
        hitbox.set(x,y,.2f,2);
        texture = new Texture("miscAssets/sddefault.png");
    }
    public void draw() {
        TDGame.batch.draw(texture, hitbox.x, hitbox.y, 2, 2);
    }
    public void instakill(Boulder boulder, ArrayList<BaseTroop> tempArr, ArrayList<BaseTroop> troopArr) {
        if (boulder != null && !running) {
            if (boulder.hitbox.overlaps(hitbox)) {
                running = true;
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