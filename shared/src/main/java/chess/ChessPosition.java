package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    int savedRow;
    int savedCol;

    @Override
    public String toString() {
        return "ChessPosition{" +
                "savedRow=" + savedRow +
                ", savedCol=" + savedCol +
                '}';
    }

    public ChessPosition(int row, int col) {
        savedRow = row;
        savedCol = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return savedRow;
    }
    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return savedCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return savedRow == that.savedRow && savedCol == that.savedCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(savedRow, savedCol);
    }
}
