package chess.db.dao;


import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.beforedb.domain.player.type.TeamColor;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public PlayerEntity save(PlayerEntity playerEntity) throws SQLException {
        ResultSet generatedKeys = getResultSet(playerEntity);
        if (generatedKeys.next()) {
            return new PlayerEntity(
                generatedKeys.getLong(1),
                playerEntity.getTeamColor(),
                playerEntity.getChessGameEntity());
        }
        throw new SQLException("playerEntity를 save()할 수 없습니다.");
    }

    private ResultSet getResultSet(PlayerEntity playerEntity) throws SQLException {
        String query = "INSERT INTO player (chess_game_id, team_color) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setLong(1, playerEntity.getChessGameEntity().getId());
        pstmt.setString(2, playerEntity.getTeamColor().getValue());
        pstmt.executeUpdate();
        return pstmt.getGeneratedKeys();
    }

    public Long findIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor) throws SQLException {
        ResultSet rs = getResultSetOfPlayer(gameId, teamColor);
        if (!rs.next()) {
            return null;
        }
        return rs.getLong("id");
    }

    private ResultSet getResultSetOfPlayer(Long gameId, TeamColor teamColor) throws SQLException {
        String query = "SELECT id FROM player WHERE chess_game_id = ? AND team_color = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gameId);
        pstmt.setString(2, teamColor.getValue());
        return pstmt.executeQuery();
    }

    public List<Long> findAllPlayerIdsByChessGameId(Long gameId) throws SQLException {
        List<Long> playerIds = new ArrayList<>();
        ResultSet rs = getResultSet(gameId);
        while (rs.next()) {
            playerIds.add(rs.getLong("id"));
        }
        return playerIds;
    }

    private ResultSet getResultSet(Long gameId) throws SQLException {
        String query = "SELECT player.id FROM player WHERE chess_game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gameId);
        return pstmt.executeQuery();
    }

    public List<PlayerEntity> findAllByChessGame(ChessGameEntity chessGameEntity)
        throws SQLException {
        List<PlayerEntity> players = new ArrayList<>();
        ResultSet rs = getResultSet(chessGameEntity);
        while (rs.next()) {
            players.add(new PlayerEntity(
                rs.getLong("id"),
                TeamColor.of(rs.getString("team_color")),
                chessGameEntity)
            );
        }
        return players;
    }

    private ResultSet getResultSet(ChessGameEntity chessGameEntity) throws SQLException {
        String query = "SELECT * FROM player WHERE chess_game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, chessGameEntity.getId());
        return pstmt.executeQuery();
    }

    public double findScoreByPlayerId(Long playerId) throws SQLException {
        String query = "SELECT score FROM player WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("해당 아이디의 플레이어를 찾을 수 없습니다.");
        }
        return rs.getDouble("score");
    }

    public void updateScore(Long playerId, double score) throws SQLException {
        String query = "UPDATE player SET score = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        pstmt.setDouble(2, score);
        pstmt.executeUpdate();
    }

    public void removeAll() throws SQLException {
        String query = "DELETE FROM player";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void removeAllByChessGame(ChessGameEntity chessGameEntity) throws SQLException {
        String query = "DELETE FROM player WHERE chess_game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, chessGameEntity.getId());
        pstmt.executeUpdate();
    }
}
