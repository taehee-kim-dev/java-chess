package chess.db.domain.piece;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;

import chess.db.dao.PieceFromDB;
import chess.db.domain.board.BoardForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.domain.piece.type.Direction;
import chess.domain.piece.type.PieceType;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.type.TeamColor;
import java.util.List;
import java.util.Objects;

public class PieceEntity {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final double score;
    private final List<Direction> directions;

    public PieceEntity(Long id, PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.id = id;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public PieceEntity(PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.id = null;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public static PieceEntity castedFrom(PieceFromDB pieceFromDB) {
        return castToConcretePieceObject(pieceFromDB);
    }

    private static PieceEntity castToConcretePieceObject(PieceFromDB pieceFromDB) {
        PieceEntity castedPieceEntity = getPieceEntityHalf(pieceFromDB);
        if (castedPieceEntity != null) {
            return castedPieceEntity;
        }
        return getPieceEntityTheOtherHalf(pieceFromDB);
    }

    private static PieceEntity getPieceEntityHalf(PieceFromDB pieceFromDB) {
        if (pieceFromDB.getPieceType() == PAWN) {
            return new PawnEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == ROOK) {
            return new RookEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == KNIGHT) {
            return new KnightEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        return null;
    }

    private static PieceEntity getPieceEntityTheOtherHalf(PieceFromDB pieceFromDB) {
        if (pieceFromDB.getPieceType() == BISHOP) {
            return new BishopEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == QUEEN) {
            return new QueenEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == KING) {
            return new KingEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        throw new IllegalArgumentException("PieceFromDB -> 구체 Piece 클래스 캐스팅 실패");
    }

    public static PieceEntity of(PieceWithColorType pieceWithColorType) {
        return PieceEntitiesCache.find(pieceWithColorType.type(), pieceWithColorType.color());
    }

    public static PieceEntity of(PieceType pieceType, TeamColor teamColor) {
        return PieceEntitiesCache.find(pieceType, teamColor);
    }

    public boolean canMoveTo(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        Direction moveDirection = moveRouteForDB.getDirection();
        if (isNotCorrectDirection(moveDirection)
            || boardForDB.isAnyPieceExistsOnRouteBeforeDestination(moveRouteForDB)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        if (boardForDB.isOwnPieceExistsInCell(moveRouteForDB.getDestination(), teamColor)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }

    protected boolean isNotCorrectDirection(Direction moveCommandDirection) {
        return !directions.contains(moveCommandDirection);
    }

    public Long getId() {
        return id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return pieceType.getName(teamColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceEntity)) {
            return false;
        }
        PieceEntity piece = (PieceEntity) o;
        return pieceType == piece.pieceType && teamColor == piece.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, teamColor);
    }
}