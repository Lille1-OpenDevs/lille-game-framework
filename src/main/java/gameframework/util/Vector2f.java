/**
 * File : Vector2f.java
 * gameframework
 * Vector2f
 *
 * Author : group1
 * C>
 */

package gameframework.util;

import java.awt.*;

/**
 * Class which can represent abstract point value for the model side of the game
 */
public class Vector2f {

    //~ Attribute ~//

    private float x;
    private float y;

    //~ Method ~//

    /**
     * Construct a vector 2D float from the values
     * @param x the value x
     * @param y the value y
     */
    public Vector2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x value of the vector
     */
    public float getX(){
        return this.x;
    }

    /**
     * @return the y value of the vector
     */
    public float getY(){
        return this.y;
    }

    /**
     * @return the length of the vector by calculating the norm
     */
    public float length(){
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * @return a new normalized vector
     */
    public Vector2f normalize(){
        return new Vector2f(this.x / Math.abs(this.x), this.y / Math.abs(this.y));
    }

    /**
     * Convert the vector to a java point
     * @return the new java point
     */
    public Point toPoint(){
        return new Point((int)this.x, (int)this.y);
    }
}
