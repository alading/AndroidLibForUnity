package com.tcl.frun.unityplugin;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.tcl.frun.api.IFrunAidlInterface;
import com.tcl.frun.api.IGameAidlInterface;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;


public class MainActivity extends UnityPlayerActivity {

    public static final String TAG = "Unity";

    boolean isBound = false;
    IGameAidlInterface GameClient;
    private String gameID = "1234567890";

    IFrunAidlInterface.Stub callback = new IFrunAidlInterface.Stub() {


        @Override
        public void onInit() throws RemoteException {
            Log.e(TAG, "-->on Init : "+gameID);
            UnityPlayer.UnitySendMessage("Main Camera", "onInit", "initialization");

        }

        @Override
        public void onRun() throws RemoteException {
            Log.e(TAG, "-->on run");
            UnityPlayer.UnitySendMessage("Main Camera", "onRun", "from java");
        }

        @Override
        public void onStand() throws RemoteException {
            Log.e(TAG, "-->on stop ");
            UnityPlayer.UnitySendMessage("Main Camera", "onStand", "from java");
        }

        @Override
        public void onJump() throws RemoteException {
            Log.e(TAG, "-->on jump");
            UnityPlayer.UnitySendMessage("Main Camera", "onJump", "from java");

        }

    };

    private ServiceConnection mUserMotionDetectionConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            GameClient = IGameAidlInterface.Stub.asInterface(service);
            isBound = true;
            Log.e(TAG, "-->Bounding service connected");
            try {
                GameClient.start(gameID, callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            GameClient = null;
            isBound = false;
            Log.e(TAG, "-->Bounding service disconnected");
        }
    };

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    public void shareText(String subject, String body) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    public void bindService() {
        Intent it = new Intent();
        it.setClassName("com.tcl.frun", "com.tcl.frun.api.ServiceAIDLUserMotion");
        it.setAction("com.tcl.frun.api.ServiceAIDLUserMotion.MotionBinder");
        bindService(it, mUserMotionDetectionConnection, Service.BIND_AUTO_CREATE);
    }
}
