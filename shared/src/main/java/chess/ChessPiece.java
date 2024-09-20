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
        if (tempType == PieceType.PAWN){

        }
        else if (tempType == PieceType.ROOK) {
            for (int i = myPosition.getRow(); i < 7; i++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + i, myPosition.savedCol), null);
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            for (int j = myPosition.getColumn(); j < 7; j++) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, myPosition.savedCol + j), null);
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            for (int  k = myPosition.getRow(); k > 0; k--) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - k, myPosition.savedCol), null);
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            for (int l = myPosition.getColumn(); l > 0; l--) {
                newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, myPosition.savedCol - l), null);
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
        }
        else if (tempType == PieceType.KNIGHT) {

        }
        else if (tempType == PieceType.BISHOP) {

        }
        else if (tempType == PieceType.QUEEN) {
            for (int i = 0; i < 7; i++){
                for (int j = 0; j < 7; j++) {
                    newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + i, myPosition.savedCol + j), null);

                }
            }
        }
        else if (tempType == PieceType.KING) {
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol), null);
            if (newMove.getEndPosition().getRow() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol + 1), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() <= 8) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol), null);
            if (newMove.getEndPosition().getRow() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow - 1, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getRow() >= 1 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
            newMove = new ChessMove(myPosition, new ChessPosition(myPosition.savedRow + 1, myPosition.savedCol - 1), null);
            if (newMove.getEndPosition().getRow() <= 8 && newMove.getEndPosition().getColumn() >= 1) {
                if (board.getPiece(newMove.getEndPosition()) == null) {
                    ourBoard.add(newMove);
                }
                else if (board.getPiece(newMove.getEndPosition()).getTeamColor() != board.getPiece(newMove.getStartPosition()).getTeamColor()) {
                    ourBoard.add(newMove);
                }
            }
        }
        return ourBoard;
    }
}
