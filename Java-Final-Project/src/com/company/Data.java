package com.company;

import java.util.ArrayList;

class Data {
    private static Data me;
    static Data getInstance() {
        if (me == null) {
            me = new Data();
        }
        return me;
    }
    private Data(){}

    enum tank_color {
        Blue, Green, Red
    }
    enum hit_wall_e {
        Vertical, Horizontal, None
    }

    private String p1_name = "Player 1", p2_name = "Player 2";
    private int p1_life = 100, p2_life = 100, map_number = 1, p1_bullet_n = 20, p2_bullet_n = 20, p1_bullet_p = 5, p2_bullet_p = 5;
    int starting_life = 100, starting_bullet = 20;
    private tank_color p1_color, p2_color;
    ArrayList<Wall> walls;

    void set_name_1(String n1) {
        p1_name = n1;
    }
    void set_name_2(String n2) { p2_name = n2; }

    void set_color_1(tank_color c1) { p1_color = c1; }
    void set_color_2(tank_color c2) { p2_color = c2; }

    void set_map(int m) {
        map_number = m;
    }

    void set_life(int l) {
        p1_life = l;
        p2_life = l;
        starting_life = l;
    }
    void set_bullet_p(int bp) {
        p1_bullet_p = bp;
        p2_bullet_p = bp;
    }
    void set_bullet_n(int bn) {
        p1_bullet_n = bn;
        p2_bullet_n = bn;
        starting_bullet = bn;
    }

    void p1_shot() { p1_bullet_n--; }
    void p2_shot() { p2_bullet_n--; }

    String get_name_1() { return this.p1_name; }
    String get_name_2() { return this.p2_name; }

    tank_color get_color_1() { return this.p1_color; }
    tank_color get_color_2() { return this.p2_color; }

    int get_map_number() { return this.map_number; }

    int get_life_1() { return this.p1_life; }
    int get_life_2() { return this.p2_life; }

    int get_p1_bullet_n() { return this.p1_bullet_n; }
    int get_p2_bullet_n() { return this.p2_bullet_n; }

    int get_bullet_p() { return this.p1_bullet_p; }

    void p1_got_shot(){ p1_life -= p2_bullet_p; }
    void p2_got_shot(){ p2_life -= p1_bullet_p; }

    void get_bullet_1() { p1_bullet_n += starting_bullet/2; }
    void get_bullet_2() { p2_bullet_n += starting_bullet/2; }

    void get_life_p1() {
        if (p1_life < starting_life/3) { p1_life += starting_life/2; }
        else { p1_life = starting_life; }
    }
    void get_life_p2() {
        if (p2_life < starting_life/3) { p2_life += starting_life/2; }
        else { p2_life = starting_life; }
    }
}
