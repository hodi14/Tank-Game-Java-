package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


class GamePlay extends JFrame {
    private Tank t1 = new Tank(250, 250);
    private Tank t2 = new Tank(750, 750);

    private ArrayList<Bullet> bullets = new ArrayList<>();

    private Bullet_p bp1 = new Bullet_p(0, 0); private Bullet_p bp2 = new Bullet_p(0, 0);
    private Life life;
    private Shield shield;

    private int bullet_timer = 2000;
    private int life_shield_timer = 3000, life_shield_rand;

    private boolean right_1, left_1, move_1;
    private boolean right_2, left_2, move_2;
    private boolean paused = false;

    private Random rand = new Random();

    private JLabel p1_life = new JLabel(), p2_life = new JLabel(), heart1 = new JLabel(), heart2 = new JLabel();
    private JLabel p1_bullet = new JLabel(), p2_bullet = new JLabel(), bullet1 = new JLabel(), bullet2 = new JLabel();
    private JLabel shield1 = new JLabel(), shield2 = new JLabel();
    private JLabel paused_lbl = new JLabel();

    private Timer game_time = new Timer(5, actionEvent -> repaint());

    GamePlay() {
        switch (Data.getInstance().get_color_1().toString()) {
            case "Blue":
                t1.c = Color.BLUE;
                break;
            case "Red":
                t1.c = Color.RED;
                break;
            case "Green":
                t1.c = Color.GREEN;
                break;
        }
        switch (Data.getInstance().get_color_2().toString()) {
            case "Blue":
                t2.c = Color.BLUE;
                break;
            case "Red":
                t2.c = Color.RED;
                break;
            case "Green":
                t2.c = Color.GREEN;
                break;
        }

        KeyListener move_wasd = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == 'W' || c == 'w') {
                    move_1 = true;
                }
                if (c == 'a' || c == 'A') {
                    left_1 = true;
                }
                if (c == 'd' || c == 'D') {
                    right_1 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (Data.getInstance().get_p1_bullet_n() > 0 && Data.getInstance().get_life_1() > 0) {
                        Bullet b = new Bullet((int) t1.x, (int) t1.y, t1.direction);
                        b.c = t1.c;
                        bullets.add(b);
                        Data.getInstance().p1_shot();
                        ShotSound();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    paused = !paused;
                    if (paused) {
                        paused_lbl.setText("PAUSED");
                        game_time.stop();
                    }
                    else {
                        paused_lbl.setText("");
                        game_time.start(); }
                }
                e.consume();
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == 'W' || c == 'w') {
                    move_1 = false;
                }
                if (c == 'a' || c == 'A') {
                    left_1 = false;
                }
                if (c == 'd' || c == 'D') {
                    right_1 = false;
                }
                e.consume();
            }
        };
        this.addKeyListener(move_wasd);

        KeyListener move_arrow = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    move_2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    left_2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    right_2 = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (Data.getInstance().get_p2_bullet_n() > 0 && Data.getInstance().get_life_2() > 0) {
                        Bullet b = new Bullet((int) t2.x, (int) t2.y, t2.direction);
                        b.c = t2.c;
                        bullets.add(b);
                        Data.getInstance().p2_shot();
                        ShotSound();
                    }
                }
                e.consume();
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    move_2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    left_2 = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    right_2 = false;
                }
                e.consume();
            }
        };
        this.addKeyListener(move_arrow);

        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        paused_lbl.setBounds(380, 2, 240, 50);
        paused_lbl.setForeground(Color.LIGHT_GRAY);
        paused_lbl.setFont(new Font(paused_lbl.getFont().getName(), paused_lbl.getFont().getStyle(), 50));
        this.add(paused_lbl);

        if (!paused) { game_time.start(); }
        else { game_time.stop(); }

        Ground.getInstance().loadMap(Data.getInstance().get_map_number());
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (t1.has_shield || t2.has_shield)
            shield.time_limit();
        if (shield != null && shield.time <= 0) {
            t1.has_shield = false;
            t2.has_shield = false;
            shield2.setText("");
            shield1.setText("");
        }

        if (life_shield_timer == 0) {
            life_shield_timer = 3000;
            if (life_shield_rand % 2 == 1 && life != null)
                life.on_map = false;
            if (life_shield_rand % 2 == 0 && shield != null)
                shield.on_map = false;
        }
        life_shield_timer--;

        if (life_shield_timer == 2600) {
            life_shield_rand = rand.nextInt(4);
            if (life_shield_rand % 2 == 1) { make_life(); }
            if (life_shield_rand % 2 == 0) { make_shield(); }
        }
        if (shield != null && life_shield_rand % 2 == 0)
            if (shield.on_map) {
                shield.paint(g);
                shield.growOld();
            }
        if (life != null && life_shield_rand % 2 == 1)
            if (life.on_map) {
                life.paint(g);
                life.growOld();
            }


        if (bullet_timer == 0) {
            bullet_timer = 2000;
            bp1.on_map = false;
            bp2.on_map = false;
        }
        bullet_timer--;

        if (bullet_timer == 1000) { make_bp(); }
        if (bp1.on_map) {
            bp1.paint(g, t1.c);
            bp1.growOld();
        }
        if (bp2.on_map) {
            bp2.paint(g, t2.c);
            bp2.growOld();
        }


        if (Data.getInstance().get_life_1() <= 0) {
            WiningSound();
            new GameOver(Data.getInstance().get_name_2(), t2.c).show();
            this.dispose();
        }
        if (Data.getInstance().get_life_2() <= 0) {
            WiningSound();
            new GameOver(Data.getInstance().get_name_1(), t1.c).show();
            this.dispose();
        }

        boolean pass_wall_1 = true;
        boolean pass_wall_2 = true;
        for (Wall w: Data.getInstance().walls) {
            w.paint(g);

            if (t1.hit_wall(w)) { pass_wall_1 = false; }
            if (t2.hit_wall(w)) { pass_wall_2 = false; }
        }

        if (move_1 && t1.can_move() && pass_wall_1) { t1.move(); }
        if(left_1) { t1.turn_left(); }
        if(right_1) { t1.turn_right(); }

        if (move_2 && t2.can_move() && pass_wall_2) { t2.move(); }
        if(left_2) { t2.turn_left(); }
        if(right_2) { t2.turn_right(); }

        if (Data.getInstance().get_life_1() > 0) { t1.paint(g); }
        if (Data.getInstance().get_life_2() > 0) { t2.paint(g); }

        for (Bullet b: bullets) {

            if (b.hit_tank(t1) && b.c == t2.c && b.on_map && !t1.has_shield) {
                Data.getInstance().p1_got_shot();
                b.on_map = false;
                hitTankSound();
            }
            if (b.hit_tank(t1) && b.c == t2.c && b.on_map && t1.has_shield) {
                b.on_map = false;
            }

            if (b.hit_tank(t2) && b.c == t1.c && b.on_map && !t2.has_shield) {
                Data.getInstance().p2_got_shot();
                b.on_map = false;
                hitTankSound();
            }
            if (b.hit_tank(t2) && b.c == t1.c && b.on_map && t2.has_shield) {
                b.on_map = false;
            }

            for (Wall w : Data.getInstance().walls) {
                switch (b.hit_wall(w)) {
                    case Horizontal:
                        b.direction = Math.PI - b.direction;
                        hitWallSound();
                        break;
                    case Vertical:
                        b.direction = -b.direction;
                        hitWallSound();
                        break;
                }
            }
            if (b.on_map) {
                b.move();
                b.growOld();
                b.paint(g);
            }
        }

        if (life_shield_rand % 2 == 1 && life != null && life.on_map) {
            if (t1.hit_life(life)) {
                Data.getInstance().get_life_p1();
                LifeSound();
                life.on_map = false;
            }
            if (t2.hit_life(life)) {
                Data.getInstance().get_life_p2();
                LifeSound();
                life.on_map = false;
            }
        }
        if (life_shield_rand % 2 == 0 && shield != null && shield.on_map) {
            if (t1.hit_shield(shield)) {
                t1.has_shield = true;
                ShieldSound();
                shield.time = 1000;
                shield.on_map = false;
            }
            if (t2.hit_shield(shield)) {
                t2.has_shield = true;
                ShieldSound();
                shield.time = 1000;
                shield.on_map = false;
            }
        }

        if (t1.hit_bullet(bp1) && bp1.on_map) {
            Data.getInstance().get_bullet_1();
            ReloadSound();
            bp1.on_map = false;
        }
        if (t2.hit_bullet(bp2) && bp2.on_map) {
            Data.getInstance().get_bullet_2();
            ReloadSound();
            bp2.on_map = false;
        }

        if (t1.has_shield)
            show_shield_1();
        if (t2.has_shield)
            show_shield_2();

        show_life_1();
        show_life_2();

        show_bullet_1();
        show_bullet_2();

        Toolkit.getDefaultToolkit().sync();
    }

    private void show_life_1() {
        String p1_life_s = Integer.toString(Data.getInstance().get_life_1());
        p1_life.setText(p1_life_s);
        p1_life.setBounds(32, 0, 100, 50);
        p1_life.setForeground(t1.c);
        p1_life.setFont(new Font(p1_life.getFont().getName(), p1_life.getFont().getStyle(), 40));
        this.add(p1_life);

        heart1.setText("♥");
        heart1.setBounds(0, 1, 50, 50);
        heart1.setForeground(t1.c);
        heart1.setFont(new Font(heart1.getFont().getName(), heart1.getFont().getStyle(), 45));
        this.add(heart1);
    }
    private void show_life_2() {
        String p2_life_s = Integer.toString(Data.getInstance().get_life_2());
        p2_life.setText(p2_life_s);
        p2_life.setBounds(915, 0, 100, 50);
        p2_life.setForeground(t2.c);
        p2_life.setFont(new Font(p2_life.getFont().getName(), p2_life.getFont().getStyle(), 40));
        this.add(p2_life);

        heart2.setText("♥");
        heart2.setBounds(882, 1, 50, 50);
        heart2.setForeground(t2.c);
        heart2.setFont(new Font(heart2.getFont().getName(), heart2.getFont().getStyle(), 45));
        this.add(heart2);
    }

    private void show_bullet_1() {
        String p1_bullet_s = Integer.toString(Data.getInstance().get_p1_bullet_n());
        p1_bullet.setText(p1_bullet_s);
        p1_bullet.setBounds(160, 0, 100, 50);
        p1_bullet.setForeground(t1.c);
        p1_bullet.setFont(new Font(p1_bullet.getFont().getName(), p1_bullet.getFont().getStyle(), 40));
        this.add(p1_bullet);

        bullet1.setText("◙");
        bullet1.setBounds(125, 1, 50, 50);
        bullet1.setForeground(t1.c);
        bullet1.setFont(new Font(bullet1.getFont().getName(), bullet1.getFont().getStyle(), 40));
        this.add(bullet1);
    }
    private void show_bullet_2() {
        String p2_bullet_s = Integer.toString(Data.getInstance().get_p2_bullet_n());
        p2_bullet.setText(p2_bullet_s);
        p2_bullet.setBounds(805, 0, 100, 50);
        p2_bullet.setForeground(t2.c);
        p2_bullet.setFont(new Font(p2_bullet.getFont().getName(), p2_bullet.getFont().getStyle(), 40));
        this.add(p2_bullet);

        bullet2.setText("◙");
        bullet2.setBounds(767, 1, 50, 50);
        bullet2.setForeground(t2.c);
        bullet2.setFont(new Font(bullet2.getFont().getName(), bullet2.getFont().getStyle(), 40));
        this.add(bullet2);
    }

    private void show_shield_1() {
        shield1.setText("◆");
        shield1.setBounds(240, 1, 50, 50);
        shield1.setForeground(t1.c);
        shield1.setFont(new Font(shield1.getFont().getName(), shield1.getFont().getStyle(), 45));
        this.add(shield1);
    }
    private void show_shield_2() {
        shield2.setText("◆");
        shield2.setBounds(702, 1, 50, 50);
        shield2.setForeground(t2.c);
        shield2.setFont(new Font(shield2.getFont().getName(), shield2.getFont().getStyle(), 45));
        this.add(shield2);
    }

    private void make_bp() {
        int bullet1_x_rand = rand.nextInt(950) + 10, bullet1_y_rand = rand.nextInt(800) + 150;
        int bullet2_x_rand = rand.nextInt(950) + 10, bullet2_y_rand = rand.nextInt(800) + 150;

        int bullet_x_1 = 50, bullet_y_1 = 150;
        int bullet_x_2 = 500, bullet_y_2 = 900;
        int bullet_x_3 = 900, bullet_y_3 = 650;
        int bullet_x_4 = 950, bullet_y_4 = 950;
        int bullet_x_5 = 500, bullet_y_5 = 650;
        boolean made_random_1 = true, made_random_2 = true;

        for (Wall w : Data.getInstance().walls) {
            if ((bullet1_x_rand >= w.x - 30 && bullet1_x_rand <= w.x + w.width + 30) && (bullet1_y_rand >= w.y - 30 && bullet2_y_rand <= w.y + w.height + 30))
                made_random_1 = false;
            if ((bullet2_x_rand >= w.x - 30 && bullet2_x_rand <= w.x + w.width + 30) && (bullet2_y_rand >= w.y - 30 && bullet2_y_rand <= w.y + w.height + 30) || (bullet1_x_rand != bullet2_x_rand && bullet1_y_rand != bullet2_y_rand))
                made_random_2 = false;
            if(!(made_random_1) && !(made_random_2)) {
                break;
            }
        }

        if (made_random_1) {
            bp1 = new Bullet_p(bullet1_x_rand, bullet1_y_rand);
        } else {
            int random = rand.nextInt(2)+1;
            switch (random) {
                case 1:
                    bp1 = new Bullet_p(bullet_x_1, bullet_y_1);
                    break;
                case 2:
                    bp1 = new Bullet_p(bullet_x_4, bullet_y_4);
                    break;
                case 3:
                    bp1 = new Bullet_p(bullet_x_5, bullet_y_5);
                    break;
            }
        }

        if (made_random_2) {
            bp2 = new Bullet_p(bullet2_x_rand, bullet2_y_rand);
        } else {
            int random = rand.nextInt(2)+1;
            switch (random) {
                case 1:
                    bp2 = new Bullet_p(bullet_x_2, bullet_y_2);
                    break;
                case 2:
                    bp2 = new Bullet_p(bullet_x_3, bullet_y_3);
                    break;
                case 3:
                    bp2 = new Bullet_p(bullet_x_5, bullet_y_5);
                    break;
            }
        }


    }
    private void make_life() {
        int life_x_rand = rand.nextInt(950) + 10, life_y_rand = rand.nextInt(800) + 150;
        int life_x_1 = 50, life_y_1 = 150;
        int life_x_2 = 500, life_y_2 = 900;
        int life_x_3 = 900, life_y_3 = 650;
        int life_x_4 = 950, life_y_4 = 950;
        int life_x_5 = 500, life_y_5 = 650;
        boolean made_random = true;

        for (Wall w : Data.getInstance().walls)
            if ((life_x_rand >= w.x - 30 && life_x_rand <= w.x + w.width + 30) && (life_y_rand >= w.y - 30 && life_y_rand <= w.y + w.height + 30)) {
                made_random = false;
                break;
            }

        if (made_random)
            life = new Life(life_x_rand, life_y_rand);
        else {
            int random = rand.nextInt(5)+1;
            switch (random) {
                case 1:
                    life = new Life(life_x_1, life_y_1);
                    break;
                case 2:
                    life = new Life(life_x_2, life_y_2);
                    break;
                case 3:
                    life = new Life(life_x_3, life_y_3);
                    break;
                case 4:
                    life = new Life(life_x_4, life_y_4);
                    break;
                case 5:
                    life = new Life(life_x_5, life_y_5);
                    break;
            }
        }
    }
    private void make_shield() {
        int shield_x_rand = rand.nextInt(950) + 10, shield_y_rand = rand.nextInt(800) + 150;
        int shield_x_1 = 50, shield_y_1 = 150;
        int shield_x_2 = 500, shield_y_2 = 900;
        int shield_x_3 = 900, shield_y_3 = 650;
        int shield_x_4 = 950, shield_y_4 = 950;
        int shield_x_5 = 500, shield_y_5 = 650;
        boolean made_random = true;

        for (Wall w : Data.getInstance().walls)
            if ((shield_x_rand >= w.x - 30 && shield_x_rand <= w.x + w.width + 30) && (shield_y_rand >= w.y - 30 && shield_y_rand <= w.y + w.height + 30)) {
                made_random = false;
                break;
            }

        if (made_random)
            shield = new Shield(shield_x_rand, shield_y_rand);
        else {
            int random = rand.nextInt(5)+1;
            switch (random) {
                case 1:
                    shield = new Shield(shield_x_1, shield_y_1);
                    break;
                case 2:
                    shield = new Shield(shield_x_2, shield_y_2);
                    break;
                case 3:
                    shield = new Shield(shield_x_3, shield_y_3);
                    break;
                case 4:
                    shield = new Shield(shield_x_4, shield_y_4);
                    break;
                case 5:
                    shield = new Shield(shield_x_5, shield_y_5);
                    break;
            }
        }

    }

    private void hitTankSound() {
        try {
            Sound.filePath = "./resource/Sounds/hitTankSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();
        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void hitWallSound() {
        try {
            Sound.filePath = "./resource/Sounds/hitWallSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();
        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void ShotSound() {
        try {
            Sound.filePath = "./resource/Sounds/ShotSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();

        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void ReloadSound() {
        try {
            Sound.filePath = "./resource/Sounds/ReloadSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();
        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void ShieldSound() {
        try {
            Sound.filePath = "./resource/Sounds/ShieldSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();
        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void LifeSound() {
        try {
            Sound.filePath = "./resource/Sounds/LifeSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();

        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void WiningSound() {
        try {
            Sound.filePath = "./resource/Sounds/WiningSound.wav";
            Sound audioPlayer = new Sound();
            audioPlayer.play();
        }
        catch (Exception ex)  {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}