package min3d.sampleProject1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by hsy on 2018-02-09.
 */

public class OrSplashActivity extends Activity {
    boolean flag =false;
    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.logo);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        Glide.with(this).load(R.drawable.moveover).into(iv);
        startLoading();
    }

    private void startLoading(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), SelectMode.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}