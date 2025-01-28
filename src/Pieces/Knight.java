package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;
import src.Type;

public class Knight extends Piece{
    Type type;

    //constructor
    public Knight(char color, int x, int y){
        super(color, x, y);
        type = Type.KNIGHT;
        loadImage();
    }

    //loads the knight sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("C:\\Users\\tobia\\Desktop\\JavaChess\\src\\Resources\\PieceSprites\\WhiteKnight.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("C:\\Users\\tobia\\Desktop\\JavaChess\\src\\Resources\\PieceSprites\\BlackKnight.png")));
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
