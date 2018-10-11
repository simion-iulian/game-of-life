import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/*
https://www.codewars.com/kata/conways-game-of-life
 */

public class GridShould {
  @ParameterizedTest
  @CsvSource({
    //Input seed matrix 1
    "4,4,"+
      "0;1;0;0;" +
      "0;0;0;0;" +
      "0;0;0;0;" +
      "0;0;0;0,"+
    //Expected neighbors matrix 1
    "1;0;1;0;" +
    "1;1;1;0;" +
    "0;0;0;0;" +
    "0;0;0;0",

    //Input seed matrix 2
    "4,4,"+
      "0;1;0;0;" +
      "0;1;0;0;" +
      "0;0;1;0;" +
      "0;0;0;0,"+
    //Expected neighbors matrix 2
    "2;1;2;0;" +
    "2;2;3;1;" +
    "1;2;1;1;" +
    "0;1;1;1"
  })
  public void
  give_correct_number_of_neighbors(
    int width, int height,
    @ConvertWith(StringArrayConverter.class) String[] seed,
    @ConvertWith(StringArrayConverter.class) String[] expectedNeighborsStrings) {

    int[] seedStatesArray = intArrayFrom(seed);
    int[] expectedNeighbors = intArrayFrom(expectedNeighborsStrings);
    CellGrid grid = new CellGrid(width, height, seedStatesArray);

    for (int x = 0; x < height; x++){
      for (int y = 0; y < width; y++){
        assertThat(grid.cellAt(x, y).numberOfNeighbors(), is(expectedNeighbors[matrixPositionInArray(width, x, y)]));
      }
    }
  } @ParameterizedTest
  @CsvSource({
    //Input seed matrix
    "4,4,"+
      "0;1;0;0;" +
      "0;1;0;0;" +
      "0;0;1;0;" +
      "0;0;0;0,"+
    //Expected generation matrix
    "0;0;0;0;" +
    "0;1;1;0;" +
    "0;0;0;0;" +
    "0;0;0;0"
  })
  public void
  check_for_next_generation(
    int width, int height,
    @ConvertWith(StringArrayConverter.class) String[] seed,
    @ConvertWith(StringArrayConverter.class) String[] expectedNeighborsStrings) {

    int[] seedStatesArray = intArrayFrom(seed);
    int[] expectedGeneration = intArrayFrom(expectedNeighborsStrings);
    CellGrid seedGrid = new CellGrid(width, height, seedStatesArray);

    CellGrid nextGrid = seedGrid.nextGeneration();

    for (int x = 0; x < height; x++){
      for (int y = 0; y < width; y++){
        boolean expectedCellStatus = expectedGeneration[matrixPositionInArray(width, x, y)] == 1;
        assertThat(nextGrid.cellAt(x, y).isAlive(), is(expectedCellStatus) );
      }
    }
  }

  @ParameterizedTest
  @CsvSource({
    "2,2,"+
      "0;1;" +
      "0;0"
  })
  public void
  initialize_input_zeros_to_false_and_ones_to_true(int width, int height, @ConvertWith(StringArrayConverter.class) String[] initial) {
    int[] seedStatesArray = intArrayFrom(initial);

    CellGrid grid = new CellGrid(width, height, seedStatesArray);
    assertThat(grid.cellAt(1,0).isAlive(), is(true));
    assertThat(grid.cellAt(0,0).isAlive(), is(false));
    assertThat(grid.cellAt(0,1).isAlive(), is(false));
    assertThat(grid.cellAt(1,1).isAlive(), is(false));
  }

  private int[] intArrayFrom(String[] array) {
    return Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
  }

  private int matrixPositionInArray(int width, int x, int y) {
    return y*width+x;
  }
}
