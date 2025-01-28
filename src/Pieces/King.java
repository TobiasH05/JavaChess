package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;
import src.Type;

public class King extends Piece{
    Type type;

    //constructor
    public King(char color, int x, int y){
        super(color, x, y);
        type = Type.KING;
        loadImage();
    }

    //loads the king sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\WhiteKing.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\BlackKing.png")));
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
    
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
    
                int newX = x + j;
                int newY = y + i;
    
                if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                    Piece targetPiece = boardPieces[newY][newX];
                    if (targetPiece == null || targetPiece.getColor() != getColor()) {
                        legalTiles.add(new Point(newX, newY));
                    }
                }
            }
        }
    
        return legalTiles;
    }
}
