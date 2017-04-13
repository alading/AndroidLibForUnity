// IFrunAidlInterface.aidl
package com.tcl.frun.api;

// Declare any non-default types here with import statements
import com.tcl.frun.api.IFrunAidlInterface;
import com.tcl.frun.api.UserData;

interface IGameAidlInterface {
    /**
    * Register User Input
    */
    void start(String gameId, IFrunAidlInterface listener);
    void stop(String gameId);
    void report(in UserData userData);

}
