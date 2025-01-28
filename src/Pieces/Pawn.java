package src.Pieces;

import src.Type;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;

public class Pawn extends Piece{
    Type type;

    //constructor
    public Pawn(char color, int x, int y){
        super(color, x, y);
        type = Type.PAWN;
        loadImage();
    }

    //loads the pawn sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\WhitePawn.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\BlackPawn.png")));
            }
        } catch (IOException exc){
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    @Override
    public ArrayList<Point> getLegalTiles(Piece[][] boardPieces){
        ArrayList<Point> legalTiles = new ArrayList<Point>();

        for (int i = 0; i < boardPieces.length; i++){
            for (int j = 0; j < boardPieces[i].length; j++){
                legalTiles.add(new Point(i, j));
            }
        }

        if (getColor() == 'W'){

        }else{
            
        }

        return legalTiles;
    }
}
