package com.vokal.mcplayer;

import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.vokal.network.*;
import com.vokal.async_image_cache.ImageFetcher;

import com.vokal.mcplayer.models.Player;

public class PlayerDetailsActivity extends SherlockFragmentActivity {

    private ImageFetcher mFetcher;

    @Override
    public void onCreate(Bundle aSavedState) {
        super.onCreate(aSavedState);
        setContentView(R.layout.details);
	mFetcher = new ImageFetcher(this);
	mFetcher.addImageCache(this);

	Player p = Player.withName(this, getIntent().getStringExtra(Player.NAME));

	ImageView avatar = (ImageView) findViewById(R.id.avatar);
        android.util.Log.d("shflsjf", getString(R.string.avatar_url, p.mName));
	mFetcher.loadImage(getString(R.string.avatar_url, p.mName), avatar);
    }
}
