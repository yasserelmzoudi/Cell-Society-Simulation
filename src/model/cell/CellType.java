package model.cell;

public enum CellType {
  NONE {
    public AliveCell createCell(int minx, int miny, int width, int height) {
      return null;
    }
  },

  ALIVE {
    public AliveCell createCell(int minx, int miny, int width, int height) {
      return new AliveCell(minx, miny, width, height);
    }
  },
  DEAD {
    public DeadCell createCell(int minx, int miny, int width, int height) {
      return new DeadCell(minx, miny, width, height);
    }
  };
}
