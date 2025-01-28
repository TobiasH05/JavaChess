package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;

public class Knight extends Piece{
    //constructor
    public Knight(char color, int x, int y){
        super(color, x, y);
        loadImage();
    }

    //loads the knight sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\WhiteKnight.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\BlackKnight.png")));
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
    
        int[] moveOffsets = {-2, -1, 1, 2};
    
        for (int i : moveOffsets) {
            for (int j : moveOffsets) {
                if (Math.abs(i) != Math.abs(j)) {
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
        }
    
        return legalTiles;
    }
}
