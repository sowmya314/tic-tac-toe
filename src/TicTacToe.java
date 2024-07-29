import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String x = "X";
    String o = "O";
    String cur = x;

    boolean gameOver = false;

    void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().isEmpty()) continue;
            if (
                    (board[i][0].getText().equals(board[i][1].getText())) &&
                            (board[i][1].getText().equals(board[i][2].getText()))) {
                textLabel.setText(board[i][0].getText() + " Wins!");
                textLabel.setBackground(Color.green);
                textLabel.setForeground(Color.black);
                gameOver = true;
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                return;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(board[0][i].getText().isEmpty()) continue;
            if (
                   (board[0][i].getText().equals(board[1][i].getText())) &&
                   (board[1][i].getText().equals(board[2][i].getText()))) {
               textLabel.setText(board[0][i].getText() + " Wins!");
               textLabel.setBackground(Color.green);
               textLabel.setForeground(Color.black);
               gameOver = true;
               for (int j = 0; j < 3; j++) {
                   setWinner(board[j][i]);
               }
               return;
           }
        }

        if (
                (board[0][0].getText().equals(board[1][1].getText())) &&
                (board[1][1].getText().equals(board[2][2].getText())) &&
                !(board[0][0].getText().isEmpty())) {
            textLabel.setText(board[0][0].getText() + " Wins!");
            textLabel.setBackground(Color.green);
            textLabel.setForeground(Color.black);
            gameOver = true;
            setWinner(board[0][0]); setWinner(board[1][1]); setWinner(board[2][2]);
            return;
        }

        if (
                (board[0][2].getText().equals(board[1][1].getText())) &&
                        (board[1][1].getText().equals(board[2][0].getText())) &&
                        !(board[1][1].getText().isEmpty())) {
            textLabel.setText(board[1][1].getText() + " Wins!");
            textLabel.setBackground(Color.green);
            textLabel.setForeground(Color.black);
            gameOver = true;
            setWinner(board[0][2]); setWinner(board[1][1]); setWinner(board[2][0]);
        }
    }

    void setWinner(JButton j) {
        j.setForeground(Color.green);
        j.setBackground(Color.gray);

    }

    void setTie (JButton[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setForeground(Color.orange);
                grid[i][j].setBackground(Color.gray);
            }
        }
        textLabel.setText("It's a tie!");
        textLabel.setBackground(Color.orange);
        textLabel.setForeground(Color.black);
    }

    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.white);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                tile.setBackground(Color.white);
                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().isEmpty()) {
                            tile.setText(cur);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                if (turns == 9) {
                                    gameOver = true;
                                    setTie(board);
                                    return;
                                }
                                cur = (x.equals(cur)) ? o : x;
                                textLabel.setText(cur + "'s turn");
                            }
                        }

                    }
                });

                boardPanel.add(tile);

            }
        }
    }
}
