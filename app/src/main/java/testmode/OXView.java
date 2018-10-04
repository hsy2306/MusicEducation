package testmode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import min3d.sampleProject1.R;

/**
 * Created by LeeDoBin on 2018-06-22.
 */

public class OXView extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ox);
        //FullScreen(this);
        Intent intent = getIntent();
        String result = intent.getExtras().getString("result");

        ImageView iv = (ImageView) findViewById(R.id.oxImg);
        if(result.equals("o")){
            iv.setImageResource(R.drawable.o);
        }else if(result.equals("x")){
            iv.setImageResource(R.drawable.x);
        }


        //Glide.with(this).load(R.drawable.moveover).into(iv);  //움짤

        startLoading();
    }
    private void startLoading(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(getApplicationContext(), SelectMode.class);
                //startActivity(intent);
                finish();
            }
        }, 1500);
    }

    public void FullScreen(Context context){
        ((OXView) context).getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|

                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }
}
