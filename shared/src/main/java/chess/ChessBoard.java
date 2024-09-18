package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    public ChessPiece[][] board;


    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow() - 1][position.getColumn() - 1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //White Pieces
        board[0][0] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);
        board[0][1] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);
        board[0][2] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);
        board[0][3] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN);
        board[0][4] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING);
        board[0][5] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);
        board[0][6] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);
        board[0][7] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);
        board[1][0] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][1] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][2] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][3] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][4] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][5] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][6] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        board[1][7] = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        //Black Pieces
        board[8][0] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);
        board[8][1] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);
        board[8][2] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);
        board[8][3] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN);
        board[8][4] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING);
        board[8][5] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);
        board[8][6] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);
        board[8][7] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);
        board[7][0] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][1] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][2] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][3] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][4] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][5] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][6] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        board[7][7] = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);

    }
}