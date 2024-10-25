package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard myBoard;
    private TeamColor currentColor;

    public ChessGame() {
        myBoard = new ChessBoard();
        currentColor = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return currentColor;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        currentColor = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    public ChessBoard copyBoard(ChessBoard board){
        ChessBoard copy = new ChessBoard();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                ChessPosition tempPosition = new ChessPosition(i,j);
                copy.addPiece(tempPosition ,board.getPiece(tempPosition));
            }
        }
        return copy;
    }


    public boolean pretendMove(ChessMove move){
        ChessBoard pretendBoard = copyBoard(myBoard);
        ChessPosition currentPosition = move.getStartPosition();
        ChessPosition endPosition =  move.getEndPosition();
        ChessPiece pretendPiece = pretendBoard.getPiece(currentPosition);
        if (pretendBoard.getPiece(endPosition) == null) {
            pretendBoard.addPiece(endPosition, pretendPiece);
            pretendBoard.removePiece(currentPosition);
            return !isInCheck(currentColor);
        }
        else {
            return false;
        }
    }


    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> potentialMoves = myBoard.getPiece(startPosition).pieceMoves(getBoard(),startPosition);
        ArrayList<ChessMove> validMoves = new ArrayList<>();
        for (ChessMove move : potentialMoves){
            if (pretendMove(move)){
                validMoves.add(move);
            }
        }
        return validMoves;

    }

    /*
    public boolean checkPiece(ChessMove move, ChessPiece piece){
        ChessPosition currentPosition = move.getStartPosition();
        ChessPosition endPosition =  move.getEndPosition();
    }
    */

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition currentPosition = move.getStartPosition();
        ChessPosition endPosition =  move.getEndPosition();
        ChessPiece piece = myBoard.getPiece(currentPosition);
        Collection<ChessMove> ourMoves = piece.pieceMoves(myBoard, currentPosition);

        if (ourMoves.contains(move)) {
            if (pretendMove(move)) {
                myBoard.addPiece(endPosition, piece);
                myBoard.removePiece(currentPosition);
            }
            else {
                throw new InvalidMoveException();
            }
        }
        else {
            throw new InvalidMoveException();
        }
    }


    public ChessPosition findKing(TeamColor teamColor) {
        ChessPosition tempPosition;
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                tempPosition = new ChessPosition(i, j);
                if (myBoard.getPiece(tempPosition).getPieceType() == ChessPiece.PieceType.KING
                        && myBoard.getPiece(tempPosition).getTeamColor() == teamColor){
                    return tempPosition;
                }
            }
        }
        return null;
    }

    public Set<ChessMove> opponentMoves(TeamColor teamColor) {
        HashSet<ChessMove> moveList = new HashSet<ChessMove>();
        ArrayList<ChessPosition> piecePositions = findPositions(teamColor);

        for (ChessPosition position : piecePositions){
            ChessPiece piece = new ChessPiece(myBoard.getPiece(position).getTeamColor(),
                    myBoard.getPiece(position).getPieceType());
            moveList.addAll(piece.pieceMoves(myBoard, position));
        }
        return  moveList;
    }

    //add special case for pawns

    public ArrayList<ChessPosition> findPositions (TeamColor teamColor){

        ChessPosition tempPosition;
        ArrayList<ChessPosition> piecePositions = new ArrayList<ChessPosition>();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++) {
                tempPosition = new ChessPosition(i, j);
                if (myBoard.getPiece(tempPosition).getPieceType() != null
                        && myBoard.getPiece(tempPosition).getTeamColor() != teamColor) {
                    piecePositions.add(tempPosition);
                }
            }
        }
        return piecePositions;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        Collection<ChessMove> opponentsMoves;
        opponentsMoves = opponentMoves(teamColor);
        ChessPosition kingPosition = findKing(teamColor);

        for (ChessMove move : opponentsMoves){
            if (move.getEndPosition() == kingPosition){
                return true;
            }
        }
        return false;

    }


    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        ArrayList<ChessMove> tempCount;
        tempCount = new ArrayList<>();
        if (isInCheck(teamColor)){
            for (int i = 1; i < 9; i++){
                for (int j = 1; j < 9; j++){
                    if (myBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor) {
                        tempCount.addAll(validMoves(new ChessPosition(i,j)));
                    }
                }
            }
            if (tempCount.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        ArrayList<ChessMove> tempCount;
        tempCount = new ArrayList<>();
        if (!isInCheck(teamColor)){
            for (int i = 1; i < 9; i++){
                for (int j = 1; j < 9; j++){
                    if (myBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor) {
                        tempCount.addAll(validMoves(new ChessPosition(i,j)));

                    }
                }
            }
            if (tempCount.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        myBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return myBoard;
    }

}