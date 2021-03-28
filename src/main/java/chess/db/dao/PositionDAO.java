package chess.db.dao;


import static chess.db.dao.DBConnection.getConnection;

import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
import chess.db.domain.position.PositionEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDAO {

    public PositionEntity findByFileAndRank(File file, Rank rank) throws SQLException {
        String query = "SELECT * FROM position WHERE file_value = ? AND rank_value = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, file.getValue());
        pstmt.setString(2, String.valueOf(rank.getValue()));
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new PositionEntity(
            rs.getLong("id"),
            rs.getString("file_value"),
            rs.getString("rank_value"));
    }
}
