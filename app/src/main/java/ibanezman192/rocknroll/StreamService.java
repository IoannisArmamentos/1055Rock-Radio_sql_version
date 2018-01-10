package ibanezman192.rocknroll;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class StreamService extends MainMenu {

    private static MediaPlayer player;
    public static int serviceAvailable;
    private static final int HELLO_ID = 1;



    public static void startStream() {
        // Koumpi Play
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    System.out.println("ONCLICK PLAYER.isPlaying nai");
                    try {
                        stopPlaying();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("ONCLICK PLAYER.startPlaying");
                    startPlaying();
                }
            }
        });

        initializeMediaPlayer();

        // Koumpi Stop
        ibStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stopPlaying();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void initializeMediaPlayer() {
        // Thetw neo Mediaplayer poy Streamarei
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            StringBuffer str = new StringBuffer();
            player.setDataSource("http://radio.onweb.gr:8078/"); //radio source url
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.i("Buffering", "" + percent);
            }
        });
    }

    private static void startPlaying() {
        ibStop.setEnabled(true);
        ibPlay.setEnabled(false);

        player.prepareAsync();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                serviceAvailable = 1;
                ibPlay.setVisibility(View.INVISIBLE);
                ibStop.setVisibility(View.VISIBLE);
                player.start();
            }
        });

    }


    public static void stopPlaying() throws IOException {
        if (player.isPlaying()) {
            serviceAvailable = 0;
            player.stop();
            player.release();
            player = null;
            ibPlay.setVisibility(View.VISIBLE);
            ibStop.setVisibility(View.INVISIBLE);
            //mNotificationManager.cancel(HELLO_ID);
            startStream();
        }

        ibStop.setEnabled(false);
        ibPlay.setEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isPlaying()) {
            player.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) player.release();
    }
}
