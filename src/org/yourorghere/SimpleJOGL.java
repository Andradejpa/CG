package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class SimpleJOGL implements GLEventListener {

    private float rotation = 0.0f;

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SimpleJOGL());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        //drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT
                | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        // Move triangle  

        gl.glTranslatef(
                -0.5f, 0.0f, -6.0f);
        gl.glRotatef(rotation, 1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_TRIANGLES);
        // Front  

        gl.glColor3f(
                1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(
                1.5f, 2f, 0.0f); // Top Of Triangle   

        gl.glColor3f(
                0.0f, 1.5f, 0.0f); // Green  
        gl.glVertex3f(
                -1.5f, -1.5f, 1.5f); // Left Of Triangle   

        gl.glColor3f(
                1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(
                1.5f, -1.5f, 1.5f); // Right Of Triangle   

        // Right  
        gl.glColor3f(
                1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(
                1.5f, 2.0f, 0.0f); // Top Of Triangle   

        gl.glColor3f(
                1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(
                1.5f, -1.5f, 1.5f); // Left Of Triangle   

        gl.glColor3f(
                0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(
                1.5f, -1.5f, -1.5f); // Right Of Triangle   

        // Back  
        gl.glColor3f(
                1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(
                1.5f, 2.0f, 0.0f); // Top Of Triangle   

        gl.glColor3f(
                0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(
                1.5f, -1.5f, -1.5f); // Left Of Triangle   

        gl.glColor3f(
                1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(
                -1.5f, -1.5f, -1.5f); // Right Of Triangle   

        //left  
        gl.glColor3f(
                1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(
                1.5f, 2.0f, 0.0f); // Top Of Triangle   

        gl.glColor3f(
                1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(
                -1.5f, -1.5f, -1.5f); // Left Of Triangle   

        gl.glColor3f(
                0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(
                -1.5f, -1.5f, 1.5f); // Right Of Triangle   

        gl.glEnd();

        gl.glFlush();
        rotation += 0.5f;
        gl.glTranslatef(
                -0.5f, 0.0f, -8.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0f, 0f, 1f); //Blue color  
        //Top Quadrilateral  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right  
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left  
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Bottom Left  
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Bottom Right  
        //Below Quadrilateral  
        gl.glColor3f(1f, 0f, 0f); //Red color  
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Upper Right   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right   
        //Front Quadrilateral  
        gl.glColor3f(0f, 1f, 0f); //Green color  
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Right   
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Right  
        //Back Quadrilateral  
        gl.glColor3f(1f, 1f, 0f); //Yellow  
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Right   
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Right   
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Left   
        //Left Quadrilateral  
        gl.glColor3f(1f, 0f, 1f); //Purple  
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Right  
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Right   
        //Right Quadrilateral  
        gl.glColor3f(0f, 1f, 1f); //Cyan  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Right   
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right   
        gl.glEnd();
        gl.glFlush();

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
