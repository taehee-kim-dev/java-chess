package chess.domain.board.setting;

import static chess.utils.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.ChessGame;
import chess.domain.position.type.File;
import chess.utils.DBCleaner;
import chess.utils.position.converter.PositionConverter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDefaultSettingTest {
    private List<String> cellsStatus;
    private List<String> blackPiecesExceptPawns;
    private List<String> whitePiecesExceptPawns;

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @BeforeEach
    void setUp() throws SQLException {
        ChessGame chessGame = new ChessGame();
        Long gameId = chessGame.createNew(new BoardDefaultSetting(), TEST_TITLE);
        cellsStatus = chessGame.getBoardStatus(gameId).getCellsStatus();
        blackPiecesExceptPawns = Arrays.asList("R", "N", "B", "Q", "K", "B", "N", "R");
        whitePiecesExceptPawns = Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r");
    }

    @DisplayName("보드 기본 세팅")
    @Test
    void BoardDefaultSetting() {
        for (int i = 0; i < 8; i++) {
            String fileValue = File.values()[i].getValue();

            assertThat(
                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "8"))
            ).isEqualTo(blackPiecesExceptPawns.get(i));

            assertThat(
                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "7"))
            ).isEqualTo("P");

            assertEmptyCellsByFile(fileValue);

            assertThat(
                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "2"))
            ).isEqualTo("p");

            assertThat(
                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "1"))
            ).isEqualTo(whitePiecesExceptPawns.get(i));
        }
    }

    private void assertEmptyCellsByFile(String fileValue) {
        for (int rankOrder = 3; rankOrder < 7; rankOrder++) {
            assertThat(
                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + rankOrder))
            ).isEqualTo(".");
        }
    }
}
