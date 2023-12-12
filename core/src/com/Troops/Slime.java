package com.Troops;

import com.Server.Client;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Gamemap;

public class Slime extends BaseTroop {

	private final Gamemap game;
	public float stateTime;


	public Slime(Gamemap game, int x, int y) {
		super(x, y, 2, 2, 100);
		troopOnMouse = false;
		stateTime = 0;
		this.game = game;
		baseAnimation = new Animation<TextureRegion>(0.033f, game.assets.slimewalk, PlayMode.LOOP);

		if (game.assets.slimewalk.size==0) {
			System.out.println("o");
		}

	}
	public void update(Viewport viewport) {
		if (!troopOnMouse) {
			Vector3 pos = viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			hitbox.x = pos.x -1;
			hitbox.y = pos.y -1;
		}
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !troopOnMouse) {
			troopOnMouse = true;
			game.assets.slimeplaced.play();
			hitbox.set(hitbox.x,hitbox.y, 1, 1.25f);
			System.out.println(hitbox.x + " " + hitbox.y);

		}
		Client.placeSlime(troopOnMouse, hitbox);
	}

	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = baseAnimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, hitbox.x, hitbox.y, 2, 2);


	}
}
