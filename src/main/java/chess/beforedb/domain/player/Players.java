package chess.beforedb.domain.player;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.domain.piece.Piece;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players() {
        players.addAll(Arrays.asList(new Player(BLACK), new Player(WHITE)));
    }

    public void add(Piece piece, Position position) {
        Player player = findPlayerByTeamColor(piece.getTeamColor());
        player.give(piece, position);
    }

    public void remove(Piece piece, Position position) {
        Player player = findPlayerByTeamColor(piece.getTeamColor());
        player.removePiece(piece, position);
    }

    private Player findPlayerByTeamColor(TeamColor teamColor) {
        return players.stream()
            .filter(player -> player.teamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 색깔 입니다."));
    }

    public double blackPlayerScore() {
        Player blackTeamPlayer = findPlayerByTeamColor(BLACK);
        return blackTeamPlayer.score();
    }

    public double whitePlayerScore() {
        Player whiteTeamPlayer = findPlayerByTeamColor(WHITE);
        return whiteTeamPlayer.score();
    }
}