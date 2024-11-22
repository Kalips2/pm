import java.util.Random;

public class Maze {

  public static int MAZE_WIDTH = 30; // Ширина лабіринту
  public static int MAZE_HEIGHT = 30; // Висота лабіринту

  private final int[][] maze = generateMaze(MAZE_WIDTH, MAZE_HEIGHT);

  public int[][] getMaze() {
    return maze;
  }

  private int[][] generateMaze(int width, int height) {
    int[][] maze = new int[height][width];

    // Заповнюємо лабіринт стінами
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        maze[i][j] = 1;
      }
    }

    // Рекурсивна функція для генерації лабіринту
    generateMazeRecursive(maze, 1, 1, width - 2, height - 2);

    return maze;
  }

  private void generateMazeRecursive(int[][] maze, int x, int y, int width, int height) {
    if (width < 2 || height < 2) {
      return;
    }

    // Вибираємо випадковий початковий напрямок
    int[] directions = {1, 2, 3, 4};
    shuffleArray(directions);

    for (int direction : directions) {
      int dx = 0;
      int dy = 0;

      if (direction == 1) { // Вгору
        dy = -2;
      } else if (direction == 2) { // Вниз
        dy = 2;
      } else if (direction == 3) { // Вліво
        dx = -2;
      } else if (direction == 4) { // Вправо
        dx = 2;
      }

      int newX = x + dx;
      int newY = y + dy;

      if (newX > 0 && newX < maze[0].length && newY > 0 && newY < maze.length &&
          maze[newY][newX] == 1) {
        // Створення проходу
        maze[y + dy / 2][x + dx / 2] = 0;
        maze[newY][newX] = 0;
        generateMazeRecursive(maze, newX, newY, width - Math.abs(dx), height - Math.abs(dy));
      }
    }
  }

  private void shuffleArray(int[] array) {
    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--) {
      int index = random.nextInt(i + 1);
      int temp = array[i];
      array[i] = array[index];
      array[index] = temp;
    }
  }
}
