package com.delta.suraj.psychlone.Connection;

public class ConnectionUtilities {
    private static String playerName;
    private static String playerType;

    public static String getPlayerName () { return playerName; }

    public static void setPlayerName (String playerName) {
        ConnectionUtilities.playerName = playerName;
    }

    public static String getPlayerType () { return playerType; }

    public static void setPlayerType (String type) {
        ConnectionUtilities.playerType = type;
    }
}
