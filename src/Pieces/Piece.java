package src.Pieces;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.GameLogic;

public abstract class Piece {
    private char color;
    private Point pos;
    private BufferedImage sprite;
    private float currentX;
    private float currentY;

    //constructor
    public Piece(char color, int x, int y){
        if (color != 'W' && color != 'B'){
            throw new IllegalArgumentException("Color must be 'W' or 'B'"); //limits the colors to black and white
        }

        this.color = color;
        pos = new Point(x, y);

        currentX = x;
        currentY = y;
    }

    //get methods
    public Point getPos(){
        return pos;
    }

    public char getColor(){
        return color;
    }

    public BufferedImage getSprite(){
        return sprite;
    }

    public float getCurrentX(){
        return currentX;
    }

    public float getCurrentY(){
        return currentY;
    }

    //set methods
    public void setPos(int x, int y){
        pos.x = x;
        pos.y = y;
    }

    public void setColor(char color){
        if (color != 'W' && color != 'B'){
            throw new IllegalArgumentException("Color must be 'W' or 'B'");
        }
        
        this.color = color;
    }

    public void setSprite(BufferedImage sprite){
        int newSize = GameLogic.TILE_SIZE;

        //scales sprite to fit tile
        BufferedImage scaledSprite = new BufferedImage(newSize, newSize, sprite.getType());
        Graphics2D g2d = scaledSprite.createGraphics();
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(sprite, 0, 0, newSize, newSize, null);
        g2d.dispose();
        
        this.sprite = scaledSprite;
    }

    public void setCurrentPos(float x, float y){
        currentX = x;
        currentY = y;
    }

    public ArrayList<Point> getLegalTiles(Piece[][] boardPieces){
        return new ArrayList<Point>();
    }
}