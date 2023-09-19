public class Board{
  private static final int rows = 6;
  private static final int columns = 7;
  Piece [][] gameBoard = new Piece [rows][columns];

  public Board(){
    for (int i = 0; i < rows; i++){
      for (int j = 0; j < columns; j++){
        gameBoard[i][j] = null;
      }
    }
  }

  public int getRows(){
    return rows;
  }

  public int getColumns(){
    return columns;
  }
  
  public int addPiece(int col, String c){
    //checks if row user inputs is valid
    if (col >= 0 && col < columns){
      //checks if column is empty
      if (gameBoard[0][col] == null){
        boolean pieceAdded = false;
        int addedRow = -1;
        for (int i = rows - 1; i >= 0; i--)
          if (gameBoard[i][col] == null){
            gameBoard[i][col] = new Piece();
            gameBoard[i][col].setColor(c);
            pieceAdded = true;
            addedRow = i;
            break;
          }
          return addedRow;
      } else 
          return -1;      
    } else
        return -1;
  }
  
  public void printBoard(){
    for (int i = 0; i < rows; i++){
      System.out.print("|");
      for (int j = 0; j < columns; j++){
        if (gameBoard[i][j] == null)
          System.out.print("_");
        else
          System.out.print(gameBoard[i][j].getColor());
        System.out.print("|");
      }
      System.out.println();
    }
  }  
  
  //true if there is a winner and false otherwise.
  public boolean checkForWinner(String c){
    return checkForHorizontalWin(c) || checkForVerticalWin(c) ||  checkForDiagonalWin(c);
  }

  public boolean checkForHorizontalWin(String c){
    for (int i = 0; i < rows; i++){
      int count = 0;
      for (int j = 0; j < columns; j++){
        if (gameBoard[i][j] != null && gameBoard[i][j].getColor().equals(c)){
          count++;
          if (count == 4){
            return true;
          }
        } else
            count = 0;
      }
    }
    return false;
  }
  public boolean checkForVerticalWin (String c){
    for (int i = 0; i < columns; i++){
      int count = 0;
      for (int j = 0; j < rows; j++){
        if (gameBoard[j][i] != null && gameBoard[j][i].getColor().equals(c)){
          count++;
          if (count == 4){
            return true;
          }
        } else
            count = 0;
      }
    }
    return false;
  }
  public boolean checkForDiagonalWin (String c){
    //Starting at top left and going down right
    for (int i = 0; i <= rows - 4; i++){
      for (int j = 0; j <= columns - 4; j++){
        Piece temp1 = gameBoard[i][j];
        Piece temp2 = gameBoard[i + 1][j + 1];
        Piece temp3 = gameBoard[i + 2][j + 2];
        Piece temp4 = gameBoard[i + 3][j + 3];
        if (temp1 != null && temp2 != null && temp3 != null && temp4 != null && temp1.getColor().equals(c) && temp2.getColor().equals(c) && temp3.getColor().equals(c) && temp4.getColor().equals(c)){
            return true;
          }
      }
    }
    //Starting at bottom left and going up right
    for (int i = rows - 3; i < rows; i++){
      for (int j = 0; j <= columns - 4; j++){
        Piece temp1 = gameBoard[i][j];
        Piece temp2 = gameBoard[i - 1][j + 1];
        Piece temp3 = gameBoard[i - 2][j + 2];
        Piece temp4 = gameBoard[i - 3][j + 3];
        if (temp1 != null && temp2 != null && temp3 != null && temp4 != null && temp1.getColor().equals(c) && temp2.getColor().equals(c) && temp3.getColor().equals(c) && temp4.getColor().equals(c)){
            return true;
          }
      }
    }
    //Starting at top right and going down 
    
    return false;
  }
  
}