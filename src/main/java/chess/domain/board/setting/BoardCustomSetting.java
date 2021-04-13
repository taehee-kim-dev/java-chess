package chess.domain.board.setting;

import chess.domain.piece.type.PieceWithColorType;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BoardCustomSetting extends BoardSetting {
    public BoardCustomSetting(List<PieceWithColorType> piecesSetting) {
        super(piecesSetting);
    }
}
