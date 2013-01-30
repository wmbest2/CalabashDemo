package com.vokal.mcplayer.db;

import android.content.*;
import android.net.Uri;

public class PlayerContentProvider extends SQLiteSimpleContentProvider {

    public static final String AUTHORITY = "com.vokal.mcplayer.db";

    private static final int PLAYERS = 0;

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());

        return super.onCreate();
    }

    protected String getTableFromUri(final Uri aUri) {
        final int match = URI_MATCHER.match(aUri);
        switch(match) {
            case PLAYERS:
                return DBHelper.TABLE_PLAYERS;
        }

        return null;
    }

    static {
        URI_MATCHER.addURI(AUTHORITY, DBHelper.TABLE_PLAYERS, PLAYERS);
    }
}
