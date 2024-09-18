package chess;

import java.util.Collection;

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
        ChessPiece piece = board.getPiece(myPosition);
        PieceType tempType = piece.getPieceType();
        if (tempType == PieceType.PAWN){

        }
        else if (tempType == PieceType.ROOK) {

        }
        else if (tempType == PieceType.KNIGHT) {

        }
        else if (tempType == PieceType.BISHOP) {

        }
        else if (tempType == PieceType.QUEEN) {

        }
        else if (tempType == PieceType.KING) {

        }

    }
}
