public class CellGrid {
  private final int width;
  private final int height;
  private Cell[][] cellsMatrix;

  CellGrid(final int width, final int height, int[] seedStatesArray) {
    this.width = width;
    this.height = height;
    cellsMatrix = new Cell[width][height];

    for (int row = 0; row < height; row++){
      for (int column = 0; column < width; column++){
        int noOfNeighbors = calculateNeighbours(row, column, seedStatesArray);
        cellsMatrix[column][row] =
          new Cell(
            seedStatesArray[row*width+column] == 1,
            noOfNeighbors);
      }
    }
  }

  Cell cellAt(int row, int column) {
    return cellsMatrix[row][column];
  }

  private int stateFromArrayAt(int row, int column, int[] inoutGrid) {
    if (row < 0 || row >= height){ return 0; }
    if(column < 0 || column >= width) { return 0; }
    return inoutGrid[row*width+column];
  }

  private int calculateNeighbours(int row, int column, int[] inoutGrid){
    return
      stateFromArrayAt(row-1, column-1,inoutGrid)+
      stateFromArrayAt(row-1, column,inoutGrid)+
      stateFromArrayAt(row-1, column+1,inoutGrid)+
      stateFromArrayAt(row, column-1,inoutGrid)+
      stateFromArrayAt(row, column+1,inoutGrid)+
      stateFromArrayAt(row+1, column-1,inoutGrid)+
      stateFromArrayAt(row+1, column,inoutGrid)+
      stateFromArrayAt(row+1, column+1,inoutGrid);
  }
}
