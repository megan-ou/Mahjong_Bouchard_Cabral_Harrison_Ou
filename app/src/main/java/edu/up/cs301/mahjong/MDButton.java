package edu.up.cs301.mahjong;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;

/**
 * Written for Lab 6!
 *
 * A class to give all discard buttons a specific ID. This will help us identify tiles during touch
 * events.
 *
 * Note: Array might be deleted because it might not be needed.
 *
 * @author Landon Harrison
 * @author Jacqui Bouchard
 * @author Megan Ou
 * @version Fall 2024
 */


public class MDButton extends androidx.appcompat.widget.AppCompatButton {
    private static int tempButtonID = 0;
    private int buttonID;

    public MDButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //reset for when the static variable gets a little too silly
        if (tempButtonID > 14) {
            tempButtonID = 0;
        }

        buttonID = tempButtonID;

        this.tempButtonID++;
    }

    public int getID(){
        return this.buttonID;
    }

}
