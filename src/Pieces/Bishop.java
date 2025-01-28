package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;

import src.Type;

public class Bishop extends Piece{
    Type type;

    //constructor
    public Bishop(char color, int x, int y){
        super(color, x, y);
        type = Type.BISHOP;
        loadImage();
    }

    //loads the bishop sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("C:\\Users\\tobia\\Desktop\\JavaChess\\src\\Resources\\PieceSprites\\WhiteBishop.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("C:\\Users\\tobia\\Desktop\\JavaChess\\src\\Resources\\PieceSprites\\BlackBishop.png")));
            }
        } catch (IOException exc){
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public ArrayList<Point> getLegalTiles(Piece[][] boardPieces){
        ArrayList<Point> legalTiles = new ArrayList<Point>();

        for (int i = 0; i < boardPieces.length; i++){
            for (int j = 0; j < boardPieces[i].length; j++){
                legalTiles.add(new Point(i, j));
            }
        }

        return legalTiles;
    }
}
