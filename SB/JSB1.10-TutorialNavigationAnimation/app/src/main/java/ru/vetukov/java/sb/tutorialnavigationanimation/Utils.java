package ru.vetukov.java.sb.tutorialnavigationanimation;

import android.graphics.Color;

import java.util.Random;

public class Utils {

    public static int generateRandomColor(final Random rnd) {
        return Color.argb(0xff,
                          (int) (rnd.nextDouble() * 255),
                          (int) (rnd.nextDouble() * 255),
                          (int) (rnd.nextDouble() * 255));
    }

}
