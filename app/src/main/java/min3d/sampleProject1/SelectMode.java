package min3d.sampleProject1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import bluetooth.BluetoothService;
import studymode.SelectStudy;
import testmode.SelectSong;
import util.AppInfo;

/**
 * Created by LeeDoBin on 2018-02-12.
 */

public class SelectMode extends Activity {
    ImageButton freemode, studymode, testmode;
    public static ImageButton btBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectmode);

        freemode = (ImageButton) findViewById(R.id.freeMode);
        studymode = (ImageButton) findViewById(R.id.studyMode);
        testmode = (ImageButton) findViewById(R.id.testMode);

        btBtn = (ImageButton)findViewById(R.id.btBtn);

        btBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppInfo.bluetoothService = new BluetoothService(SelectMode.this);
                AppInfo.bluetoothService.checkBluetooth();
            }
        });
        freemode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Piano.class);
                startActivity(intent);
            }
        });

        studymode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectStudy.class);
                startActivity(intent);
            }
        });

        testmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectSong.class);
                startActivity(intent);
            }
        });
    }
}