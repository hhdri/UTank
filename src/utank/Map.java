package utank;

import java.util.LinkedList;
import java.util.List;

public class Map {
    List<Wall> defaultWalls, mapWalls;
    Map(int mapNumber) {
        defaultWalls = new LinkedList<>();
        defaultWalls.add(new Wall(20, 45, 435, true));
        defaultWalls.add(new Wall(20, 45, 460, false));
        defaultWalls.add(new Wall(480, 45, 435, true));
        defaultWalls.add(new Wall(20, 480, 461, false));

        mapWalls = new LinkedList<>();
        switch (mapNumber) {
            case 0:
                break;
            case 1:
                mapWalls.add(new Wall(190, 100, 110, true));
                mapWalls.add(new Wall(80, 210, 110, false));
                mapWalls.add(new Wall(310, 100, 110, true));
                mapWalls.add(new Wall(310, 210, 110, false));
                mapWalls.add(new Wall(80, 300, 110, false));
                mapWalls.add(new Wall(190, 300, 110, true));
                mapWalls.add(new Wall(310, 300, 100, false));
                mapWalls.add(new Wall(310, 300, 110, true));
                break;
            case 2:
                mapWalls.add(new Wall(100, 100, 300, false));
                mapWalls.add(new Wall(400, 100, 200, true));
                mapWalls.add(new Wall(150, 200, 200, true));
                mapWalls.add(new Wall(150, 400, 250, false));
                mapWalls.add(new Wall(250, 230, 50, false));
                mapWalls.add(new Wall(300, 230, 50, true));
                mapWalls.add(new Wall(250, 230, 50, true));
                mapWalls.add(new Wall(250, 280, 50, false));
                break;
            case 3:
                mapWalls.add(new Wall(100, 100, 80, false));
                mapWalls.add(new Wall(180, 100, 80, true));
                mapWalls.add(new Wall(100, 100, 80, true));
                mapWalls.add(new Wall(100, 180, 80, false));
                mapWalls.add(new Wall(330, 100, 80, false));
                mapWalls.add(new Wall(410, 100, 80, true));
                mapWalls.add(new Wall(330, 100, 80, true));
                mapWalls.add(new Wall(330, 180, 80, false));
                mapWalls.add(new Wall(100, 330, 80, false));
                mapWalls.add(new Wall(180, 330, 80, true));
                mapWalls.add(new Wall(100, 330, 80, true));
                mapWalls.add(new Wall(100, 410, 80, false));
                mapWalls.add(new Wall(330, 330, 80, false));
                mapWalls.add(new Wall(410, 330, 80, true));
                mapWalls.add(new Wall(330, 330, 80, true));
                mapWalls.add(new Wall(330, 410, 80, false));
                mapWalls.add(new Wall(235, 235, 40, false));
                mapWalls.add(new Wall(235, 235, 40, true));
                mapWalls.add(new Wall(275, 235, 40, true));
                mapWalls.add(new Wall(235, 275, 40, false));
                break;
            case 4:
                mapWalls.add(new Wall(400, 100, 180, true));
                mapWalls.add(new Wall(100, 250, 180, true));
                mapWalls.add(new Wall(200, 150, 80, true));
                mapWalls.add(new Wall(200, 230, 100, false));
                mapWalls.add(new Wall(300, 150, 80, true));
                mapWalls.add(new Wall(200, 330, 80, true));
                mapWalls.add(new Wall(200, 330, 100, false));
                mapWalls.add(new Wall(300, 330, 80, true));
        }
    }
}
