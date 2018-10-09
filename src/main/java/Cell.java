public class Cell {
  private final boolean cellStatus;
  private final int noOfNeighbors;

  Cell(boolean cellStatus, int noOfNeighbors) {

    this.cellStatus = cellStatus;
    this.noOfNeighbors = noOfNeighbors;
  }

  boolean isAlive() {
    return cellStatus;
  }

  Cell nextGeneration() {
    boolean newCellStatus = cellStatus;
    if(noOfNeighbors < 2 || noOfNeighbors > 3) {
      newCellStatus = false;
    }
    if(noOfNeighbors == 3){
      newCellStatus = true;
    }
    return new Cell(newCellStatus, noOfNeighbors);
  }
}
