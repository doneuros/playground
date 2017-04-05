package main.writer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marc on 20.03.2017.
 */
public class Action {
    private int x;
    private int y;
    private Integer event;
    private long paus;
    public Action(int x, int y, int event, long paus) {
        this.x = x;
        this.y = y;
        this.event = event;
        this.paus = paus;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public long getPaus() {
        return paus;
    }

    public void setPaus(long paus) {
        this.paus = paus;
    }


    public static void PerformActions(ArrayList<Action> actions, Robot r) throws InterruptedException {
        for(Action action:actions){
            r.mouseMove(action.getX(),action.getY());
            Thread.sleep(action.getPaus());
            try {
                r.mousePress(action.getEvent());
                Thread.sleep(70);
                r.mouseRelease(action.getEvent());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
