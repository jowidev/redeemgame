package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.TDGame;

import static com.mygdx.game.Assets.SKIN;

public class HUD extends Table {
    private float money;
    private float moneyTimer = 0; // Nuevo temporizador para el sistema de plata
    private static final float MONEY_INTERVAL = 7f; // Intervalo para agregar dinero (en segundos)
    private static final int MONEY_AMOUNT = 5; // Cantidad de dinero a agregar cada vez
    private final Table timerTable;
    private Table slimeTable;
    private Table boulderTable;
    private final Stack stack;
    private final Stack stack2;
    private final Stack stack3;
    private float time = 90;
    private int showTime;
    private final Label numberLabel;
    private boolean running = true;
    private Label slimeMoneyLabel;
    private Label boulderMoneyLabel;
    private Skin skin;

    public HUD(TDGame game, float initialMoney) {
        skin = Assets.manager.get(SKIN);
        Texture signImg = game.assets.timerBg;
        Texture currBg = game.assets.currBg;
        Image timerBg = new Image(signImg);
        Image bgCurr = new Image(currBg);
        Image bgCurr2 = new Image(currBg);
        showTime = (int) time;
        timerTable = new Table();


        stack = new Stack();
        numberLabel = new Label(String.valueOf(showTime), skin);
        slimeMoneyLabel = new Label("$" + String.valueOf(money), skin);
        slimeMoneyLabel.setScale(2);
        slimeMoneyLabel.setFontScale(1.25f);
        slimeMoneyLabel.setAlignment(Align.center);
        boulderMoneyLabel = new Label("$" + String.valueOf(money), skin);
        boulderMoneyLabel.setScale(2);
        boulderMoneyLabel.setFontScale(1.25f);
        boulderMoneyLabel.setAlignment(Align.center);
        numberLabel.setScale(2);
        numberLabel.setFontScale(1.25f);
        numberLabel.setAlignment(Align.center);
        stack.add(timerBg);
        stack2 = new Stack();
        stack3 = new Stack();
        stack2.add(bgCurr);
        stack3.add(bgCurr2);
        slimeTable = new Table();
        boulderTable = new Table();
        stack.add(numberLabel);
        stack2.add(slimeMoneyLabel); // esta linea hace que este en la tabla pero no hace nada
        stack3.add(boulderMoneyLabel);
        timerTable.setSkin(skin);
        slimeTable.setSkin(skin);
        boulderTable.setSkin(skin);
        setTablePos();
        bothTables();



        money = 0;


    }

    public void bothTables() {

        slimeTable.setDebug(false);
        slimeTable.setPosition(16, (float) Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /6f);
        slimeTable.setSize(64,48);
        slimeTable.add(stack2)
                .width(Gdx.graphics.getWidth() * (Constants.PIXELTOTILE * 2))
                .height(Gdx.graphics.getHeight() * (Constants.PIXELTOTILE * 2));


        boulderTable.setDebug(false);
        boulderTable.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/15f, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6f);

        boulderTable.setSize(64,48);
        boulderTable.add(stack3)
                .width(Gdx.graphics.getWidth() * (Constants.PIXELTOTILE * 2))
                .height(Gdx.graphics.getHeight() * (Constants.PIXELTOTILE * 2));

    }
    public void update(TDGame game, float delta) {
        if (running && time > 0) {
            reduceTime(delta);
        } else {
            if (running) {
                game.pause();
                running = false;
            }
        }
    }
    private void reduceTime(float delta) {
        time -= delta;
        showTime = (int) time;
        numberLabel.setText(String.valueOf(showTime));
    }

    public void stop() {
        running = false;
    }

    private void setTablePos() {
        timerTable.center().top().padTop(16)
                .setPosition((float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());

        timerTable.add(stack)
                .width(Gdx.graphics.getWidth() * (Constants.PIXELTOTILE * 2))
                .height(Gdx.graphics.getHeight() * (Constants.PIXELTOTILE * 2));


    }
    public void updateMoney(float delta) {
        moneyTimer += delta;

        if (moneyTimer >= MONEY_INTERVAL) {
            moneyTimer -= MONEY_INTERVAL;

            money += MONEY_AMOUNT;
            slimeMoneyLabel.setText("$"+ String.valueOf(money));
            boulderMoneyLabel.setText("$"+ String.valueOf(money));
        }
    }

    public int getTime() {
        return (int) time;
    }
    public Table getSlimeTable() {
        return slimeTable;
    }
    public Table getBoulderTable() {
        return boulderTable;
    }
    public Table getTimerTable() {
        return timerTable;
    }
}