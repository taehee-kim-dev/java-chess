package chess.controller.web;


import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.MoveResponseDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.service.ChessWebService;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

@Controller
public class WebController {
    private static final String ROOT = "/";
    private static final String CREATE_CHESS_ROOM = "rooms";
    private static final String CHESS_BOARD = "rooms";
    private static final String MOVE = "move";
    private static final String DELETE = "delete";
    private static final String CHESS_BOARD_VIEW = "chess-board.html";
    private static final String HOME_VIEW = "index.html";
    private static final String RESPONSE_DTO = "responseDTO";
    private static final String APPLICATION_JSON = "application/json";

    private final ChessWebService chessWebService;
    private final Gson gson;

    public WebController(ChessWebService chessWebService) {
        this.chessWebService = chessWebService;
        this.gson = new Gson();
    }

    public void run() {
        staticFiles.location("/public");
        handleHomeRequest();
        handleCreateChessRoomRequest();
        handleGetChessBoardRequest();
        handleMoveRequest();
        handleDeleteRequest();
    }

    @GetMapping(ROOT)
    public String home(Model model) throws SQLException {
        List<ChessGameResponseDTO> allRoomsIdAndTitle = chessWebService.getAllRoomsIdAndTitle();
        model.addAttribute("allChessGameRooms", allRoomsIdAndTitle);
        return "index";
    }

    @PostMapping(ROOT + CREATE_CHESS_ROOM)
    public String createChessRoomRequest(@RequestParam("room-title") String roomTitle) throws SQLException {
        Long createdChessGameId = chessWebService.createNewChessGame(roomTitle);
        return "redirect:" + ROOT + CHESS_BOARD + "?id=" + createdChessGameId;
    }

    @GetMapping(ROOT + CHESS_BOARD)
    public String getChessBoardRequest(@RequestParam("id") Long gameId, Model model) throws SQLException {
        ResponseDTO responseDTO = chessWebService.getGameStatus(gameId);
        model.addAttribute(RESPONSE_DTO, responseDTO);
        putBoardRanksToModelV2(model, responseDTO.getBoardResponseDTO());
        return CHESS_BOARD_VIEW;
    }

    private void putBoardRanksToModelV2(Model model, BoardResponseDTO boardResponseDTO) {
        model.addAttribute("rank8", boardResponseDTO.getRank8());
        model.addAttribute("rank7", boardResponseDTO.getRank7());
        model.addAttribute("rank6", boardResponseDTO.getRank6());
        model.addAttribute("rank5", boardResponseDTO.getRank5());
        model.addAttribute("rank4", boardResponseDTO.getRank4());
        model.addAttribute("rank3", boardResponseDTO.getRank3());
        model.addAttribute("rank2", boardResponseDTO.getRank2());
        model.addAttribute("rank1", boardResponseDTO.getRank1());
    }

    private void handleMoveRequest() {
        post(ROOT + MOVE, APPLICATION_JSON, (req, res) -> {
            MoveRequestDTO moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTO.class);
            MoveResponseDTO moveResponse = chessWebService.requestMove(moveRequestDTO);
            res.type(APPLICATION_JSON);
            return gson.toJson(moveResponse);
        });
    }

    private void handleDeleteRequest() {
        get(ROOT + DELETE, (req, res) -> {
            Long gameId = Long.valueOf(req.queryParams("id"));
            chessWebService.deleteGame(gameId);
            res.redirect(ROOT);
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
