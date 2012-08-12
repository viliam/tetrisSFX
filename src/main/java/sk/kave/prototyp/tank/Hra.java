/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.kave.prototyp.tank;

/**
 *
 * @author Igo
 */

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Hra {

    private static final int DOPREDU = 10;//o kolko sa posuniem dopredu, ked idem rovno (bez natocenia)
    private final int UHOL_OTOCENIA = 10; //v stupnoch

    private Point2D viewPort = new Point2D(0, 0);
    private final int VIEWPORT_VYSKA = 500;//aka velka cast pozadia ma byt vidiet
    private final int VIEWPORT_SIRKA = 500;


    private ImageView imgViewLand;//obrazok pozadia
    private Tank tank;
    private Timer playTimer;//toto je len zatial, kym sa nenaucim timeline
    private final int REFRESH_RATE = 100; //ako casto sa bude prekreslovat obrazovka [msec] - pouzite v timerovi

    public Hra() {

        tank = new Tank();

        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Platform.runLater(new Runnable() {

            public void run() {
                initFX(fxPanel);
            }
        });
        frame.setLocationRelativeTo(null);

        playTimer = new Timer(REFRESH_RATE, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateFx();
            }
        });
        playTimer.start();
    }

    /**
     * tato metoda updatne otocenie tanku a zobrazovane pozadie (pohyb tanku)
     */
    private void updateFx() {
        switch (tank.getVpred()) {
            case 1:
                posunViewPort(DOPREDU, tank.getUhol());
                break;
            case - 1:
                posunViewPort(- DOPREDU, tank.getUhol());
                break;
        }
        switch (tank.getDoprava()) {
            case 1:
                tank.rotujTank(UHOL_OTOCENIA);
                break;
            case - 1:
                tank.rotujTank(- UHOL_OTOCENIA);
                break;
        }
    }

    private void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
        initKeyListeners(fxPanel);
    }

    /**
     * vytvorim pozadie a nahodim do sceny vsetky (oba) obrazky - tank+pozadie
     *
     * @return
     */
    private Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        StackPane sp = new StackPane();

        Image imgLand = new Image(Hra.class.getResourceAsStream("/sk/kave/prototyp/tank/mapa.png"));
        imgViewLand = new ImageView(imgLand);
        sp.getChildren().add(imgViewLand);
        imgViewLand.setViewport(new Rectangle2D(viewPort.getX(), viewPort.getY(), VIEWPORT_SIRKA, VIEWPORT_VYSKA));

        sp.getChildren().add(tank.getImgViewTank());

        root.getChildren().add(sp);
        return scene;
    }

    /**
     * posunutie pozadia - iluzia pohybu ak je argument zaporny, idem dozadu
     *
     * @param dopredu     o kolko ma ist tank dopredu (v PIXELOCH !!!!)
     * @param tankRotacia uhol natocenia
     */
    private void posunViewPort(int dopredu, double tankRotacia) {
        double posunX = dopredu * Math.sin(Math.toRadians(90 - tankRotacia)); //tu moze byt nejaky problem so zapornymi uhlami... ale kto vie...sinus je parny, tak sa to asi samo opravi
        double posunY = dopredu * Math.cos(Math.toRadians(90 - tankRotacia));//to iste, co pri sinuse hore, ale tu sa to mozno samo neopravi :)

        viewPort = new Point2D(viewPort.getX() + posunX, viewPort.getY() + posunY);
        imgViewLand.setViewport(new Rectangle2D(viewPort.getX(), viewPort.getY(), VIEWPORT_SIRKA, VIEWPORT_VYSKA));
    }

    /**
     * odchytavanie stlacenia/pustenia klevesov
     *
     * @param component
     */
    private void initKeyListeners(JComponent component) {
        component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w'://hore
                        tank.setVpred(1);
                        break;

                    case 's': //dole
                        tank.setVpred(- 1);
                        break;

                    case 'd': //doprava
                        tank.setDoprava(1);
                        break;

                    case 'a': //dolava
                        tank.setDoprava(- 1);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w'://hore alebo dole
                    case 's':
                        tank.setVpred(0);
                        break;
                    case 'd'://doprava alebo dolava
                    case 'a':
                        tank.setDoprava(0);
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Hra();
            }
        });
    }
}