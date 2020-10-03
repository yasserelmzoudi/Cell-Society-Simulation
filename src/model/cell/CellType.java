package model.cell;

public enum CellType {
  NONE {
    @Override
    public Cell createCell(int minx, int miny, int width, int height) {
      return null;
    }
  },

  ALIVE {
    @Override
    public AliveCell createCell(int minx, int miny, int width, int height) {
      return new AliveCell(minx, miny, width, height);
    }
  },
  DEAD {
    @Override
    public DeadCell createCell(int minx, int miny, int width, int height) {
      return new DeadCell(minx, miny, width, height);
    }
  };

}
