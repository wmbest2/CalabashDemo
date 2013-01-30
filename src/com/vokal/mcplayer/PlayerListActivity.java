package com.vokal.mcplayer;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.vokal.network.*;

import com.vokal.mcplayer.models.Player;

public class PlayerListActivity extends SherlockFragmentActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	
	NetworkClient.getInstance().setImpl(new HttpURLConnectionImpl(""));
	
	PlayerListFragment.setup(this, R.id.player_list);

	new Thread() {
	    public void run() {
		try {
		Player.get(getApplicationContext());
		} catch (Exception e) {}
	    }
	}.start();
    }
}
