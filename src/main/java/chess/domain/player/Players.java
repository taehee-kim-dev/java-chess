package chess.domain.player;

import chess.dao.entity.PiecePositionEntity;
import chess.dao.player.PlayerRepository;
import chess.domain.player.score.ScoreCalculator;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Players {
    private final PlayerRepository playerRepository;
    private final ScoreCalculator scoreCalculator;

    public Players(PlayerRepository playerRepository, ScoreCalculator scoreCalculator) {
        this.playerRepository = playerRepository;
        this.scoreCalculator = scoreCalculator;
    }

    public void createAndSaveNewPlayers(Long gameId) throws SQLException {
        playerRepository.save(TeamColor.values(), gameId);
    }

    public Long getPlayerIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor) throws SQLException {
        return playerRepository.findIdByGameIdAndTeamColor(gameId, teamColor);
    }

    public void removeAllByChessGame(Long gameId) throws SQLException {
        playerRepository.removeAllByChessGame(gameId);
    }

    public double getCalculatedScore(List<PiecePositionEntity> piecePositionEntities) {
        return scoreCalculator.getCalculatedScore(piecePositionEntities);
    }
}
