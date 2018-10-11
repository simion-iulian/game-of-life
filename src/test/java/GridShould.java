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
    "2,2,"+
      "0;1;" +
      "0;0",
    "4,4,"+
      "0;1;0;0;" +
      "0;0;0;0;" +
      "0;0;0;0;" +
      "0;0;0;0"
  })
  public void
  give_correct_number_of_neighbors(int width, int height, @ConvertWith(StringArrayConverter.class) String[] initial) {
    int[] seedStatesArray = intArrayFrom(initial);

    CellGrid grid = new CellGrid(width, height, seedStatesArray);
    assertThat(grid.cellAt(1,0).numberOfNeighbors(), is(0));
    assertThat(grid.cellAt(0,0).numberOfNeighbors(), is(1));
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
}
