package src.Boards;

public abstract class Board {
    //variables
    private int cols;
    private int rows;

    //constructor
    public Board(){
        
    }

    //returns nr. of columns
    public int getCols(){
        return cols;
    }

    //returns nr. of rows
    public int getRows(){
        return rows;
    }

    //sets nr. of columns
    public void setCols(int cols){
        this.cols = cols;
    }

    //sets nr. of rows
    public void setRows(int rows){
        this.rows = rows;
    }
}
