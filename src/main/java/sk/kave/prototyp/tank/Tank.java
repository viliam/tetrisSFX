/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.kave.prototyp.tank;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Igo
 */
public class Tank {

    private int vpred = 0; //1 = dopredu; -1 = dozadu; 0 = nic .... asi by bol lepsi enum
    private int doprava = 0; //1 = doprava; -1 = dolava; 0 = nic
    private ImageView imgViewTank; //obrazok tanku

    public Tank() {
        Image imgTank = new Image(Tank.class.getResourceAsStream("/sk/kave/prototyp/tank/tank.png"));
        imgViewTank = new ImageView(imgTank);
    }

    public Tank(double natocenie) {
        this();
        imgViewTank.setRotate(natocenie);
    }

    public ImageView getImgViewTank() {
        return imgViewTank;
    }

    public double getUhol() {
        return imgViewTank.getRotate();
    }

    public void rotujTank(int uhol) {
        imgViewTank.setRotate(imgViewTank.getRotate() + uhol);
    }

    public int getVpred() {
        return vpred;
    }

    public void setVpred(int vpred) {
        this.vpred = vpred;
    }

    public int getDoprava() {
        return doprava;
    }

    public void setDoprava(int doprava) {
        this.doprava = doprava;
    }
}
