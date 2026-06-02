class Person {
    String name;
    char symbol;

    Person(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return name + " = " + symbol;
    }
}

class Game {

    private static final int SIZE = 3;

    private final char[][] board;
    private final Person player;
    private final Person robot;
    private final Scanner scanner;

    private boolean robotTurn;
    private int turns;

    Game(char[][] board, Person player) {
        this.board = board;
        this.player = player;
        this.robot = new Person(
                "Bob",
                player.symbol == 'X' ? 'O' : 'X'
        );

        this.scanner = new Scanner(System.in);

        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
    }

    public void play() {

        while (true) {

            if (turns == 9) {
                IO.println("--------------- DRAW ---------------");
                return;
            }

            Person current = robotTurn ? robot : player;

            int move = getMove(current);

            placeMove(move, current.symbol);

            IO.println(current.name + " entered: " + move);

            printBoard();

            if (isWinner()) {
                IO.println(current.name + " won the game!");
                return;
            }

            turns++;
            robotTurn = !robotTurn;
        }
    }

    private int getMove(Person current) {
        boolean firstEntry=true;
        while (true) {

            int move;

            if (robotTurn) {
                if(firstEntry)
                    IO.println("Robot's turn");
                move = (int) (Math.random() * 9) + 1;
            } else {
                if(firstEntry) {
                    IO.println(current.name + "'s turn");
                    IO.println("Enter a value from 1 to 9:");
                }
                move = scanner.nextInt();
            }

            if (isValidMove(move)) {
                return move;
            }
            firstEntry=false;
            if (!robotTurn) {
                IO.println("Invalid move. Try again.");
            }
        }
    }

    private boolean isValidMove(int move) {

        if (move < 1 || move > 9) {
            return false;
        }

        int row = (move - 1) / SIZE;
        int col = (move - 1) % SIZE;

        return board[row][col] == '.';
    }

    private void placeMove(int move, char symbol) {

        int row = (move - 1) / SIZE;
        int col = (move - 1) % SIZE;

        board[row][col] = symbol;
    }

    private boolean isWinner() {

        // Rows
        for (int row = 0; row < SIZE; row++) {
            if (board[row][0] != '.' &&
                    board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2]) {
                return true;
            }
        }

        // Columns
        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] != '.' &&
                    board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col]) {
                return true;
            }
        }

        // Main diagonal
        if (board[1][1] != '.' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return true;
        }

        // Anti-diagonal
        return board[1][1] != '.' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0];
    }

    private void printBoard() {
        for (char[] row : board) {
            IO.println("-".repeat(13));
            IO.print("| ");
            for (char cell : row)
                IO.print(cell + " | ");
            IO.println();
        }
        IO.println("-".repeat(13));
    }
}

void main() {
    char[][] board = new char[3][3];
    Person alice = new Person("Alice", 'X');
    Game game = new Game(board, alice);
    game.play();
}