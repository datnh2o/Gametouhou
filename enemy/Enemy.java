package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;

public class Enemy extends GameObject {
    public int hp;
    public int damage;

    public Enemy() {
        // image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
        //  renderer=new Renderer("assets/images/enemies/level0/pink/0.png");
        renderer = new Renderer("assets/images/enemies/level0/blue");
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hitBox = new BoxCollider(this, 28, 28);
        hp = 3;
        damage = 1;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    @Override
    public void run() {
        super.run();//velocity
        //System.out.println(velocity.x);
        this.move();
        this.checkPlayer();

    }

    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class, this.hitBox);
        if (player != null) {
            player.takeDamage(damage);
            this.deactive();
        }
    }

    private void move() {
        if (this.onGoingRight() && this.outOfBoundRight()) {
            this.reserveVelocityX();
        }
        if (this.onGoingLeft() && this.outOfBoundLeft()) {
            this.reserveVelocityX();
        }
        this.deactiveIfNeeded();
    }

    @Override
    public void reset() {
        super.reset();
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hp = 3;
    }

    private void deactiveIfNeeded() {
        if (position.y > 600) {
            this.deactive();
        }
    }

    private boolean outOfBoundLeft() {
        return position.x < 0;
    }


    private boolean onGoingLeft() {
        return velocity.x < 0;
    }

    private void reserveVelocityX() {
        velocity.x = -velocity.x;
    }

    private boolean outOfBoundRight() {
        return position.x > 384 - 32;
    }

    private boolean onGoingRight() {
        return velocity.x > 0;
    }
}
