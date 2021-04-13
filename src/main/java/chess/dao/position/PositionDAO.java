package chess.dao.position;


import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDAO implements PositionRepository {
    private final JdbcTemplate jdbcTemplate;

    public PositionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Position findByFileAndRank(File file, Rank rank) throws SQLException {
        String query = "SELECT * FROM position WHERE file_value = ? AND rank_value = ?";
        return jdbcTemplate.queryForObject(
            query,
            (resultSet, rowNum) -> {
                Position position = new Position(
                    resultSet.getLong("id"),
                    resultSet.getString("file_value"),
                    resultSet.getString("rank_value")
                );
                return position;
            }, file.getValue(), String.valueOf(rank.getValue()));
    }
//    @Override
//    public Position findByFileAndRank(File file, Rank rank) throws SQLException {
//        String query = "SELECT * FROM position WHERE file_value = ? AND rank_value = ?";
//        ResultSet resultSet = SQLQuery.select(query, file.getValue(), String.valueOf(rank.getValue()));
//        if (!resultSet.next()) {
//            return null;
//        }
//        return new Position(
//            resultSet.getLong("id"),
//            resultSet.getString("file_value"),
//            resultSet.getString("rank_value"));
//    }
}
