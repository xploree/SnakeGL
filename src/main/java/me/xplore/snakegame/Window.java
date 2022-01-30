package me.xplore.snakegame;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.IntBuffer;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private long glfwWindow;
    private final String windowName = "Snake";
    int windowWidth = 600;
    int windowHeight = 600;


    public void Run(){
        System.out.println("Running...");

        init();
        loop();
        // Free callbacks and destroy window
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);
        // Terminate GLFW and Free the ErrorCallback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();

    }
    public void init(){
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        glfwWindow = glfwCreateWindow(windowWidth, windowHeight, windowName, NULL, NULL);
        if(glfwWindow == NULL){
            throw new RuntimeException("Failed to Create Window.");
        }
        // Get the thread stack and push a new frame
        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(glfwWindow, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    glfwWindow,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make openGL current context
        glfwMakeContextCurrent(glfwWindow);
        // enable v-sync
        glfwSwapInterval(1);
        // show window
        glfwShowWindow(glfwWindow);

    }
    public void loop(){
        GL.createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        while ( !glfwWindowShouldClose(glfwWindow) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glfwWindow); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}

