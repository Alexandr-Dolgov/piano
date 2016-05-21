package android.zaharova.ru.projectpiano;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class StaffView extends View {

    private static final float distanceFromStuffLinesRate = 0.15f;

    public StaffView(Context context) {
        super(context);
    }

    public StaffView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawStaff(canvas);

        int x = 0;
        int y = 100;

        GameActivity gameActivity = (GameActivity)this.getContext();
        List<Note> noteList = gameActivity.noteList;
        for (Note note : noteList) {
            x = x + 100;
            switch (note.getName()) {
                case DO: y = 100; break;
                case RE: y = 150; break;
                case MI: y = 200; break;
                case FA: y = 250; break;
                case SOL: y = 300; break;
                case LYA: y = 350; break;
                case SI: y = 400; break;
            }

            //отрисовываем одну ноту
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.n4);
            float noteHeightRate = 0.40f;
            int noteHeight = Math.round(noteHeightRate * getHeight());
            canvas.drawBitmap(b, null, new Rect(x, y, x + b.getWidth(), y + noteHeight), new Paint());
        }

    }

    //рисуем нотный стна
    private void drawStaff (Canvas canvas) {
        float x1 = 0;
        float x2 = getWidth();
        float y = getHeight() * 0.2f;

        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);

        float distanceFromStuffLines = distanceFromStuffLinesRate * getHeight();
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(x1, y, x2, y, paint);
            y += distanceFromStuffLines;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("StaffView.onTouchEvent(...)");
        return true;
    }

}
