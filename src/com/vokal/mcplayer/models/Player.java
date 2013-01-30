package com.vokal.mcplayer.models;

import android.content.*;
import android.database.*;
import android.net.Uri;

import org.json.*;

import com.vokal.network.NetworkClient;
import com.vokal.network.NetworkResponse;

import com.vokal.mcplayer.db.*;

public class Player {
    
    public static final String ID        = "_id";
    public static final String PLAYER_ID = "player_id";
    public static final String NAME      = "player";
    public static final String X         = "x";
    public static final String Y         = "y";
    public static final String Z         = "z";

    public static final String[] ALL = new String[] {
        ID, PLAYER_ID, NAME, X, Y, Z
    };

    public static final Uri CONTENT_URI = Uri.parse("content://" +
        PlayerContentProvider.AUTHORITY + "/" + DBHelper.TABLE_PLAYERS);

    public int mId = -1;
    public String mPlayerId;
    public String mName;
    public int mX;
    public int mY;
    public int mZ;

    private Player() {

    }

    public Player(final JSONObject aData) throws JSONException {
        android.util.Log.d("SHFHDSFKHSDLKFJSLKDJFLKSDJFLKSJDLFHKSL", aData.toString());
        mPlayerId = aData.getString(ID);
        mName     = aData.getString(NAME);
        mX        = aData.optInt(X);
        mY        = aData.optInt(Y);
        mZ        = aData.optInt(Z);
    }

    public static void get(final Context aContext) throws Exception {
        NetworkClient client = NetworkClient.getInstance(); 
        NetworkResponse resp = client.get("http://vokal-mapserve.jit.su/players");

        if (resp.getCode() / 100 == 2) {
            String data = resp.getResponse();
            JSONArray players = new JSONArray(data);

            for (int i = 0; i < players.length(); ++i) {
                new Player(players.getJSONObject(i)).save(aContext);
            }
        }
    }

    public void save(Context aContext) {
        ContentValues values = getContentValues();

        Uri inserted = aContext.getContentResolver().insert(CONTENT_URI, values);
        try {
            mId = (int) ContentUris.parseId(inserted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    protected ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        if (mId != -1) {
            values.put(ID, mId);
        }

        values.put(PLAYER_ID, mId);
        values.put(NAME, mName);
        values.put(X, mX);
        values.put(Y, mY);
        values.put(Z, mZ);

        return values;
    }

    public static Player fromCursor(Cursor aCursor) {
        Player result = null;

        if (PlayerContentProvider.isValidCursor(aCursor)) {
            result = new Player();

            int index = 0;
            for(String name : aCursor.getColumnNames()) {
                result.setByCursorColumn(aCursor, name, index);
                ++index;
            }
        }

        return result;
    }

    private void setByCursorColumn(final Cursor aCursor, final String aName, final int index) {
        if (aName.equals(ID)) {
            mId = aCursor.getInt(index);
        } else if (aName.equals(PLAYER_ID)) {
            mPlayerId = aCursor.getString(index);
        } else if (aName.equals(NAME)) {
            mName = aCursor.getString(index);
        } else if (aName.equals(X)) {
            mX = aCursor.getInt(index);
        } else if (aName.equals(Y)) {
            mY = aCursor.getInt(index);
        } else if (aName.equals(Z)) {
            mZ = aCursor.getInt(index);
        }
    }


}
