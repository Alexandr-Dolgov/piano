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
                case DO: y = 10; break;
                case RE: y = 15; break;
                case MI: y = 20; break;
                case FA: y = 25; break;
                case SOL: y = 30; break;
                case LYA: y = 35; break;
                case SI: y = 40; break;
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

}
