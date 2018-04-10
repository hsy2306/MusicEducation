package min3d.sampleProject1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import studymode.SelectStudy;

/**
 * Created by LeeDoBin on 2018-02-12.
 */

public class SelectMode extends Activity{
    Button freemode, studymode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectmode);
        freemode = (Button)findViewById(R.id.freeMode);
        studymode = (Button)findViewById(R.id.studyMode);

        freemode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExampleInsideLayout.class);
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
    }
}
