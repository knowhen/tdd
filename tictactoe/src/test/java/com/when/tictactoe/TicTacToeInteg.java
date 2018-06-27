package com.when.tictactoe;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;

import org.junit.Test;

public class TicTacToeInteg {

    @Test
    public void givenMongoDbIsRunningWhenPlayThenNoException() throws UnknownHostException {
        TicTacToe ticTacToe = new TicTacToe();
        assertEquals(TicTacToe.NO_WINNER, ticTacToe.play(1, 1));
    }
}
