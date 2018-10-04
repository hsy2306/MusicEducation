package studymode;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import min3d.sampleProject1.R;
import min3d.sampleProject1.Task;

/**
 * Created by LeeDoBin on 2018-02-27.
 */

public class StudyTheory extends Activity {
    TextView text;
    ImageView iv;
    ImageButton left, right, exit;
    String theory;
    String theoryText[] = new String[9];
    String imgText[] = new String[9];
    Resources res;
    int resID[] = new int[9];
    int page = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_theory);
        text = (TextView)findViewById(R.id.text);
        left = (ImageButton)findViewById(R.id.left);
        right = (ImageButton)findViewById(R.id.right);
        iv = (ImageView)findViewById(R.id.image);
        exit = (ImageButton)findViewById(R.id.exit);

        text.setTypeface(Typeface.createFromAsset(getAssets(), "RIX_STAR_N_ME.TTF"));
        try {
            theory = new Task().execute("stave").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("tagtt",theory);
        splitString(theory, this);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page == 9){
                    page = 0;
                }else{
                    page++;
                }
                pageText();
                Log.i("tagtt",String.valueOf(page));
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page == 0){
                    page = 9;
                }else{
                    page--;
                }
                pageText();
                Log.i("tagtt",String.valueOf(page));
            }
        });
        pageText();
    }

    public void splitString(String text, Context context){
        String splited[];
        int j = 0;
        int k = 1;
        splited = text.split("&");
        for(int i = 0; i< splited.length; i++){
            Log.i("tagtt",splited[i] + " ");
        }
        for(int i = 0; i < 9; i++){
            theoryText[i] = splited[j];
            j= j+2;
            imgText[i] = splited[k];
            resID[i] = getResources().getIdentifier(imgText[i] , "drawable", context.getPackageName());
            k = k+2;
        }
    }

    public void pageText(){
        switch (page){
            case 0 :
                text.setText(theoryText[0]);
                iv.setImageResource(resID[0]);
                break;
            case 1 :
                text.setText(theoryText[1]);
                iv.setImageResource(resID[1]);
                break;
            case 2 :
                text.setText(theoryText[2]);
                iv.setImageResource(resID[2]);
                break;
            case 3 :
                text.setText(theoryText[3]);
                iv.setImageResource(resID[3]);
                break;
            case 4 :
                text.setText(theoryText[4]);
                iv.setImageResource(resID[4]);
                break;
            case 5 :
                text.setText(theoryText[5]);
                iv.setImageResource(resID[5]);
                break;
            case 6 :
                text.setText(theoryText[6]);
                iv.setImageResource(resID[6]);
                break;
            case 7 :
                text.setText(theoryText[7]);
                iv.setImageResource(resID[7]);
                break;
            case 8 :
                text.setText(theoryText[8]);
                iv.setImageResource(resID[8]);
                break;
        }
    }
}
