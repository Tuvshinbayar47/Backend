package mn.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ROWS = 20;
    public static final int COLS = 10;
    public static final int BLOCK_SIZE = 30;
    private int[][] board = new int[ROWS][COLS]; // 20x10 самбар

    private Timer timer;

    private final int[][][] tetrominoes = {
        // I
        {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // O
        {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // T
        {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // S
        {
            {0, 1, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // Z
        {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // J
        {
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // L
        {
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }
    };

    private int[][] currentShape;
    private int shapeRow = 0;
    private int shapeCol = 3;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        resetCurrentShape();
        timer = new Timer(500, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (canMoveDown()) {
            shapeRow++;
        } else {
            mergeToBoard();
            clearFullLines();

            if (isGameOver()) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "GAME OVER!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else {
                resetCurrentShape();
            }
        }
        repaint();
    }

    private boolean canMoveDown() {
        for (int r = 0; r < currentShape.length; r++) {
            for (int c = 0; c < currentShape[r].length; c++) {
                if (currentShape[r][c] == 1) {
                    int newRow = shapeRow + r + 1;
                    int newCol = shapeCol + c;
                    if (newRow >= ROWS || board[newRow][newCol] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void clearFullLines() {
        for (int row = ROWS - 1; row >= 0; row--) {
            boolean isFull = true;
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 0) {
                    isFull = false;
                    break;
                }
            }

            if (isFull) {
                for (int r = row; r > 0; r--) {
                    for (int c = 0; c < COLS; c++) {
                        board[r][c] = board[r - 1][c];
                    }
                }
                for (int c = 0; c < COLS; c++) {
                    board[0][c] = 0;
                }
                row++; // Check the same row again
            }
        }
    }

    private void resetCurrentShape() {
        int index = (int) (Math.random() * tetrominoes.length);
        currentShape = tetrominoes[index];
        shapeRow = 0;
        shapeCol = 3;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Grid
        g.setColor(Color.DARK_GRAY);
        for (int r = 0; r <= ROWS; r++) {
            g.drawLine(0, r * BLOCK_SIZE, COLS * BLOCK_SIZE, r * BLOCK_SIZE);
        }
        for (int c = 0; c <= COLS; c++) {
            g.drawLine(c * BLOCK_SIZE, 0, c * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        }

        // Draw board
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 1) {
                    g.setColor(Color.BLUE);
                    int x = c * BLOCK_SIZE;
                    int y = r * BLOCK_SIZE;
                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        // Draw falling shape
        drawShape(g);
    }

    private void drawShape(Graphics g) {
        g.setColor(Color.MAGENTA);
        for (int r = 0; r < currentShape.length; r++) {
            for (int c = 0; c < currentShape[r].length; c++) {
                if (currentShape[r][c] == 1) {
                    int x = (shapeCol + c) * BLOCK_SIZE;
                    int y = (shapeRow + r) * BLOCK_SIZE;
                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.MAGENTA);
                }
            }
        }
    }

    private void mergeToBoard() {
        for (int r = 0; r < currentShape.length; r++) {
            for (int c = 0; c < currentShape[r].length; c++) {
                if (currentShape[r][c] == 1) {
                    int boardRow = shapeRow + r;
                    int boardCol = shapeCol + c;
                    if (boardRow >= 0 && boardRow < ROWS && boardCol >= 0 && boardCol < COLS) {
                        board[boardRow][boardCol] = 1;
                    }
                }
            }
        }
    }

    private int getShapeWidth(int[][] shape) {
        int maxCol = 0;
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    maxCol = Math.max(maxCol, c);
                }
            }
        }
        return maxCol + 1;
    }

    private int getShapeHeight(int[][] shape) {
        int maxRow = 0;
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    maxRow = Math.max(maxRow, r);
                }
            }
        }
        return maxRow + 1;
    }

    private boolean isGameOver() {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
        case KeyEvent.VK_LEFT:
            if (canFit1(currentShape, shapeRow, shapeCol - 1)) shapeCol--;
            break;

        case KeyEvent.VK_RIGHT:
            if (canFit1(currentShape, shapeRow, shapeCol + 1)) shapeCol++;
            break;

        case KeyEvent.VK_SPACE:
            if (canFit1(currentShape, shapeRow + 1, shapeCol)) shapeRow++;
            break;
            case KeyEvent.VK_UP:
                int[][] rotated = rotateMatrix(currentShape);
                if (canFit1(rotated, shapeRow, shapeCol)) {
                    currentShape = rotated;
                }
                break;

        }

        repaint();
    }
    private boolean canFit1(int[][] shape, int testRow, int testCol) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    int row = testRow + r;
                    int col = testCol + c;
                    if (row < 0 || row >= ROWS || col < 0 || col >= COLS || board[row][col] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean canFit(int[][] shape, int testRow, int testCol) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    int row = testRow + r;
                    int col = testCol + c;
                    if (row < 0 || row >= ROWS || col < 0 || col >= COLS || board[row][col] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private int[][] rotateMatrix(int[][] matrix) {
        int size = matrix.length;
        int[][] result = new int[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                result[c][size - 1 - r] = matrix[r][c];
            }
        }
        return result;
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
    }
}
