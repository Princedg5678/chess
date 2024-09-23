package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    ChessGame.TeamColor color;
    ChessPiece.PieceType savedType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        color = pieceColor;
        savedType = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return color == that.color && savedType == that.savedType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, savedType);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return savedType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> ourBoard = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        PieceType tempType = piece.getPieceType();
        ChessMove newMove;

        if (tempType == PieceType.PAWN) {
            int moveDirection;
            if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) { //White
                moveDirection = 1;
            } else { //Black
                moveDirection = -1;
            }

            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol), null);
            //to add, check to see if on board
            if (board.getPiece(newMove.getEndPosition()) == null) {
                if (newMove.getEndPosition().savedRow == 1 || newMove.getEndPosition().savedRow == 8) {
                    newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol), PieceType.QUEEN);
                    ourBoard.add(newMove);
                    newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol), PieceType.BISHOP);
                    ourBoard.add(newMove);
                    newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol), PieceType.KNIGHT);
                    ourBoard.add(newMove);
                    newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol), PieceType.ROOK);
                    ourBoard.add(newMove);
                } else {
                    ourBoard.add(newMove);
                    newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection + moveDirection, myPosition.savedCol), null);
                }
                if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getRow() <= 8) {
                    if (board.getPiece(newMove.getEndPosition()) == null && (((moveDirection == 1) && (myPosition.savedRow == 2)) || ((moveDirection == -1) && (myPosition.savedRow == 7)))) {
                        ourBoard.add(newMove);
                    }
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol + 1), null);
            if ((newMove.getEndPosition().savedCol <= 8)) {
                if (board.getPiece(newMove.getEndPosition()) != null) {
                    if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                        if (newMove.getEndPosition().savedRow == 1 || newMove.getEndPosition().savedRow == 8) {
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol + 1), PieceType.QUEEN);
                            ourBoard.add(newMove);
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol + 1), PieceType.BISHOP);
                            ourBoard.add(newMove);
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol + 1), PieceType.KNIGHT);
                            ourBoard.add(newMove);
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol + 1), PieceType.ROOK);
                            ourBoard.add(newMove);
                        } else {
                            ourBoard.add(newMove);
                        }
                    }
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol - 1), null);
            if ((newMove.getEndPosition().savedCol >= 1)) {
                if (board.getPiece(newMove.getEndPosition()) != null) {
                    if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                        if (newMove.getEndPosition().savedRow == 1 || newMove.getEndPosition().savedRow == 8) {
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol - 1), PieceType.QUEEN);
                            ourBoard.add(newMove);
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol - 1), PieceType.BISHOP);
                            ourBoard.add(newMove);
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol - 1), PieceType.KNIGHT);
                            ourBoard.add(newMove);
                            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + moveDirection, myPosition.savedCol - 1), PieceType.ROOK);
                            ourBoard.add(newMove);
                        } else {
                            ourBoard.add(newMove);
                        }
                    }
                }
            }
        } else if (tempType == PieceType.ROOK) {
            for (int i = myPosition.getRow() + 1; i <= 8; i++) {
                newMove = new ChessMove(myPosition, new ChessPosition(i, myPosition.savedCol), null);
                if ((newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int j = myPosition.getColumn() + 1; j <= 8; j++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, j), null);
                if (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int k = myPosition.getRow() - 1; k >= 1; k--) {
                newMove = new ChessMove(myPosition, new ChessPosition(k, myPosition.savedCol), null);
                if ((newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int l = myPosition.getColumn() - 1; l >= 1; l--) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, l), null);
                if (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
        } else if (tempType == PieceType.KNIGHT) {
            //Up
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 2, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 2, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            //Right
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol + 2), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol + 2), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            //Down
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 2, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 2, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            //Left
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol - 2), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol - 2), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
        } else if (tempType == PieceType.BISHOP) {
            for (int i = 1; i < 9; i++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + i, myPosition.savedCol + i), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int j = 1; j < 9; j++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - j, myPosition.savedCol + j), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int k = 1; k < 9; k++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - k, myPosition.savedCol - k), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int l = 1; l < 9; l++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + l, myPosition.savedCol - l), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
        } else if (tempType == PieceType.QUEEN) {
            for (int i = myPosition.getRow() + 1; i <= 8; i++) {
                newMove = new ChessMove(myPosition, new ChessPosition(i, myPosition.savedCol), null);
                if ((newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int j = myPosition.getColumn() + 1; j <= 8; j++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, j), null);
                if (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int k = myPosition.getRow() - 1; k >= 1; k--) {
                newMove = new ChessMove(myPosition, new ChessPosition(k, myPosition.savedCol), null);
                if ((newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int l = myPosition.getColumn() - 1; l >= 1; l--) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, l), null);
                if (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int i = 1; i < 9; i++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + i, myPosition.savedCol + i), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int j = 1; j < 9; j++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - j, myPosition.savedCol + j), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int k = 1; k < 9; k++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - k, myPosition.savedCol - k), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
            for (int l = 1; l < 9; l++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + l, myPosition.savedCol - l), null);
                if (newMove.getEndPosition().savedRow > 8 || newMove.getEndPosition().savedRow < 1 || (newMove.getEndPosition().savedCol > 8 || newMove.getEndPosition().savedCol < 1)) {
                    break;
                }
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                    break;
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() == board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    break;
                }
            }
        } else if (tempType == PieceType.KING) {
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol), null);
            if (newMove.getEndPosition().getRow() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol), null);
            if (newMove.getEndPosition().getRow() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                } else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
        }
        return ourBoard;
    }
}
