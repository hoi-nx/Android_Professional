package com.heartofdead.base.animation;


import com.heartofdead.base.R;

/**
 * Created by nguyenxuanhoi2903 on 29/11/2017.
 */

public enum ScreenAnimation {

    OPEN_FULL(R.anim.enter_to_right, R.anim.exit_to_right,
            R.anim.enter_to_left, R.anim.exit_to_left),
    OPEN_FULL_RIGT_TO_LEFT(R.anim.enter_to_left, R.anim.exit_to_left,
            R.anim.enter_to_right, R.anim.exit_to_right),
    OPEN_FULL_LEFT_TO_RIGHT( R.anim.enter_to_right, R.anim.exit_to_right,R.anim.enter_to_left, R.anim.exit_to_left),
    OPEN_FULL_BOTTOM(R.anim.enter_to_bottom, R.anim.exit_to_bottom,
            R.anim.pop_enter_bottom, R.anim.pop_exit_bottom),
    NONE(0, 0, 0, 0);



    private final int enterToRight;
    private final int exitToRight;
    private final int enterToLeft;
    private final int exitToLeft;

    ScreenAnimation(int openToRight, int exitToRight, int openToLeft, int exitToLeft) {
        this.enterToRight = openToRight;
        this.exitToRight = exitToRight;
        this.enterToLeft = openToLeft;
        this.exitToLeft = exitToLeft;
    }

    public int getEnterToRight() {
        return enterToRight;
    }

    public int getExitToRight() {
        return exitToRight;
    }

    public int getEnterToLeft() {
        return enterToLeft;
    }

    public int getExitToLeft() {
        return exitToLeft;
    }

}
