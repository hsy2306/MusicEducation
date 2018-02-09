package min3d.sampleProject1;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.vos.Color4;
import min3d.vos.Light;

/**
 * Example of adding an OpenGL scene within a conventional Android application layout.
 * Entails overriding RenderActivity's onCreateSetContentView() function, and
 * adding _glSurfaceView to the appropriate View...
 *
 * @author Lee
 */
public class ExampleInsideLayout extends RendererActivity implements View.OnClickListener {
    Button up, down;
    Color4[] blackColor;
    Color4[] whiteColor;
    Object3dContainer white[], black[];

    @Override
    protected void onCreateSetContentView() {
        setContentView(R.layout.custom_layout_example);
        up = new Button(this);
        down = new Button(this);
        FrameLayout ll = (FrameLayout) this.findViewById(R.id.listActivity);
        ll.addView(_glSurfaceView);

        addContentView(new CameraPreview(this), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    public void onClick(View $v) {

    }

    public void initScene() {
        scene.lights().add(new Light());

        blackColor = new Color4[6];
        blackColor[0] = new Color4(76,76,76,255);   //오른쪽
        blackColor[1] = new Color4(0,0,0,255);      //정면
        blackColor[2] = new Color4(76,76,76,255);
        blackColor[3] = new Color4(76,76,76,255);
        blackColor[4] = new Color4(76,76,76,255);      //상단
        blackColor[5] = new Color4(76,76,76,255);      //하단

        whiteColor = new Color4[6];
        whiteColor[0] = new Color4(193,193,193,255);   //왼쪽
        whiteColor[1] = new Color4(255,255,255,255);      //정면
        whiteColor[2] = new Color4(193,193,193,255);    //오른쪽
        whiteColor[3] = new Color4(255,255,255,255);
        whiteColor[4] = new Color4(193,193,193,193);      //상단
        whiteColor[5] = new Color4(193,193,193,255);      //하단

        scene.backgroundColor().setAll(0x00000000);

        white = new Object3dContainer[8];

        for(int i = 0; i < white.length; i++){
            white[i] = new Box(0.2f, 1f, 0.2f, whiteColor, false,false,true);
        }
       // white[0] = new Box(0.2f, 1f, 0.2f, whiteColor, false,false,true);

        black = new Object3dContainer[5];

        for(int i = 0; i < black.length; i++){
            black[i] = new Box(0.2f, 0.5f, 0.2f, blackColor, false,false,true);
        }

/*
        IParser parser = Parser.createParser(Parser.Type.OBJ,
                getResources(), "min3d.sampleProject1:raw/camaro_obj", true);
        parser.parse();

        objModel = parser.getParsedObject();*/

        for(int i = 0; i < white.length; i++){
            white[i].position().x = -1f + 0.22f * i;
            white[i].position().y = 0.1f;
            white[i].rotation().y = -90;
            white[i].rotation().z = 30;
            scene.addChild(white[i]);
        }

        for(int i = 0; i < 2; i++){
            black[i].position().x = -0.89f - 0.22f * (i-1);
            black[i].position().y = 0.35f;
            black[i].position().z = 0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 20;
            scene.addChild(black[i]);
        }

        for(int i = 2; i < 5; i++){
            black[i].position().x = -0.23f - 0.22f * (i-4);
            black[i].position().y = 0.35f;
            black[i].position().z = 0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 20;
            scene.addChild(black[i]);
        }
    }

    @Override
    public void updateScene() {
        //cube.rotation().y++;
        //objModel.rotation().z++;
    }

    public boolean onTouchEvent(MotionEvent e) {
        super.onTouchEvent(e);
        if (e.getAction() == MotionEvent.ACTION_UP) {
            //cube.defaultColor().setAll(0xffffffff);
            white[5].scale().x += 0.5f;
            return true;
        } else if (e.getAction() == MotionEvent.ACTION_DOWN) {
            //cube.defaultColor().setAll(0xffd8d8d8);
            white[5].scale().x -= 0.5f;
            return true;
        }
        return false;
    }
}

