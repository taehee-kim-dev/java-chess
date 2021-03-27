package chess.db.domain.position;

import chess.db.dao.PositionDAO;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PositionEntitiesCache {
    private static final PositionDAO positionDAO = new PositionDAO();
    private static final List<PositionEntity> positionEntities = new ArrayList<>();

    private PositionEntitiesCache() {
    }

    static {
        try {
            cachePositions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cachePositions() throws SQLException {
        List<Rank> ranks = Arrays.asList(Rank.values());
        List<Rank> reversedRanks = ranks.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        for (Rank rank : reversedRanks) {
            cachingPositionsOfRank(rank);
        }
    }

    private static void cachingPositionsOfRank(Rank rank) throws SQLException {
        for (File file : File.values()) {
            PositionEntity positionEntity = positionDAO.findByFileAndRank(file, rank);
            positionEntities.add(positionEntity);
        }
    }

    public static PositionEntity find(File file, Rank rank) {
        return positionEntities.stream()
            .filter(positionEntity ->
                    positionEntity.getFile() == file && positionEntity.getRank() == rank)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 입니다."));
    }

    public static PositionEntity get(int index) {
        return positionEntities.get(index);
    }
}
