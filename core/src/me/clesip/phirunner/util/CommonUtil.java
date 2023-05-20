package me.clesip.phirunner.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.ogg.util.OggInfoReader;
import org.jaudiotagger.audio.wav.util.WavInfoReader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CommonUtil {
    public static float getSongDuration(File file) throws IOException, CannotReadException { // from stackoverflow
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rwd");
//        OggInfoReader oggInfoReader = new OggInfoReader();
        WavInfoReader infoReader = new WavInfoReader();
        GenericAudioHeader read = infoReader.read(randomAccessFile);
        return read.getPreciseLength();
    }

    public static Vector2 getRotatedPosition(Vector2 center, double deltaX, double deltaY, double rotateDegree) {
        if (deltaX == 0 && deltaY == 0) return center;
        double lineSin = Math.sin(Math.toRadians(rotateDegree));
        double lineCos = Math.cos(Math.toRadians(rotateDegree));
        double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double deltaSin = deltaY / hypotenuse;
        double deltaCos = deltaX / hypotenuse;
        double sin = deltaSin * lineCos + deltaCos * lineSin;
        double cos = lineCos * deltaCos - lineSin * deltaSin;
        return new Vector2((float) (center.x + hypotenuse * cos), (float) (center.y + sin * hypotenuse));
    }

    public static void drawTextureWithCenterScale(SpriteBatch batch, Vector2 center, Texture texture, float scale, float rotateDegree) {
        batch.draw(texture, center.x - texture.getWidth() * scale / 2, center.y - texture.getHeight() * scale / 2,
                texture.getWidth() * scale / 2,texture.getHeight() * scale / 2, texture.getWidth() * scale,
                texture.getHeight() * scale,1,1, rotateDegree, 0, 0, texture.getWidth(),
                texture.getHeight(),false,false);
    }

    public static void drawTextureWithCenterSize(SpriteBatch batch, Vector2 center, Texture texture, float width, float height, float rotateDegree) {
        batch.draw(texture, center.x - width / 2, center.y - height / 2,
                width / 2,height / 2, width,
               height,1,1, rotateDegree, 0, 0, texture.getWidth(),
                texture.getHeight(),false,false);
    }

    public static void drawTextureWithCenterSizeRealSize(SpriteBatch batch, Vector2 center, Texture texture, float width, float height, float rotateDegree, int srcX, int srcY, int srcWidth, int srcHeight) {
        batch.draw(texture, center.x - width / 2, center.y - height / 2,
                srcWidth / 2f,srcHeight / 2f, width,
                height,1,1, rotateDegree, srcX, srcY, srcWidth, srcHeight,false,false);
    }
}
