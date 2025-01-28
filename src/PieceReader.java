package src;

import java.util.ArrayList;
import src.Pieces.Piece;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import src.Pieces.Pawn;
import src.Pieces.Rook;
import src.Pieces.Knight;
import src.Pieces.Bishop;
import src.Pieces.Queen;
import src.Pieces.King;

public class PieceReader {
    private String filename;

    public PieceReader(String filename){
        this.filename = filename;
    }

    public ArrayList<Piece> fillPieceArrayList(ArrayList<Piece> pieces, char arrayListColor){
        ArrayList<String> pieceStrings = new ArrayList<String>();
        
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                pieceStrings.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String pieceString : pieceStrings){
            if (pieceString.trim().isEmpty()){
                continue;
            }

            String[] pieceAttrs = pieceString.split("\\s+");

            String type = pieceAttrs[0];
            char color = pieceAttrs[1].charAt(0);
            int x = Integer.parseInt(pieceAttrs[2]);
            int y = Integer.parseInt(pieceAttrs[3]);

            if (arrayListColor == color){
                switch (type) {
                    case "Pawn":
                        pieces.add(new Pawn(color, x, y));
                        break;
                    case "Rook":
                        pieces.add(new Rook(color, x, y));
                        break;
                    case "Knight":
                        pieces.add(new Knight(color, x, y));
                        break;
                    case "Bishop":
                        pieces.add(new Bishop(color, x, y));
                        break;
                    case "Queen":
                        pieces.add(new Queen(color, x, y));
                        break;
                    case "King":
                        pieces.add(new King(color, x, y));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown piece type: " + type);
                }
            }
        }

        return pieces;
    }
}
