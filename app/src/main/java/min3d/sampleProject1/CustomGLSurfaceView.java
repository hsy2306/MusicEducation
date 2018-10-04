package min3d.sampleProject1;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.SurfaceHolder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CustomGLSurfaceView extends GLSurfaceView implements Renderer {

	public CustomGLSurfaceView(Context context) {

		super(context);

		setEGLConfigChooser(8, 8, 8, 8, 16, 8);
		getHolder().setFormat(PixelFormat.RGBA_8888);
		//getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		setRenderer(this);
		setZOrderOnTop(true);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	// Clear screen with clear color
		gl.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);   // Set drawing color as black
		
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -6.0f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  // Black as background color
		gl.glClearDepthf(1.0f); 							//Depth Buffer Setup
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		super.surfaceDestroyed(holder);
	}
}
