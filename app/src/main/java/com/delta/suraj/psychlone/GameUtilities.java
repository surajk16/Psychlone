package com.delta.suraj.psychlone;

public class GameUtilities {
    private static String playerName;
    enum Type {HOST, PLAYER};
    private static Type playerType;

    public static String getPlayerName () { return playerName; }

    public static void setPlayerName (String playerName) {
        GameUtilities.playerName = playerName;
    }

    public static Type getPlayerType () { return playerType; }

    public static void setPlayerType (String type) {
        if (type.equals("HOST"))
            GameUtilities.playerType = Type.HOST;
        else
            GameUtilities.playerType = Type.PLAYER;
    }
}
