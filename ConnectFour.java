import java.util.Scanner;

public class ConnectFour{
  private Board gameBoard;
  private String player1Color;
  private String player2Color;
  //if true, it is player1's turn
  //if false, player2's turn
  private boolean turn;
  
  public ConnectFour(){
    gameBoard = new Board();
    player1Color = "R"; //R = Red in GUI
    player2Color = "Y"; //Y = Yellow in GUI
    turn = ((int) (Math.random() * 2) == 0);
  }

  public boolean getTurn(){
    return turn;
  }

  //Should return the column where the user's piece is, or -1 if piece was not placed
  public int round(int col){
    int row = -1;
    
    String color = "";
    if (turn){
      color = player1Color;
    } else {
      color = player2Color;
    }

    //System.out.println("Round: " + color);
    row = gameBoard.addPiece(col, color);   
    
    if (row != -1)
      turn = !turn;
    return row;
  }
  /*
  public void startGame(){
    boolean running = true;
    Scanner scan = new Scanner(System.in);
    
    while (running){
      gameBoard.printBoard();
      String color = "";
      if (turn){
        color = player1Color;
        System.out.println("Player 1's Turn");
      } else {
        color = player2Color;
        System.out.println("Player 2's Turn");
      }
      System.out.println("Which column would you like to place your piece in?");
      System.out.println("Type a number from 1 to 7");
      int column = scan.nextInt() - 1;
      int turnDone;
      if (turn)
        turnDone = gameBoard.addPiece(column, player1Color);
      else
        turnDone = gameBoard.addPiece(column, player2Color);
      if (turnDone != -1){
        if(checkForWinner()){
          gameBoard.printBoard();
          if (turn)
            System.out.println("Player 1 Wins!");
          else
            System.out.println("Player 2 Wins!");
          
          System.out.println("Play again?");
          String y = scan.nextLine();
          y = y.toLowerCase();
          if (y.equals("yes"))
            resetGame();
          else
            running = false;
        }
        turn = !turn;
      } else
        System.out.println("Piece could not be added. Try again.");
    }
  }
  */
  public boolean checkForWinner(){
    String winColor = "";
    if (!turn)
      winColor = player1Color;
    else
      winColor = player2Color;
    return gameBoard.checkForWinner(winColor);
  }
  public void resetGame(){
    gameBoard = new Board();
    turn = ((int) (Math.random() * 2) == 0);
  }
}