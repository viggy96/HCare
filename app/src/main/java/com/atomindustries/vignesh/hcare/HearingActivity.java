package com.atomindustries.vignesh.hcare;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class HearingActivity extends Activity {
    int toneNumber = 0;
    MediaPlayer player = new MediaPlayer();
    int[] tone = new int[] {R.raw.tone, R.raw.tone2, R.raw.tone3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hearing_layout);
        player = MediaPlayer.create(this, tone[toneNumber]);
        if (player != null) player.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hearing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_credits) {
            Intent intent = new Intent(this, CreditsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clicked(View view) {
        if (toneNumber < tone.length - 1) {
            toneNumber++;
            //player.release();
            player.reset();
            player = MediaPlayer.create(this, tone[toneNumber]);
            if (player != null) player.start();
        }
        else {
            player.stop();
            player.release();
            Intent intent = new Intent(this, HearingResultActivity.class);
            startActivity(intent);
        }
    }
}
