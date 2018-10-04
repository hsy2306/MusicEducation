package studymode;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.sampleProject1.R;
import min3d.sampleProject1.soundManager;
import min3d.vos.Color4;
import min3d.vos.Light;

/**
 * Created by LeeDoBin on 2018-02-15.
 */

public class StudyPiano extends RendererActivity {
    ImageButton button[], exit;
    Color4[] blackColor;
    Color4[] whiteColor;
    Color4[] test;
    Object3dContainer white[], black[];

    String string[] = {"도", "레", "미", "파", "솔", "라", "시", "도"};

    @Override
    protected void onCreateSetContentView() {
        setContentView(R.layout.custom_layout_example);
        soundManager.getInstance().init(this);
        FrameLayout fl = (FrameLayout) this.findViewById(R.id.listActivity);
        exit = (ImageButton)findViewById(R.id.exit);
        View view = new View(this);
        //view.setBackgroundResource(R.drawable.learnnote);
        addContentView(view, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        fl.addView(_glSurfaceView);
        int btnid = R.id.btnnote01;
        button = new ImageButton[8];
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        for (int i = 0; i < button.length; i++) {
            button[i] = (ImageButton) findViewById(btnid++);
            button[i].setTag(i);
            Log.i("tagtt", String.valueOf(i));
        }
        for(int i =0; i< button.length; i++){
            button[i].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    for (int i = 0; i < button.length; i++) {
                        button[i] = (ImageButton) view;
                    }
                    int action = motionEvent.getAction();

                    if (action == MotionEvent.ACTION_DOWN) {
                        for (int i = 0; i < button.length; i++) {
                            if (i == (int) button[i].getTag()) {
                                Log.i("tagtt", String.valueOf(i));
                                scene.removeChild(white[i]);
                                white[i] = new Box(0.2f, 1f, 0.2f, test, false, false, true);
                                white[i].position().x =0.2f -1f + 0.22f * i;
                                white[i].position().y = 0.1f;
                                white[i].rotation().y = -90;
                                white[i].rotation().z = 30;
                                soundManager.getInstance().play(i);
                                scene.addChild(white[i]);
                                white[i].scale().x -= 0.5f;
                                return true;
                            }
                        }
                    } else if (action == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < button.length; i++) {
                            if (i == (int) button[i].getTag()) {
                                scene.removeChild(white[i]);
                                white[i] = new Box(0.2f, 1f, 0.2f, whiteColor, false, false, true);
                                white[i].position().x =0.2f -1f + 0.22f * i;
                                white[i].position().y = 0.1f;
                                white[i].rotation().y = -90;
                                white[i].rotation().z = 30;
                                scene.addChild(white[i]);
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }


    public void initScene() {
        scene.lights().add(new Light());

        blackColor = new Color4[6];
        blackColor[0] = new Color4(76, 76, 76, 255);   //오른쪽
        blackColor[1] = new Color4(0, 0, 0, 255);      //정면
        blackColor[2] = new Color4(76, 76, 76, 255);
        blackColor[3] = new Color4(76, 76, 76, 255);
        blackColor[4] = new Color4(76, 76, 76, 255);      //상단
        blackColor[5] = new Color4(76, 76, 76, 255);      //하단

        whiteColor = new Color4[6];
        whiteColor[0] = new Color4(193, 193, 193, 255);   //왼쪽
        whiteColor[1] = new Color4(255, 255, 255, 255);      //정면
        whiteColor[2] = new Color4(193, 193, 193, 255);    //오른쪽
        whiteColor[3] = new Color4(255, 255, 255, 255);
        whiteColor[4] = new Color4(193, 193, 193, 193);      //상단
        whiteColor[5] = new Color4(193, 193, 193, 255);      //하단

        test = new Color4[6];
        test[0] = new Color4(193, 193, 193, 255);    //왼쪽
        test[1] = new Color4(150, 150, 150, 255);    //정면
        test[2] = new Color4(180, 180, 180, 255);    //오른쪽
        test[3] = new Color4(255, 255, 255, 255);
        test[4] = new Color4(193, 193, 193, 193);    //상단
        test[5] = new Color4(193, 193, 193, 255);    //하단

        scene.backgroundColor().setAll(0x00000000);

        white = new Object3dContainer[8];

        for (int i = 0; i < white.length; i++) {
            white[i] = new Box(0.2f, 1f, 0.2f, whiteColor, false, false, true);
        }

        black = new Object3dContainer[5];

        for (int i = 0; i < black.length; i++) {
            black[i] = new Box(0.2f, 0.5f, 0.2f, blackColor, false, false, true);
        }

        int soundId = R.raw.sound1;
        for (int i = 0; i < white.length; i++) {
            white[i].position().x = 0.2f + -1f + 0.22f * i;
            white[i].position().y = 0.1f;
            white[i].rotation().y = -90;
            white[i].rotation().z = 30;
            soundManager.getInstance().addSound(i, soundId++);
            scene.addChild(white[i]);
        }

        for (int i = 0; i < 2; i++) {
            black[i].position().x = 0.2f-0.89f - 0.22f * (i - 1);
            black[i].position().y = 0.35f;
            black[i].position().z = 0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 20;
            scene.addChild(black[i]);
        }

        for (int i = 2; i < 5; i++) {
            black[i].position().x = 0.2f-0.23f - 0.22f * (i - 4);
            black[i].position().y = 0.35f;
            black[i].position().z = 0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 20;
            scene.addChild(black[i]);
        }
    }

    @Override
    public void updateScene() {

    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            for (int i = 0; i < button.length; i++) {
                button[i] = (ImageButton) view;
            }
            int action = motionEvent.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                for (int i = 0; i < button.length; i++) {
                    if (i == (int) button[i].getTag()) {
                        scene.removeChild(white[i]);
                        white[i] = new Box(0.2f, 1f, 0.2f, test, false, false, true);
                        white[i].position().x = -1f + 0.22f * i;
                        white[i].position().y = 0.1f;
                        white[i].rotation().y = -90;
                        white[i].rotation().z = 30;
                        soundManager.getInstance().play(i);
                        scene.addChild(white[i]);
                        white[i].scale().x -= 0.5f;
                        return true;
                    }
                }
            } else if (action == MotionEvent.ACTION_UP) {
                for (int i = 0; i < button.length; i++) {
                    if (i == (int) button[i].getTag()) {
                        scene.removeChild(white[i]);
                        white[i] = new Box(0.2f, 1f, 0.2f, whiteColor, false, false, true);
                        white[i].position().x = -1f + 0.22f * i;
                        white[i].position().y = 0.1f;
                        white[i].rotation().y = -90;
                        white[i].rotation().z = 30;
                        scene.addChild(white[i]);
                        return true;
                    }
                }
            }
            return false;
        }
    };
}
