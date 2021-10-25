package com.anish.calabashbros;

public class World {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private MazeGenerator mg;
    private Tile<Thing>[][] tiles;
    private int[][] Maze;
    private int[] des;
    public World() {
        mg=new MazeGenerator(WIDTH);
        mg.generateMaze();
        Maze=mg.getMaze();
        des=mg.genarateDest();
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if(Maze[i][j]==1)
                    tiles[i][j].setThing(new Floor(this));
                else
                    tiles[i][j].setThing(new Wall(this));
            }
        }
        tiles[des[1]][des[0]].setThing(new Dest(this));
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }
    public Thing getOriginThing(int x,int y){
        Thing res;
        if(Maze[x][y]==1)
            res= new Floor(this);
        else
            res=new Wall(this);
        return res;
    }
    public boolean legalNode(int x,int y){
        return x >= 0 && y >= 0 && x < WIDTH && y < WIDTH && Maze[x][y]==1;
    }
    public boolean isDest(int x,int y){
        return x==des[1]&&y==des[0];
    }
    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

}
