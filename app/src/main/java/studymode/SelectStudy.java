package studymode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import min3d.sampleProject1.R;

/**
 * Created by LeeDoBin on 2018-02-15.
 */

public class SelectStudy extends Activity{

    ImageButton studyPiano, studyTheory, exit;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_layout);
        studyPiano = (ImageButton)findViewById(R.id.studyTheory);
        studyTheory = (ImageButton)findViewById(R.id.studyPiano);
        exit = (ImageButton)findViewById(R.id.exit);

        studyTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudyTheory.class);
                startActivity(intent);

            }
        });

        studyPiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudyPiano.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
