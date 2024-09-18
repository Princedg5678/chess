"# My notes" 

To build the chess board, make a multidimensional matrix

Board is position - 1

board[][] = new ChessPiece(ChessGame.TeamColor.(Color),ChessPiece.PieceType.(Piece Type))

for ChessPosition, set new variables called saveRow and saveCol

Do same thing for ChessMove. Remember that promotion is different from the other two.

Do same thing for ChessPiece. For collection, do : 

ChessPiece piece = board.getPiece(myPosition);
PieceType tempType = piece.getPieceType();

then make if statements for all pieces.
