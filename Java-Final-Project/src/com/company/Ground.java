package com.company;

import java.util.ArrayList;


class Ground {

    private static Ground me;
    static Ground getInstance() {
        if (me == null) {
            me = new Ground();
        }
        return me;
    }
    private Ground(){}

    void loadMap(int mapNo) {
        switch (mapNo) {
            case 1:
                map1();
                break;
            case 2:
                map2();
                break;
            case 3:
                map3();
                break;
            case 4:
                map4();
                break;
            case 5:
                map5();
                break;
            case 6:
                map6();
                break;
        }
    }

    private void map1() {
        Data data = Data.getInstance();
        data.walls = new ArrayList<>(14);

        data.walls.add(new Wall(0, 95, 1000, 10));

        data.walls.add(new Wall(150,100,10,250));
        data.walls.add(new Wall(150,150, 150, 10));
        data.walls.add(new Wall(300,150,10,150));
        data.walls.add(new Wall(150, 450,10,300));
        data.walls.add(new Wall(150,750,150,10));
        data.walls.add(new Wall(0,450,150,10));
        data.walls.add(new Wall(700,700,10,150));
        data.walls.add(new Wall(850,700,10,300));
        data.walls.add(new Wall(700,850,150,10));
        data.walls.add(new Wall(850,250,10,300));
        data.walls.add(new Wall(850,250,150,10));
        data.walls.add(new Wall(700,550,160,10));
        data.walls.add(new Wall(425,425,150,150));
    }
    private void map2(){
        Data data = Data.getInstance();
        data.walls = new ArrayList<>(13);

        data.walls.add(new Wall(0, 95, 1000, 10));

        data.walls.add(new Wall(150,100,10,350));
        data.walls.add(new Wall(300,100,10,250));
        data.walls.add(new Wall(150,450,300,10));
        data.walls.add(new Wall(850,700,10,300));
        data.walls.add(new Wall(550,700,300,10));
        data.walls.add(new Wall(700,550,300,10));
        data.walls.add(new Wall(300,450,10,300));
        data.walls.add(new Wall(0,600,150,10));
        data.walls.add(new Wall(850,100,10,300));
        data.walls.add(new Wall(0,850,450,10));
        data.walls.add(new Wall(450,100,10,200));
        data.walls.add(new Wall(450,200,300,10));
    }

    private void map3(){
        Data data = Data.getInstance();
        data.walls = new ArrayList<>(12);

        data.walls.add(new Wall(0, 95, 1000, 10));

        data.walls.add(new Wall(300,100,10,235));
        data.walls.add(new Wall(150,330,160,10));
        data.walls.add(new Wall(700,700,10,300));
        data.walls.add(new Wall(700,700,150,10));
        data.walls.add(new Wall(500,400,10,200));
        data.walls.add(new Wall(400,500,400,10));
        data.walls.add(new Wall(150,450,10,150));
        data.walls.add(new Wall(0,600,160,10));
        data.walls.add(new Wall(500,200,150,10));
        data.walls.add(new Wall(650,200,10,430));
        data.walls.add(new Wall(150,800,380,10));
    }

    private void map4(){
        Data data = Data.getInstance();
        data.walls = new ArrayList<>(13);

        data.walls.add(new Wall(0, 95, 1000, 10));

        data.walls.add(new Wall(0,300,500,10));
        data.walls.add(new Wall(500,850,500,10));
        data.walls.add(new Wall(500,300,10,200));
        data.walls.add(new Wall(500,650,10,200));
        data.walls.add(new Wall(500, 500,150,10));
        data.walls.add(new Wall(350,650,150,10));
        data.walls.add(new Wall(650,500,10,150));
        data.walls.add(new Wall(350,500,10,150));
        data.walls.add(new Wall(200,500,150,10));
        data.walls.add(new Wall(650,650,150,10));
        data.walls.add(new Wall(0,850,300,10));
        data.walls.add(new Wall(700,300,300,10));

    }

    private void map5(){
        Data data = Data.getInstance();
        data.walls = new ArrayList<>(10);

        data.walls.add(new Wall(0, 95, 1000, 10));

        data.walls.add(new Wall(150,100,10,200));
        data.walls.add(new Wall(350,100,10,200));
        data.walls.add(new Wall(850,200,10,200));
        data.walls.add(new Wall(600,200,10,350));
        data.walls.add(new Wall(500,450,200,10));
        data.walls.add(new Wall(200,550,410,10));
        data.walls.add(new Wall(600,700,400,10));
        data.walls.add(new Wall(0,650,250,10));
        data.walls.add(new Wall(250,650,10,200));
    }

    private void map6() {
        Data data = Data.getInstance();
        data.walls = new ArrayList<>(9);

        data.walls.add(new Wall(0, 95, 1000, 10));

        data.walls.add(new Wall(150,500,700,10));
        data.walls.add(new Wall(300,200,10,300));
        data.walls.add(new Wall(700,500,10, 350));
        data.walls.add(new Wall(700,100,10,200));
        data.walls.add(new Wall(700, 300,300,10));
        data.walls.add(new Wall(0,700,300,10));
        data.walls.add(new Wall(300, 700, 10,300));
        data.walls.add(new Wall(500,350,10,300));
    }
}