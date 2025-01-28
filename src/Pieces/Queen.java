package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;
import src.Type;

public class Queen extends Piece{
    Type type;

    //constructor
    public Queen(char color, int x, int y){
        super(color, x, y);
        type = Type.QUEEN;
        loadImage();
    }

    //loads the queen sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\WhiteQueen.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\BlackQueen.png")));
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
    
        int[] directions = {-1, 0, 1};
    
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) continue;
    
                int newX = x;
                int newY = y;
    
                while (true) {
                    newX += j;
                    newY += i;
    
                    if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                        Piece targetPiece = boardPieces[newY][newX];
                        if (targetPiece == null) {
                            legalTiles.add(new Point(newX, newY));
                        } else {
                            if (targetPiece.getColor() != getColor()) {
                                legalTiles.add(new Point(newX, newY));
                            }
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    
        return legalTiles;
    }
}
