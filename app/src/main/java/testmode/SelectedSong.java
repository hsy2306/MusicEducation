package testmode;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import bluetooth.BluetoothService;
import min3d.sampleProject1.R;
import min3d.sampleProject1.TaskTest;
import min3d.sampleProject1.soundManager;
import util.AppInfo;

/**
 * Created by LeeDoBin on 2018-05-31.
 */

public class SelectedSong extends Activity {
    //static final int REQUEST_ENABLE_BT = 10;
    ImageButton nextBtn, compareBtn, exit;
    ImageView measureImg, oxImg;
    TextView textView;
    BluetoothService bluetoothService;
    String fullMeasure;
    String measure[];
    int correctCount = 0;
    int soundId;
    int measureId;
    FrameLayout fl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedsong);
        textView = (TextView) findViewById(R.id.datatv);
        compareBtn = (ImageButton) findViewById(R.id.compareImgBtn);
        measureImg = (ImageView) findViewById(R.id.measureImg);
        nextBtn = (ImageButton) findViewById(R.id.nextImgBtn);
        exit = (ImageButton) findViewById(R.id.exit);

        fl = (FrameLayout)findViewById(R.id.fl);
        oxImg = new ImageView(this);
        oxImg.setLayoutParams(new FrameLayout.LayoutParams(300,300));

        soundManager.getInstance().init(this);
        measureId = R.drawable.air_measure01;
        soundId = R.raw.air_block01;

        for (int i = 0; i < 4; i++) {
            soundManager.getInstance().addSound(i, soundId++);
        }
        soundManager.getInstance().addSound(4, R.raw.wrong);
        soundManager.getInstance().addSound(5, R.raw.correct);
        soundManager.getInstance().addSound(6, R.raw.crap);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.air_block01);
        mediaPlayer.start();

        try {
            fullMeasure = new TaskTest().execute("AirPlaneSong").get(); //동요추가시 변경
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("tagtt", "data : " + fullMeasure);
        measure = fullMeasure.split("&");

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppInfo.num = "";
                if(AppInfo.bluetoothService != null) {
                    AppInfo.bluetoothService.sendData();
                }
                finish();
            }
        });

        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String fullData = textView.getText().toString();
                String fullData = String.valueOf(AppInfo.num);

                //String fullData = selectMode.getData();
                Log.i("tagtt", "data : " + fullData);
                Intent intent = new Intent(SelectedSong.this, OXView.class);
                if (fullData.equals(measure[correctCount])) {

                    soundManager.getInstance().play(5);
                    nextBtn.setVisibility(View.VISIBLE);
                    //oxImg.setBackgroundResource(R.drawable.o);
                    //oxImg.bringToFront();
                    //fl.addView(oxImg);
                    //SystemClock.sleep(300);
                    //fl.removeView(oxImg);
                    intent.putExtra("result", "o");
                } else {
                    soundManager.getInstance().play(4);
                    intent.putExtra("result", "x");
                }
                if(AppInfo.bluetoothService != null) {
                    AppInfo.bluetoothService.sendData();
                }
                    AppInfo.num = "";
                    startActivity(intent);

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                if (correctCount < 4) {
                    soundManager.getInstance().play(correctCount);
                    measureId++;
                    measureImg.setImageResource(measureId);
                }
                Log.i("tagtt", measure[correctCount]);

                nextBtn.setVisibility(View.INVISIBLE);
                measure[correctCount].trim();
                if (measure[correctCount].equals("End")) {
                    //soundManager.getInstance().play(5);
                    Intent intent = new Intent(SelectedSong.this, FinishSong.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        /*bluetoothService = new BluetoothService(SelectedSong.this, handler);
        bluetoothService.checkBluetooth();*/
    }

    @Override
    protected void onDestroy() {
        AppInfo.num = "";
        if(AppInfo.bluetoothService != null) {
            AppInfo.bluetoothService.sendData();
        }
        super.onDestroy();
    }
}
