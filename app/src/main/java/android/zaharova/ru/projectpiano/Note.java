package android.zaharova.ru.projectpiano;

import android.graphics.Bitmap;

public class Note {

    private Name name;
    private Duration duration;
    private Bitmap picture;
    private Accidental accidental;

    public enum Name {
        DO, RE, MI, FA, SOL, LYA, SI
    }

    public enum Duration {
        WHOLE, HALF, QUARTER, EIGHTH
    }

    public enum Accidental {
        SHARP, FLAT, NATURAL
    }

    public Note(Name name) {
        this.name = name;
        duration = Duration.QUARTER;
        accidental = Accidental.NATURAL;
    }

    void test() {

    }

    public Duration getDuration() {
        return duration;
    }

    public Name getName() {
        return name;
    }
}
