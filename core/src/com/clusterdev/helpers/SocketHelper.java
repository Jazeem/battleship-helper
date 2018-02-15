package com.clusterdev.helpers;

import com.clusterdev.Constants;
import com.clusterdev.gameworld.GameWorld;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by jazeem on 15/02/18.
 */

public class SocketHelper {

    private static SocketHelper mInstance;
    public Socket mSocket;

    public static SocketHelper getInstance(GameWorld world){
        if(mInstance == null)
            mInstance = new SocketHelper(world);
        return mInstance;
    }

    public SocketHelper(final GameWorld world){
        try {
            mSocket = IO.socket("https://polar-castle-22052.herokuapp.com/");
//            mSocket = IO.socket("http://localhost:3000/");
        } catch (URISyntaxException e) {}
        mSocket.connect();
        mSocket.on("gamestarted", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                world.setGameState(Constants.GAME_STATE.ARRANGE);
            }
        });
        mSocket.on("gameover", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if(((String)args[0]).equals("roomfull"))
                    world.setGameState(Constants.GAME_STATE.FULL);
            }
        });
        mSocket.on("arrange", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if(args[0].equals("waiting"))
                    world.setArrangeWaiting(true);
                else
                    world.setGameState(Constants.GAME_STATE.PLAYING);
            }
        });
    }
}
