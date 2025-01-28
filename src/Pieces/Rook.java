package src.Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import javax.imageio.ImageIO;

public class Rook extends Piece{
    //constructor
    public Rook(char color, int x, int y){
        super(color, x, y);
        loadImage();
    }

    //loads the rook sprite
    private void loadImage(){
        try {
            if (getColor() == 'W'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\WhiteRook.png")));
            }

            if (getColor() == 'B'){
                setSprite(ImageIO.read(new File("src\\Resources\\PieceSprites\\BlackRook.png")));
            }
        } catch (IOException exc){
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    @Override
    public ArrayList<Point> getLegalTiles(Piece[][] boardPieces){
        ArrayList<Point> legalTiles = new ArrayList<Point>();
        int x = getPos().x;
        int y = getPos().y;
        int rows = boardPieces.length;
        int cols = boardPieces[0].length;

        //check up
        for (int i = getPos().y - 1; i >= 0; i--){
            if (boardPieces[i][x] != null){
                if (boardPieces[i][x].getColor() != getColor()){
                    legalTiles.add(new Point(x, i));
                }
                break;
            }else{
                legalTiles.add(new Point(x, i));
            }
        }

        //check down
        for (int i = getPos().y + 1; i < rows; i++){
            if (boardPieces[i][x] != null){
                if (boardPieces[i][x].getColor() != getColor()){
                    legalTiles.add(new Point(x, i));
                }
                break;
            }else{
                legalTiles.add(new Point(x, i));
            }
        }

        //check right
        for (int i = getPos().x + 1; i < cols; i++){
            if (boardPieces[y][i] != null){
                if (boardPieces[y][i].getColor() != getColor()){
                    legalTiles.add(new Point(i, y));
                }
                break;
            }else{
                legalTiles.add(new Point(i, y));
            }
        }

        //check left
        for (int i = getPos().x - 1; i >= 0; i--){
            if (boardPieces[y][i] != null){
                if (boardPieces[y][i].getColor() != getColor()){
                    legalTiles.add(new Point(i, y));
                }
                break;
            }else{
                legalTiles.add(new Point(i, y));
            }
        }

        return legalTiles;
    }
}
