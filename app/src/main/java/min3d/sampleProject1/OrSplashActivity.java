package min3d.sampleProject1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * Created by hsy on 2018-02-09.
 */

public class OrSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstantState)
    {
        super.onCreate(savedInstantState);
        new Setmode().FullScreen(this);
        SystemClock.sleep(3500);
        startActivity(new Intent(this,SelectMode.class));
        finish();


    }
}
