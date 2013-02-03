package com.vokal.mcplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vokal.mcplayer.models.Player;

public class DBHelper extends SQLiteOpenHelper {
    
    private static final String TAG = DBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "players.db";
    private static int DATABASE_VERSION       = 1;

    public static final String TABLE_PLAYERS = "players";

    DBHelper(Context aContext) {
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase aDb) {
        Log.w(TAG, "CREATING DATABASE");
        aDb.execSQL(
            "CREATE TABLE "  + TABLE_PLAYERS                         + " (" + 
            Player.ID        + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
            Player.PLAYER_ID + " VARCHAR UNIQUE,"                    + 
            Player.NAME      + " VARCHAR,"                           + 
            Player.X         + " INTEGER,"                           + 
            Player.Y         + " INTEGER,"                           + 
            Player.Z         + " INTEGER"                            + 
            ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase aDb, int aOldVersion, int aNewVersion) {
        Log.w(TAG, "Upgrading database from " + aOldVersion + " to " + aNewVersion);

        aDb.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);

        onCreate(aDb);
    }
}
