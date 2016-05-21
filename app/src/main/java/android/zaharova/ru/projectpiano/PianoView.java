package android.zaharova.ru.projectpiano;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class PianoView extends View {

    public PianoView(Context context) {
        super(context);
    }

    public PianoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    Rect[] rectangles;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float leftBorderX = 10;
        drawPiano(canvas, leftBorderX);
    }

    //рисуем пианино (клавиатуру)
    private void drawPiano(Canvas canvas, float leftBorderX) {
        float viewHeight = getHeight();
        float viewWidth = getWidth();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);

        Paint paintFill = new Paint();
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(Color.BLACK);

        float keyHeightRate = 0.9f;
        float sharpKeyWidthRate = 0.7f;
        float sharpKeyHeightRate = 0.5f;

        float bottomBorderY = 10;
        int keyWidth = Math.round((viewWidth - leftBorderX * 2) / 7);
        int keyHeight = Math.round(viewHeight * keyHeightRate);

        int sharpKeyWidth = Math.round((1 - sharpKeyWidthRate) * 2 * keyWidth);
        int sharpKeyHeight = Math.round(sharpKeyHeightRate * keyHeight);

        rectangles = new Rect[12];
        int x = Math.round(leftBorderX);
        int y = Math.round(viewHeight - bottomBorderY - keyHeight);
        for (int i = 0; i < 7; i++) {
            rectangles[i] = new Rect(x, y, x + keyWidth, y + keyHeight);
            canvas.drawRect(rectangles[i], paint);
            x = x + keyWidth;
        }
        int j = 7;
        for (int i = 7; i < 13; i++) {
            if (i - 7 == 2) {
                continue;
            }
            x = rectangles[i - 7].left + Math.round(keyWidth*sharpKeyWidthRate);
            y = rectangles[i - 7].top;

            rectangles[j] = new Rect(x, y, x + sharpKeyWidth, y + sharpKeyHeight);
            canvas.drawRect(rectangles[j], paintFill);

            j++;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }

        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        //черные кнопки
        int i;
        boolean contains = false;
        for (i = 7; i < rectangles.length; i++) {
            if (rectangles[i].contains(x,y)) {
                contains = true;
                break;
            }
        }
        if (contains) {
            //была нажата кнопка rectangle[i]
        }

        Note note = null;
        //белые кнопки
        contains = false;
        for (i = 0; i < 7; i++) {
            if (rectangles[i].contains(x,y)) {
                contains = true;
                break;
            }
        }
        if (contains) {
            //была нажата кнопка rectangle[i]
            switch (i) {
                case 0: note = new Note(Note.Name.DO); break;
                case 1: note = new Note(Note.Name.RE); break;
                case 2: note = new Note(Note.Name.MI); break;
                case 3: note = new Note(Note.Name.FA); break;
                case 4: note = new Note(Note.Name.SOL); break;
                case 5: note = new Note(Note.Name.LYA); break;
                case 6: note = new Note(Note.Name.SI); break;
            }
        }
        GameActivity gameActivity = (GameActivity)this.getContext();
        gameActivity.noteList.add(note);

        //перерисовываем нотный стан
        gameActivity.staffView.invalidate();

        return true;
    }

}
