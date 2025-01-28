package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.Pieces.Piece;
import src.Boards.EightByEightSquare;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class GameLogic extends JPanel implements ActionListener, KeyListener, MouseListener{
    //constants
    private final int DELAY = 25;
    public static final int TILE_SIZE = 100;
    private static final long serialVersionUID = 490905409104883233L;
    private static final float ANIMATION_DURATION = 0.05f;
    private static final EightByEightSquare board = new EightByEightSquare();

    //variables
    private Timer timer;
    
    //keeps track of pieces
    private Piece[][] boardArray = new Piece[board.getCols()][board.getRows()];
    private ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    private ArrayList<Piece> whitePieces = new ArrayList<Piece>();

    private static Point selectedTile = null;

    private long lastUpdateTime = System.nanoTime();

    private PieceReader testReader = new PieceReader("src/Resources/PiecePositions/DefaultPosition.txt");

    // private boolean whiteCheck = false;
    // private boolean blackCheck = false;

    //constructor
    public GameLogic(){
        setPreferredSize(new Dimension(TILE_SIZE * board.getCols(), TILE_SIZE * board.getRows()));
        setBackground(new Color(235, 236, 208));
        timer = new Timer(DELAY, this);
        timer.start();

        addMouseListener(this);

        whitePieces = testReader.fillPieceArrayList(whitePieces, 'W');
        blackPieces = testReader.fillPieceArrayList(blackPieces, 'B');
    }

    //happens every frame
    @Override
    public void actionPerformed(ActionEvent e){
        //handles animaton of pieces
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastUpdateTime) / 1_000_000_000.0f;
        lastUpdateTime = currentTime;

        for (Piece piece : whitePieces){
            if (!updatePiecePosition(piece, deltaTime)){
                resetBoardArray();
                updateBoardArray(whitePieces);
                updateBoardArray(blackPieces);
            }
        }

        for (Piece piece : blackPieces){
            if (!updatePiecePosition(piece, deltaTime)){
                resetBoardArray();
                updateBoardArray(whitePieces);
                updateBoardArray(blackPieces);
            }
        }

        repaint(); //redraws everything every frame
    }

    //paints background and pieces
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        board.draw(g);
        drawPieces(g, this, whitePieces);
        drawPieces(g, this, blackPieces);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    //fires event when mouse is pressed
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int col = e.getX() / TILE_SIZE;
            int row = e.getY() / TILE_SIZE;

            if (col < 0 || col >= board.getCols() || row < 0 || row >= board.getRows()) {
                return;
            }

            Piece clickedPiece = boardArray[row][col];

            if (selectedTile != null) {
                int selectedCol = selectedTile.x;
                int selectedRow = selectedTile.y;
                Piece selectedPiece = boardArray[selectedRow][selectedCol];

                if (selectedPiece != null) {
                    if (clickedPiece == null) {
                        handleMove(selectedPiece, col, row, (selectedPiece.getColor() == 'W') ? blackPieces : whitePieces);
                        selectedTile = null;
                    } else if (clickedPiece.getColor() != selectedPiece.getColor()) {
                        handleMove(selectedPiece, col, row, (selectedPiece.getColor() == 'W') ? blackPieces : whitePieces);
                        selectedTile = null;
                    } else {
                        if(clickedPiece != null){
                            selectedTile = new Point(col, row);
                        }
                    }
                } else {
                    selectedTile = null;
                }
            } else {
                if(clickedPiece != null){
                    selectedTile = new Point(col, row);
                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            selectedTile = null;
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }

    //renders and draws all pieces on the board
    private void drawPieces(Graphics g, ImageObserver observer, ArrayList<Piece> pieces){
        for (Piece piece : pieces){
            int x = Math.round(piece.getCurrentX() * GameLogic.TILE_SIZE);
            int y = Math.round(piece.getCurrentY() * GameLogic.TILE_SIZE);

            g.drawImage(
                piece.getSprite(),
                x,
                y,
                observer
            );
        }
    }

    //help method for updateBoardArray()
    private void resetBoardArray(){
        for (int i = 0; i < boardArray.length; i++){
            for (int j = 0; j < boardArray[i].length; j++){
                boardArray[i][j] = null;
            }
        }
    }

    //updates the board array for the selected group of pieces
    private void updateBoardArray(ArrayList<Piece> pieces){
        for (Piece piece : pieces){
            boardArray[piece.getPos().y][piece.getPos().x] = piece;
        }
    }

    //handles the event that a piece moves
    private void handleMove(Piece piece, int col, int row, ArrayList<Piece> opponentPieces) {
        Piece targetPiece = boardArray[row][col];

        if (targetPiece != null) {
            if (opponentPieces.contains(targetPiece)) {
                opponentPieces.remove(targetPiece);
            }
        }

        piece.setPos(col, row);
        resetBoardArray();
        updateBoardArray(whitePieces);
        updateBoardArray(blackPieces);
    }

    //animates the move of a piece
    private boolean updatePiecePosition(Piece piece, float deltaTime){
        float targetX = piece.getPos().x;
        float targetY = piece.getPos().y;

        float currentX = piece.getCurrentX();
        float currentY = piece.getCurrentY();

        float speed = 1 / ANIMATION_DURATION;
        float dx = (targetX - currentX) * speed * deltaTime;
        float dy = (targetY - currentY) * speed * deltaTime;

        if (Math.abs(dx) > 0.01 || Math.abs(dy) > 0.01){
            piece.setCurrentPos(currentX + dx, currentY + dy);
            return true;
        }else{
            piece.setCurrentPos(targetX, targetY);
            return false;
        }
    }

    //returns the selected tile
    public static Point getSelectedTile(){
        return selectedTile;
    }

    //returns TILE_SIZE
    public static int getTileSize(){
        return TILE_SIZE;
    }
}