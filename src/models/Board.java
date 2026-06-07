package models;

public class Board {
        private int boardId;
        private Character [][] board;
        private int [] rowArr;
        private int [] colArr;
        private int antiDig;
        private int dig;

        public Board(int boardId){
            this.boardId = boardId;
            this.board = new Character[3][3];
            this.rowArr = new int[3];
            this.colArr = new int[3];
            this.antiDig = 0;
            this.dig = 0;
        }

    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        this.board = board;
    }

    public int[] getRowArr() {
        return rowArr;
    }

    public void setRowArr(int[] rowArr) {
        this.rowArr = rowArr;
    }

    public int[] getColArr() {
        return colArr;
    }

    public void setColArr(int[] colArr) {
        this.colArr = colArr;
    }

    public int getAntiDig() {
        return antiDig;
    }

    public void setAntiDig(int antiDig) {
        this.antiDig = antiDig;
    }

    public int getDig() {
        return dig;
    }

    public void setDig(int dig) {
        this.dig = dig;
    }


    public void setValue(int row, int col, Character symbol){
            this.board[row][col] = symbol;
            if(symbol == 'x'){

                this.rowArr[row]++;
                this.colArr[col]++;
                if(row == col){
                    this.dig++;
                }
                if(row + col == 2){
                    this.antiDig++;
                }
            }else{

                this.rowArr[row]--;
                this.colArr[col]--;
                if(row == col){
                    this.dig--;
                }
                if(row + col == 2){
                    this.antiDig--;
                }
            }
    }

    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                // If cell is null, print empty space
                String value = (board[i][j] == null) ? " " : board[i][j].toString();

                System.out.print(" " + value + " ");

                // Vertical separator
                if (j < 2) {
                    System.out.print("|");
                }
            }

            System.out.println();

            // Horizontal separator
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }
}
