package game.physics;

import game.GameObject;
import game.Vector2D;

public class BoxCollider {
    public int width;
    public int height;
    public Vector2D position;

    public BoxCollider(GameObject master, int width, int height) {
        this.position = master.position;
        this.width = width;
        this.height = height;
    }

    public double top() {
        return position.y;
    }

    public double bot() {
        return this.top() + this.height;
    }

    public double left() {
        return position.x;
    }

    public double right() {
        return this.left() + this.width;
    }

    public boolean intersects(BoxCollider other) {
        //box duoc goi (this) co giao voi box truyen vao (other) hay khong?
        return other.bot() >= this.top()
                && other.top() <= this.bot()
                && other.right() >= this.left()
                && other.left() <= this.right();
    }
}
