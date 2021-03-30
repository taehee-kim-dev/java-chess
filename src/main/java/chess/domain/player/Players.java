package chess.domain.player;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.dao.PlayerDAO;
import chess.dao.entity.PiecePositionEntity;
import chess.domain.player.score.ScoreCalculator;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.List;

public class Players {
    private final PlayerDAO playerDAO;
    private final ScoreCalculator scoreCalculator;

    public Players() {
        playerDAO = new PlayerDAO();
        scoreCalculator = new ScoreCalculator();
    }

    public void createAndSaveNewPlayers(Long gameId) throws SQLException {
        playerDAO.save(WHITE, gameId);
        playerDAO.save(BLACK, gameId);
    }

    public Long getPlayerIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor)
        throws SQLException {
        return playerDAO.findIdByGameIdAndTeamColor(gameId, teamColor);
    }

    public Scores getPlayersScores(Long gameId) throws SQLException {
        Long whitePlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, BLACK);
        double whitePlayerScore = playerDAO.findScoreByPlayerId(whitePlayerId);
        double blackPlayerScore = playerDAO.findScoreByPlayerId(blackPlayerId);
        return new Scores(whitePlayerScore, blackPlayerScore);
    }

    public void removeAllByChessGame(Long gameId) throws SQLException {
        playerDAO.removeAllByChessGame(gameId);
    }

    public void calculateAndUpdateScores(Long playerId, List<PiecePositionEntity> piecesPositions)
        throws SQLException {

        double score = scoreCalculator.getCalculatedScore(piecesPositions);
        playerDAO.updateScore(playerId, score);
    }
}