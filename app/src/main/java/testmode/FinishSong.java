package testmode;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import min3d.sampleProject1.R;

/**
 * Created by LeeDoBin on 2018-06-22.
 */

public class FinishSong extends Activity{
    ImageButton finishBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        finishBtn = (ImageButton) findViewById(R.id.finishImgBtn);
        ImageView iv = (ImageView) findViewById(R.id.finImg);

        Glide.with(this).load(R.drawable.finmove).into(iv);


        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
