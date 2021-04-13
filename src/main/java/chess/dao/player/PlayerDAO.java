package chess.dao.player;


import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAO implements PlayerRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TeamColor[] teamColors, Long gameId) throws SQLException {
        String query = "INSERT INTO player (team_color, chess_game_id) VALUES (?, ?), (?, ?)";
        jdbcTemplate.update(query, teamColors[0].getValue(), gameId, teamColors[1].getValue(), gameId);
    }

    @Override
    public Long findIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor) throws SQLException {
        String query = "SELECT id FROM player WHERE chess_game_id = ? AND team_color = ?";
        return jdbcTemplate.queryForObject(
            query,
            Long.class,
            gameId, teamColor.getValue()
        );
    }

    @Override
    public void removeAllByChessGame(Long gameId) throws SQLException {
        String query = "DELETE FROM player WHERE chess_game_id = ?";
        jdbcTemplate.update(query, gameId);
    }

    @Override
    public void removeAll() throws SQLException {
        String query = "DELETE FROM player";
        jdbcTemplate.update(query);
    }
}
