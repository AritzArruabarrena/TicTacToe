import java.util.Scanner;
/**
 * The main class for the Tic-Tac-Toe (Console-OO, non-graphics version)
 * It acts as the overall controller of the game.
 */
public class GameMain {
   // Define properties
   /** The game board */
   private Board board;
   /** The current state of the game (of enum State) */
   private State currentState;
   /** The current player (of enum Seed) */
   private Seed  currentPlayer;

   private static Scanner in = new Scanner(System.in);

   /** Constructor to setup the game */
   public GameMain() {
      // Perform one-time initialization tasks
      initGame();

      // Reset the board, currentStatus and currentPlayer
      newGame();

      // Play the game once
      do {
         // The currentPlayer makes a move.
         // Update cells[][] and currentState1
         stepGame();
         // Refresh the display
         board.paint();
         // Print message if game over
         if (currentState == State.CROSS_WON) {
            System.out.println("'X'-ak irabazi du!\nAgur!");
         } else if (currentState == State.NOUGHT_WON) {
            System.out.println("'O'-ak irabazi du!\nAgur!");
         } else if (currentState == State.DRAW) {
            System.out.println("Empatea izan da!\nAgur!");
         }
         // Switch currentPlayer
         currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
      } while (currentState == State.PLAYING);  // repeat until game over
   }

   /** Perform one-time initialization tasks */
   public void initGame() {
      board = new Board();  // allocate game-board
   }

   /** Reset the game-board contents and the current states, ready for new game */
   public void newGame() {
      board.newGame();  // clear the board contents
      currentPlayer = Seed.CROSS;   // CROSS plays first
      currentState = State.PLAYING; // ready to play
   }

   /** The currentPlayer makes one move.
       Update cells[][] and currentState. */
   public void stepGame() {
      boolean validInput = false;  // for validating input
      do {
         String icon = currentPlayer.getIcon();
         System.out.print("'" + icon + "' jokalaria, sartu ezazu zure mugimendua [1-3] ilara eta [1-3] zutabeen artean zenbakiak espazio batez bananduta): ");
         int row = in.nextInt() - 1;   // [0-2]
         int col = in.nextInt() - 1;
         if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
               && board.cells[row][col].content == Seed.NO_SEED) {
            // Update cells[][] and return the new game state after the move
            currentState = board.stepGame(currentPlayer, row, col);
            validInput = true; // input okay, exit loop
         } else {
            System.out.println("(" + (row + 1) + "," + (col + 1)
                  + ") mugimenduak ez du balio saiatu berriz [1-3] ilara eta [1-3] zutabeen artean zenbakiak espazio batez bananduta");
         }
      } while (!validInput);   // repeat until input is valid
   }

   /** The entry main() method */
   public static void main(String[] args) {
      new GameMain();  // Let the constructor do the job
   }
}