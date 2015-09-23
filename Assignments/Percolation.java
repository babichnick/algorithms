import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private enum Tile {
        BLOCKED,
        OPEN,
        FULL
    }
    
    private Tile[][] tiles;
    
    private WeightedQuickUnionUF unionFind;
    
    public Percolation(int n) {
        
        tiles     = new Tile[n][n];
        unionFind = new WeightedQuickUnionUF(n * n + 2);
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                tiles[r][c] = Tile.BLOCKED;
            }
        }
    }
    
    public void open(int i, int j) {
        
        tiles[i - 1][j - 1] = Tile.OPEN;
        
        if (i == 1) {
            unionFind.union(j - 1, tiles.length * tiles.length);
        }
        
        if (i == tiles.length) {
            unionFind.union((tiles.length - 1) * tiles.length + (j - 1), tiles.length * tiles.length + 1);
        }
        
        // top
        unionTo(i, j, i - 1, j);
        
        // right
        unionTo(i, j, i, j + 1);
        
        // bottom
        unionTo(i, j, i + 1, j);
        
        // left
        unionTo(i, j, i, j - 1);
    }
    
    private void unionTo(int i, int j, int i2, int j2) {
        
        if (i2 < 1 || i2 > tiles.length || j2 < 1 || j2 > tiles.length) {
            return;
        }
        
        if (isBlocked(i2, j2)) {
            return;
        }
        
        unionFind.union(
            (i - 1) * tiles.length + (j - 1),
            (i2 - 1) * tiles.length + (j2 - 1)
        );
    }
    
    private boolean isBlocked(int i, int j) {
        
        if (i < 1 || i > tiles.length || j < 1 || j > tiles.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        return tiles[i - 1][j - 1] == Tile.BLOCKED;
    }
    
    public boolean isOpen(int i, int j) {
        
        if (i < 1 || i > tiles.length || j < 1 || j > tiles.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        return tiles[i - 1][j - 1] == Tile.OPEN;
    }
    
    public boolean isFull(int i, int j) {
        
        if (i < 1 || i > tiles.length || j < 1 || j > tiles.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        boolean connected = unionFind.connected(
            tiles.length * tiles.length,
            (i - 1) * tiles.length + (j - 1)
        );
        
        boolean isOpen = isOpen(i, j);
        
        return connected && isOpen;
    }
    
    public boolean percolates() {
        
        return unionFind.connected(
            tiles.length * tiles.length,
            (tiles.length * tiles.length) + 1
        );
    }
}