package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;

public class Pawn extends Piece{
    //constructor
    public Pawn(char color, int x, int y){
        super(color, x, y);
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
        ArrayList<Point> legalTiles = new ArrayList<>();
        int x = getPos().x;
        int y = getPos().y;
        int rows = boardPieces.length;
        int cols = boardPieces[0].length;
        char color = getColor();
    
        int direction = (color == 'W') ? -1 : 1;
    
        if (y + direction >= 0 && y + direction < rows) {
            if (boardPieces[y + direction][x] == null) {
                legalTiles.add(new Point(x, y + direction));
            }
        }
    
        if ((color == 'W' && y == 6) || (color == 'B' && y == 1)) {
            if (boardPieces[y + direction][x] == null && boardPieces[y + 2 * direction][x] == null) {
                legalTiles.add(new Point(x, y + 2 * direction));
            }
        }
    
        int[] captureOffsets = {-1, 1};
        for (int offset : captureOffsets) {
            int newX = x + offset;
            int newY = y + direction;
    
            if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                Piece targetPiece = boardPieces[newY][newX];
                if (targetPiece != null && targetPiece.getColor() != color) {
                    legalTiles.add(new Point(newX, newY));
                }
            }
        }
    
        return legalTiles;
    }

    @Override
    public boolean isPromotionAvailable(){
        if (getColor() == 'W' && getPos().getY() == 0){
            return true;
        }

        if (getColor() == 'B' && getPos().getY() == 7){
            return true;
        }

        return false;
    }
}
