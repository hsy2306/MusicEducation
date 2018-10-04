package testmode;

import java.io.Serializable;

/**
 * Created by LeeDoBin on 2018-06-18.
 */

public class Song implements Serializable {
    Integer color;
    String name;

    public Song(Integer color, String name) {
        this.color = color;
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
