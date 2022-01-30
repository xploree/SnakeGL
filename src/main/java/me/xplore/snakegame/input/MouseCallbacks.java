package me.xplore.snakegame.input;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class MouseCallbacks {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[4];
    private boolean isDragging;

    private MouseCallbacks(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseCallbacks get(){
        if(MouseCallbacks.instance == null){
            instance = new MouseListener();
        }
    }


    // callback for using a button
    public static void mouseButtonCallback(long window, int button, int action, int mods){
        // checks if the mouse was pressed
        if(action == GLFW_PRESS){
            get().mouseButtonPressed[button] = true;
        }
    }
}
