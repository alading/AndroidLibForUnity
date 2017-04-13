// IUserMotionDetectorAidlInterface.aidl
package com.tcl.frun.api;

// Declare any non-default types here with import statements

interface IFrunAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onInit();
    void onRun();
    void onStand();
    void onJump();
}
