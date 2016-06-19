package com.example.tictactoegamehw;

public class TicTakToe {


    public enum CellValue {X, O, EMPTY}

    public enum MoveResult {VICTORY, DRAW, VALID_MOVE, INVALID_MOVE}

    private CellValue[] board;
    private int moveCount;
    private boolean isXTurn;

    public TicTakToe() {
        board = new CellValue[9];
        resetGame();
    }

    public void resetGame() {
        for (int i = 0; i < board.length; i++) {
            board[i] = CellValue.EMPTY;
        }
        moveCount = 0;
        isXTurn = true;
    }

    public MoveResult makeMove(int cell) {
        if (cell < 1 || cell > 9)
            return MoveResult.INVALID_MOVE;
        cell--;
        if (board[cell] == CellValue.EMPTY) {
            moveCount++;
            board[cell] = isXTurn ? CellValue.X : CellValue.O;
            isXTurn = !isXTurn;
            if (moveCount >= 5 && checkVictory(cell))
                return MoveResult.VICTORY;
            if (moveCount == 9)
                return MoveResult.DRAW;
            return MoveResult.VALID_MOVE;
        }
        return MoveResult.INVALID_MOVE;
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    private boolean checkVictory(int cell) {
        int row = cell / 3;
        int column = cell % 3;
        if (board[column] == board[column + 3] && board[column] == board[column + 6])
            return true;
        row *= 3;
        if (board[row] == board[row + 1] && board[row] == board[row + 2])
            return true;
        if (cell % 2 == 0) {
            boolean diagonal1 = board[0] != CellValue.EMPTY && board[0] == board[4] && board[0] == board[8];
            if (diagonal1)
                return true;
            boolean diagonal2 = board[2] != CellValue.EMPTY && board[2] == board[4] && board[0] == board[6];
            if (diagonal2)
                return true;
        }
        return false;
    }
}
