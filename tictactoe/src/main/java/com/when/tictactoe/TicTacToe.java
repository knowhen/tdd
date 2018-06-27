package com.when.tictactoe;

import java.net.UnknownHostException;

import com.when.tictactoe.mongo.TicTacToeBean;
import com.when.tictactoe.mongo.TicTacToeCollection;

public class TicTacToe {
    private TicTacToeCollection ticTacToeCollection;

    private Character[][] board = { { '\0', '\0', '\0' }, { '\0', '\0', '\0' }, { '\0', '\0', '\0' } };
    private static final int SIZE = 3;
    public static final String NO_WINNER = "No winner";
    public static final String RESULT_DRAW = "The result is draw";
    private char lastPlayer = '\0';
    private int turn = 0;

    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
    }

    public TicTacToe(TicTacToeCollection collection) {
        this.ticTacToeCollection = collection;
        if (!collection.drop()) {
            throw new RuntimeException("Dropping DB collection fialed");
        }
    }

    public TicTacToeCollection getTicTacToeCollection() {
        return ticTacToeCollection;
    }

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(new TicTacToeBean(++turn, x, y, lastPlayer));

        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return RESULT_DRAW;
        } else {
            return NO_WINNER;
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X')
            return 'O';
        return 'X';
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3)
            throw new RuntimeException("Outside board!");
    }

    private void setBox(TicTacToeBean move) {
        if (board[move.getX() - 1][move.getY() - 1] != '\0') {
            throw new RuntimeException("Box is occupied!");
        } else {
            board[move.getX() - 1][move.getY() - 1] = lastPlayer;
            if (!getTicTacToeCollection().saveMove(move)) {
                throw new RuntimeException("Saving to DB failed");
            }
        }
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * 3;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        if (horizontal == playerTotal || vertical == playerTotal || diagonal1 == playerTotal
                || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0')
                    return false;
            }
        }
        return true;
    }
}
