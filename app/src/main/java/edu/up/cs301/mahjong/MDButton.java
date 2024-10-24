package edu.up.cs301.mahjong;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;

/**
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
    private MDButton[][] buttonArray = new MDButton[2][7];

    public MDButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        buttonID = tempButtonID;

        populateArray();

        this.tempButtonID++;


    }

    private void populateArray(){
        if (buttonID < 7) {
            buttonArray[0][buttonID] = this;
        }
        else {
            buttonArray[1][buttonID-7] = this;
        }
    }

    public int getID(){
        return this.buttonID;
    }

}
