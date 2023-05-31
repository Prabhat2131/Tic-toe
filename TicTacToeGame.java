import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame {
    private JButton[] buttons;
    private boolean player1Turn;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setLocation(500, 200);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        player1Turn = true;

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Raleway", Font.BOLD, 40));
            buttons[i].addActionListener(new ButtonClickListener());
            add(buttons[i]);
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if (player1Turn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }

            clickedButton.setEnabled(false);
            player1Turn = !player1Turn;

            if (checkForWin()) {
                String winner = player1Turn ? "Player 2 (O)" : "Player 1 (X)";
                JOptionPane.showMessageDialog(null, "Game Over! " + winner + " wins!");
                resetGame();
            } else if (checkForDraw()) {
                JOptionPane.showMessageDialog(null, "Game Over! It's a draw!");
                resetGame();
            }
        }
    }

    private boolean checkForWin() {
        String[] board = new String[9];

        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2]) && !board[i].equals("")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6]) && !board[i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].equals("")) ||
                (board[2].equals(board[4]) && board[2].equals(board[6]) && !board[2].equals(""))) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicTacToeGame game = new TicTacToeGame();
                game.setVisible(true);
            }
        });
    }
}
