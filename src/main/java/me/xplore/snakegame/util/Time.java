package me.xplore.snakegame.util;

public class Time {
    public static float TimeStarted = System.nanoTime();

    public static float getTime() {
        return (float)((System.nanoTime() - TimeStarted) * 1E-9);
    }
}
