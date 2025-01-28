package src.Boards;

import java.awt.Color;
import java.awt.Graphics;

import src.GameLogic;

public class EightByEightSquare extends Board{
    //constructor
    public EightByEightSquare(){
        super.setCols(8);
        super.setRows(8);
    }

    //draws the board
    public void draw(Graphics g){
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(new Color(235, 236, 208));
                } else {
                    g.setColor(new Color(119, 149, 86));
                }

                //colors the selected tile yellow
                if (GameLogic.getSelectedTile() != null && GameLogic.getSelectedTile().getX() == col && GameLogic.getSelectedTile().getY() == row) {
                    if ((GameLogic.getSelectedTile().getX() + GameLogic.getSelectedTile().getY()) % 2 == 0){
                        g.setColor(new Color(245, 246, 130));
                    }else{
                        g.setColor(new Color(185, 202, 67));
                    }
                }

                g.fillRect(col * GameLogic.getTileSize(), row * GameLogic.getTileSize(), GameLogic.getTileSize(), GameLogic.getTileSize());
            }
        }
    }
}
