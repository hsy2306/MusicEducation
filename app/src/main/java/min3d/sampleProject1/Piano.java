package min3d.sampleProject1;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

import camera.CameraPreview;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.vos.Color4;
import min3d.vos.Light;
import util.AppInfo;

/**
 * Example of adding an OpenGL scene within a conventional Android application layout.
 * Entails overriding RenderActivity'SelectMode onCreateSetContentView() function, and
 * adding _glSurfaceView to the appropriate View...
 *
 * @author Lee
 */

public class Piano extends RendererActivity {
    Color4[] blackColor;
    Color4[] whiteColor;
    Color4[] test;
    Object3dContainer white[], black[];
    String data = "";
    String splitedData[];
    FrameLayout ll;
    Handler handler = null;
    CameraPreview cameraPreview;
    ImageView[] red;
    Random random;
    customThread thread;

    @Override
    protected void onCreateSetContentView() {
        setContentView(R.layout.custom_layout);
        soundManager.getInstance().init(this);

        ll = (FrameLayout) this.findViewById(R.id.listActivity);
      //  mSensorManager = (SensorManager)getSystemService(Application.SENSOR_SERVICE);
      //  mGgyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
       // mGyroLis = new GyroscopeListener();
        ll.addView(_glSurfaceView);
        AppInfo.str = "";
        thread = new customThread();
        thread.start();
        cameraPreview = new CameraPreview(this);
        ll.addView(cameraPreview);

        random = new Random();
        red = new ImageView[8];

        for (int i = 0; i < 8; i++) {
            red[i] = new ImageView(this);
            red[i].setLayoutParams(new FrameLayout.LayoutParams(200, 200));
            red[i].bringToFront();
            red[i].setX(100 + i * 200);
            red[i].setY(200);
            red[i].invalidate();
            ll.addView(red[i]);
        }
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("tagtt", "handler msg : " + msg.what);
                //red.invalidate();
                int noteId = R.drawable.arnote01;
                Animation animation = AnimationUtils.loadAnimation
                        (getApplicationContext(), R.anim.alpha_anim);
                red[msg.what].setBackgroundResource(noteId + msg.what);
                red[msg.what].setX(setX());

                red[msg.what].setY(setY());
                red[msg.what].bringToFront();
                red[msg.what].startAnimation(animation);
            }
        };
        splitedData = data.split("&");
        Log.i("tagtt", "Split Data : " + splitedData.toString());
     //   mSensorManager.registerListener(mGyroLis, mGgyroSensor, SensorManager.SENSOR_DELAY_UI);
    }

    class customThread extends Thread {
        int num;
        private boolean flag = true;
        @Override
        public void run() {
            while (flag) {
                if (!AppInfo.str.equals("")) {
                    Log.i("tagtt", "num string");
                    if (!AppInfo.str.equals(null)) {
                        Log.i("tagtt", "thread string : " + String.valueOf(AppInfo.num));
                        num = Integer.parseInt(AppInfo.str) - 97;
                        Log.i("tagtt", "thread num : " + String.valueOf(num));

                        if (num > -1 && num < 8) {

                            Log.i("tagtt", "pinao num : " + String.valueOf(num));
                            AppInfo.str = "";
                            soundManager.getInstance().play(num);
                            int button = num + 7;
                            scene.removeChild(white[button]);
                            white[button] = new Box(0.2f, 1f, 0.2f, test, false, false, true);
                            white[button].position().x = -0.56f + 0.22f * num;
                            white[button].position().y = -0.8f;
                            white[button].rotation().y = -90;
                            white[button].rotation().z = 30;
                            scene.addChild(white[button]);
                            white[button].scale().x -= 0.5f;
                            Message msg = handler.obtainMessage();
                            msg.what = num;
                            handler.sendMessage(msg);
                            SystemClock.sleep(500);

                            scene.removeChild(white[button]);
                            white[button] = new Box(0.2f, 1f, 0.2f, whiteColor, false, false, true);
                            white[button].position().x = -0.56f + 0.22f * num;
                            white[button].position().y = -0.8f;
                            white[button].rotation().y = -90;
                            white[button].rotation().z = 30;
                            scene.addChild(white[button]);
                        }
                    }
                }
            }
        }
        public void finishThread() {
            flag = false;
        }
    }

    @Override
    public void onBackPressed() {
        thread.finishThread();
       // mSensorManager.unregisterListener(mGyroLis);
        super.onBackPressed();
    }

    public void onPause(){
        super.onPause();
      //  mSensorManager.unregisterListener(mGyroLis);
        thread.finishThread();
    }

    public void onDestroy(){
        super.onDestroy();
      //  mSensorManager.unregisterListener(mGyroLis);
        thread.finishThread();
    }

    public int setX() {
        return random.nextInt(1500);
    }

    public int setY() {
        return random.nextInt(400);
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

        white = new Object3dContainer[20];

        for (int i = 0; i < white.length; i++) {
            white[i] = new Box(0.2f, 1f, 0.2f, whiteColor, false, false, true);
        }

        black = new Object3dContainer[14];

        for (int i = 0; i < black.length; i++) {
            black[i] = new Box(0.2f, 0.5f, 0.2f, blackColor, false, false, true);
        }
        int soundId = R.raw.sound1;
        for (int i = 0; i < 8; i++) {
            soundManager.getInstance().addSound(i, soundId++);
        }

        for (int i = 0; i < white.length; i++) {
            white[i].position().x = -2.1f + 0.22f * i;
            white[i].position().y = -0.8f;
            white[i].rotation().y = -90;
            white[i].rotation().z = 30;
            scene.addChild(white[i]);
        }

        for (int i = 0; i < 2; i++) {
            black[i].position().x = -1.99f - 0.22f * (i - 1);
            black[i].position().y = -0.5f;
            black[i].position().z = -0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 30;
            scene.addChild(black[i]);
        }

        for (int i = 2; i < 5; i++) {
            black[i].position().x = -1.33f - 0.22f * (i - 4);
            black[i].position().y = -0.5f;
            black[i].position().z = -0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 30;
            scene.addChild(black[i]);
        }

        for (int i = 5; i < 7; i++) {
            black[i].position().x = -0.45f - 0.22f * (i - 6);
            black[i].position().y = -0.5f;
            black[i].position().z = -0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 30;
            scene.addChild(black[i]);
        }

        for (int i = 7; i < 10; i++) {
            black[i].position().x = 0.21f - 0.22f * (i - 9);
            black[i].position().y = -0.5f;
            black[i].position().z = -0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 30;
            scene.addChild(black[i]);
        }

        for (int i = 10; i < 12; i++) {
            black[i].position().x = 1.09f - 0.22f * (i - 11);
            black[i].position().y = -0.5f;
            black[i].position().z = -0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 30;
            scene.addChild(black[i]);
        }

        for (int i = 12; i < 14; i++) {
            black[i].position().x = 1.75f - 0.22f * (i - 13);
            black[i].position().y = -0.5f;
            black[i].position().z = -0.1f;
            black[i].rotation().y = -90;
            black[i].rotation().z = 30;
            scene.addChild(black[i]);
        }
    }

    @Override
    public void updateScene() {

    }
    /*
    private class GyroscopeListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {


            double gyroX = event.values[0];
            double gyroY = event.values[1];
            double gyroZ = event.values[2];


            dt = (event.timestamp - timestamp) * NS2S;
            timestamp = event.timestamp;


            if (dt - timestamp*NS2S != 0) {


                pitch = pitch + gyroY*dt;
                roll = roll + gyroX*dt;
                yaw = yaw + gyroZ*dt;

                Log.e("tagttt", "GYROSCOPE           [X]:" + String.format("%.4f", event.values[0])
                        + "           [Y]:" + String.format("%.4f", event.values[1])
                        + "           [Z]:" + String.format("%.4f", event.values[2])
                        + "           [Pitch]: " + String.format("%.1f", pitch*RAD2DGR)
                        + "           [Roll]: " + String.format("%.1f", roll*RAD2DGR)
                        + "           [Yaw]: " + String.format("%.1f", yaw*RAD2DGR)
                        + "           [dt]: " + String.format("%.4f", dt));

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
*/
}

