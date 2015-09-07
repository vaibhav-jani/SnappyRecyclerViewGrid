package com.websoft.trial.snappyrecyclerview;

/**
 * Created by vaibhav.jani on 4/13/2015.
 */
public interface ISnappyLayoutManager {

    /**
     * @param velocityX
     * @param velocityY
     * @return the resultant position from a fling of the given velocity.
     */
    int getPositionForVelocity(int velocityX, int velocityY);

    /**
     * @return the position this list must scroll to to fix a state where the
     * views are not snapped to grid.
     */
    int getFixScrollPos();
}
