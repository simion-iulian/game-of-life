import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CellShould {
  @ParameterizedTest
  @CsvSource({
    "true, 1, false",
    "true, 2, true",
    "true, 3, true",
    "false, 3, true",
    "true, 4, false",
    "false, 1, false",
    "false, 2, false",
  })
  void
  die_with_fewer_than_two_live_neighbors(boolean initialStatus, int noOfNeighbors, boolean expectedStatus) {
    Cell cell = new Cell(initialStatus, noOfNeighbors);

    Cell newCell = cell.nextGeneration();

    assertThat(newCell.isAlive(), is(expectedStatus));
  }
}
