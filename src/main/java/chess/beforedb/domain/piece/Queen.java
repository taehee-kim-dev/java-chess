package chess.beforedb.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.QUEEN;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class Queen extends Piece {
    private static final double SCORE = 9;

    public Queen(TeamColor teamColor) {
        super(QUEEN, teamColor, SCORE, Direction.queenDirections());
    }
}