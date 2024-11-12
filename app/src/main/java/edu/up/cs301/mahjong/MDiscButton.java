package edu.up.cs301.mahjong;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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


public class MDiscButton extends androidx.appcompat.widget.AppCompatButton {
    private int buttonID;

    public MDiscButton(@NonNull Context context, @Nullable AttributeSet attrs, int id) {
        super(context, attrs);

        this.buttonID = id;
    }

    public int getID(){
        return this.buttonID;
    }

}
