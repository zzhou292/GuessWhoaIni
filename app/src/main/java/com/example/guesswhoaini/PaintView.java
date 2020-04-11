
package com.example.guesswhoaini;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import static com.facebook.FacebookSdk.getApplicationContext;


public class PaintView extends View {

    public static int BRUSH_SIZE = 20;
    public static final int DEFAULT_COLOR = Color.RED;  //0
    public static final int DEFAULT_COLOR2 = Color.GREEN;  //1
    public static final int DEFAULT_COLOR3 = Color.BLUE;  //2
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final float TOUCH_TOLERANCE = 4;
    public int colorIndicator = 0;
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<FingerPath> paths = new ArrayList<>();
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    private LocDBMes dBMes= null;
    private ArrayList<Coordinates> coord;


    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
        coord = new ArrayList<Coordinates>();

    }

    private void simulateDrawing(ArrayList<Coordinates> newCoords) {
        for(Coordinates event : newCoords){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    touchStart(event.getX(), event.getY(), false);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE :
                    touchMove(event.getX(), event.getY(), false);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP :
                    touchUp(event.getX(), event.getY(), false);
                    invalidate();
                    break;
            }
        }
    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
    }

    public void red() {
        colorIndicator = 0;
    }

    public void green() {
        colorIndicator = 1;
    }

    public void blue() {
        colorIndicator = 2;
    }

    public void clear() {
        backgroundColor = DEFAULT_BG_COLOR;
        paths.clear();
        FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/").getReference().removeValue();
        red();
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor);

        for (FingerPath fp : paths) {

            //FirebaseOptions options = new FirebaseOptions.Builder()
                    //.setApplicationId("guesswhoa-322a1")
                    //.setApiKey("AIzaSyCaQnv8AIWi9h0zjZSMunBOoNELiLMgYx4")
                    //.setDatabaseUrl("https://guesswhoa-322a1-58abe.firebaseio.com/")
                    //.build();



            //FirebaseApp.initializeApp(this.getContext(), options, "guesswhoa-322a1-58abe");
            //FirebaseApp secondApp = FirebaseApp.getInstance("guesswhoa-322a1-58abe");


            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);

            if (fp.color==0)
                mPaint.setColor(DEFAULT_COLOR);
            else if (fp.color==1)
                mPaint.setColor(DEFAULT_COLOR2);
            else
                mPaint.setColor(DEFAULT_COLOR3);

            //FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/")
              //      .getReference()
                //    .push()
                  //  .setValue(new FingerPath(fp.color, fp.strokeWidth, fp.path,fp.x,fp.y,fp.mx,fp.my
                       //     )
                    //);
            mCanvas.drawPath(fp.path, mPaint);

        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y, boolean addToCoord) {
        mPath = new Path();
        FingerPath fp = new FingerPath(colorIndicator, strokeWidth, mPath, x,y,mX,mY);
        paths.add(fp);


        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;

        if(addToCoord) {
            coord.add(new Coordinates(x, y, MotionEvent.ACTION_DOWN));
        }
    }

    private void touchMove(float x, float y, boolean addToCoord) {

        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            System.out.println("x"+x);
            System.out.println("y"+y);
            mX = x;
            mY = y;

            if(addToCoord) {
                coord.add(new Coordinates(x, y, MotionEvent.ACTION_MOVE));
            }
        }

       // FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/")
          //  .getReference()
           //  .push()
            //.setValue(new LocDBMes(x,y,colorIndicator
           //    )
        // );
    }

    private void touchUp(float x, float y, boolean addToCoord) {
        mPath.lineTo(x, y);
        if(addToCoord) {
            coord.add(new Coordinates(mX, mY,  MotionEvent.ACTION_UP));
        }

        for(Coordinates c : coord){
            String nextStringIdx = getNextString();
            FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/").getReference().child(nextStringIdx).child("x").setValue(c.getX());
            FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/").getReference().child(nextStringIdx).child("y").setValue(c.getY());
            FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/").getReference().child(nextStringIdx).child("action").setValue(c.getAction());
        }
        coord.clear();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y,true);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y,true);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp(x,y,true);
                invalidate();
                break;
        }

        return true;
    }

    private String getNextString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((new Date()).getTime());
        Random random = new Random();
        for(int i = 0; i < 32; i++){
            int c = random.nextInt(26) + 97;
            stringBuilder.append((char)c);
        }
        return stringBuilder.toString();
    }
}