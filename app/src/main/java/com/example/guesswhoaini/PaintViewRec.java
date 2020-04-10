
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;


public class PaintViewRec extends View {

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
    private DatabaseReference mSearchedLocationReference;


    public PaintViewRec(Context context) {
        this(context, null);
    }

    public PaintViewRec(Context context, AttributeSet attrs) {
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
        red();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor);

        mSearchedLocationReference= FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/")
                .getReference();

        mSearchedLocationReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.d("Locations updated", "location: " + dataSnapshot); //log


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // read query is cancelled.
                    }
                });

        for (FingerPath fp : paths) {
            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);

            if (fp.color==0)
                mPaint.setColor(DEFAULT_COLOR);
            else if (fp.color==1)
                mPaint.setColor(DEFAULT_COLOR2);
            else
                mPaint.setColor(DEFAULT_COLOR3);

            //mCanvas.drawPath(fp.path, mPaint);

        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }


        //FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/")
         //       .getReference()
         //       .push()
          //      .setValue(new LocDBMes(x,y,colorIndicator
          //              )
           //     );
}
