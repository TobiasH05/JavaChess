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
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\WhiteBishop.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\BlackBishop.png")));
            }
        } catch (IOException exc){
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    @Override
    public ArrayList<Point> getLegalTiles(Piece[][] boardPieces){
        ArrayList<Point> legalTiles = new ArrayList<>();
        int x = getPos().x;
        int y = getPos().y;
        int rows = boardPieces.length;
        int cols = boardPieces[0].length;
    
        //check up-right
        for (int i = 1; x + i < cols && y - i >= 0; i++) {
            if (boardPieces[y - i][x + i] != null) {
                if (boardPieces[y - i][x + i].getColor() != getColor()) {
                    legalTiles.add(new Point(x + i, y - i));
                }
                break;
            } else {
                legalTiles.add(new Point(x + i, y - i));
            }
        }
    
        //check up-left
        for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
            if (boardPieces[y - i][x - i] != null) {
                if (boardPieces[y - i][x - i].getColor() != getColor()) {
                    legalTiles.add(new Point(x - i, y - i));
                }
                break;
            } else {
                legalTiles.add(new Point(x - i, y - i));
            }
        }
    
        //check down-right
        for (int i = 1; x + i < cols && y + i < rows; i++) {
            if (boardPieces[y + i][x + i] != null) {
                if (boardPieces[y + i][x + i].getColor() != getColor()) {
                    legalTiles.add(new Point(x + i, y + i));
                }
                break;
            } else {
                legalTiles.add(new Point(x + i, y + i));
            }
        }
    
        //check down-left
        for (int i = 1; x - i >= 0 && y + i < rows; i++) {
            if (boardPieces[y + i][x - i] != null) {
                if (boardPieces[y + i][x - i].getColor() != getColor()) {
                    legalTiles.add(new Point(x - i, y + i));
                }
                break;
            } else {
                legalTiles.add(new Point(x - i, y + i));
            }
        }
    
        return legalTiles;
    }
}
