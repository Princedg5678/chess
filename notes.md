"# My notes" 

To build the chess board, make a multidimensional matrix

Board is position - 1

for reset board, set the position, color and piece type.

for ChessPosition, set new variables called saveRow and saveCol

Do same thing for ChessMove. Remember that promotion is different from the other two.

Do same thing for ChessPiece. For collection, do : 

ChessPiece piece = board.getPiece(myPosition);
use getPiece on board
make a tempType using getPieceType

then make if statements for all pieces.

for pawn, set int moveDirection to 1 or -1, depending on if it is white or black. use this int for the code.

Queen is just a combination of rook and bishop, just copy and paste their code.

Hard-code the King and the Knight.

be sure to add equals and hashcode for all four classes with the Generate option.

Possibly write functions for checking things such as "IsOnBoard"
