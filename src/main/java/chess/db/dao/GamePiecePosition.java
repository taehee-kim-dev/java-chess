package chess.db.dao;

public class GamePiecePosition {
    private final Long playerPiecePositionId;
    private Long positionId;

    public GamePiecePosition(Long playerPiecePositionId, Long positionId) {
        this.playerPiecePositionId = playerPiecePositionId;
        this.positionId = positionId;
    }

    public Long getPlayerPiecePositionId() {
        return playerPiecePositionId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
