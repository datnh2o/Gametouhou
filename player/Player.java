package game.player;

import game.GameObject;
import game.KeyEventPress;

import game.item.Item;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.Mathx;

import java.awt.*;

public class Player extends GameObject {
    public int hp;
    public boolean immune;

    public Player() {
        //  image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new Renderer("assets/images/players/straight");
//        position = new Vector2D(200, 500);
        position.set(175, 500);
        hitBox = new BoxCollider(this, 32, 48);
        hp = 3;
        immune = false;
    }

    int count = 0; // dem so khung hinh
    int renderCount = 0;

    @Override
    public void render(Graphics g) {
        if (immune) {
            renderCount++;
            if (renderCount % 3 == 0)
                super.render(g);
        } else {
            super.render(g);
        }
    }

    @Override
    public void run() { // 60 fps >> 60 vien dan dc tao ra 1s >> 3 vien duoc tao ra 1s
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
        this.checkItem();
    }

    public void checkItem(){
        Item item = GameObject.findIntersects(Item.class, hitBox);
        if (item != null){
            item.deactive();
            hp += 2;
        }
    }

    private void fire() {
        count++;
        if (KeyEventPress.isFirePress && count > 20) {
//            PlayerBullet bullet = new PlayerBullet();
            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
            bullet.position.set(this.position.x, this.position.y);
            bullet.velocity.setAngle(Math.toRadians(-90));

            PlayerBullet bullet2 = GameObject.recycle(PlayerBullet.class);
            bullet2.position.set(this.position.x - 10, this.position.y);
            bullet2.velocity.setAngle(Math.toRadians(-135));

            PlayerBullet bullet3 = GameObject.recycle(PlayerBullet.class);
            bullet3.position.set(this.position.x + 10, this.position.y);
            bullet3.velocity.setAngle(Math.toRadians(-45));

            PlayerBullet bullet4 = GameObject.recycle(PlayerBullet.class);
            bullet4.position.set(this.position.x - 5, this.position.y);
            bullet4.velocity.setAngle(Math.toRadians(-112.5));

            PlayerBullet bullet5 = GameObject.recycle(PlayerBullet.class);
            bullet5.position.set(this.position.x +5, this.position.y);
            bullet5.velocity.setAngle(Math.toRadians(-67.5));

            count = 0;
        }
    }

    private void limitPosition() {
        position.x = Mathx.clamp(position.x, 0, 384 - 32);
        position.y = Mathx.clamp(position.y, 0, 600 - 48);
    }

    private void move() {
        if (KeyEventPress.isUpPress) {
            position.y -= 3;
        }
        if (KeyEventPress.isLeftPress) {
            position.x -= 3;
        }
        if (KeyEventPress.isRightPress) {
            position.x += 3;
        }
        if (KeyEventPress.isDownPress) {
            position.y += 3;
        }
    }

    int immuneCount = 0;

    private void checkImmune() {
        if (immune) {
            immuneCount++;
            if (immuneCount > 120) {
                immune = false;
            }
        } else {
            immuneCount = 0;
        }

    }

    public void takeDamage(int damage) {
        if (!immune) {
            hp -= damage;
            if (hp <= 0) {
                hp = 0;
                this.deactive();
            } else {
                //bat tu
                immune = true;
            }
        }
    }

    public void takeHp (int addHp){
        hp += addHp;
    }
}
