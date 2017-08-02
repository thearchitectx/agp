package org.thearchitect.agp.cell;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.InputStreamReader;

/**
 *
 * @author x4rb
 */
public class Sheet {
    private String image;
    private int[] blocked;

    public Sheet() {
    }

    public Sheet(String image, int[] blocked) {
        this.image = image;
        this.blocked = blocked;
    }

    public String getImage() {
        return image;
    }

    public int[] getBlocked() {
        return blocked;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBlocked(int[] blocked) {
        this.blocked = blocked;
    }

    public static Sheet load(String ymlResource) {
        YamlReader reader = new YamlReader(new InputStreamReader(Sheet.class.getResourceAsStream(ymlResource)));
        try {
            return reader.read(Sheet.class);
        } catch (YamlException ex) {
            throw new RuntimeException(ex);
        }
    }
}
