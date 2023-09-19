import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

public class ConnectFourGUI extends JFrame{
  //The game itself
  private ConnectFour LeGame;
  //the actual screen
  private Container cp;
  //The title at the top
  private String title = "Connect Four: ";
  //width of screen
  private static final int windowWidth = 750;
  //height of screen
  private static final int windowHeight = 650;
  //number of rows on the board
  private static final int rows = 6;
  //number of columns on the board
  private static final int columns = 7;
  //an empty space on the board
  private String emptyHole = "Pieces/empty.png";
  //a space occupied by a red piece
  private String redPiece = "Pieces/red.png";
  //a space occupied by a yellow piece
  private String yellowPiece = "Pieces/yellow.png";
  //empty space as an Icon on the panel
  private ImageIcon emptyIcon = new ImageIcon(emptyHole);
  //red piece in a space as an Icon on the panel
  private ImageIcon redIcon = new ImageIcon(redPiece);
  //yellow piece in a space as an Icon on the panel
  private ImageIcon yellowIcon = new ImageIcon(yellowPiece);
  
public ConnectFourGUI(){
  emptyIcon = new ImageIcon(emptyHole);
  redIcon = new ImageIcon(redPiece);
  yellowIcon = new ImageIcon(yellowPiece);

  LeGame = new ConnectFour();

  cp = getContentPane();
  cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

  //fills the board with empty pieces
  for (int i = 0; i < rows; i++){
    for (int j = 0; j < columns; j++){
      JButton button = new JButton();
      button.setIcon(emptyIcon);
      button.setPreferredSize(new Dimension(100, 100));
      //converts row and column of box where user clicks to one value
      int coord = i * 10 + j;
      button.setName(coord + "");
      button.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent actionEvent){
          JButton square = ((JButton)(actionEvent.getSource()));
          updater(square);
        }
      });
      cp.add(button);
    }
  }
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
  boolean turn = LeGame.getTurn();
    if (!turn)
      setTitle(title + "Player 2's Turn");
    else
      setTitle(title + "Player 1's Turn");
  setLocationRelativeTo(null);
  setSize(windowWidth, windowHeight);
  setVisible(true);
}
  
  private void updater(JButton b){
    int coordNum = Integer.parseInt(b.getName());
    //int row = coordNum / 10;
    int col = coordNum%10;

    boolean turn = LeGame.getTurn();
    if (turn)
      setTitle(title + "Player 2's Turn");
    else
      setTitle(title + "Player 1's Turn");
    
    int rowAdded = LeGame.round(col);
    
    if(rowAdded != -1){
      JButton temp = ((JButton)(cp.getComponent(columns * rowAdded + col)));
      //System.out.println(columns * rowAdded + col);
      if(LeGame.getTurn())
        temp.setIcon(yellowIcon);
      else
        temp.setIcon(redIcon);
      if (LeGame.checkForWinner()){
        if (turn)
          JOptionPane.showMessageDialog(null, "Player One has won!");
        else
          JOptionPane.showMessageDialog(null, "Player Two has won!");
      }
    } else
        JOptionPane.showMessageDialog(null, "Please choose a valid position");
    //System.out.println("Row: " + row + " Col: " + col + " was clicked!");
  }
  
}

