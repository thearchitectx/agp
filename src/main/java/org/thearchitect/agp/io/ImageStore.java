package org.thearchitect.agp.io;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import javafx.scene.image.Image;

/**
 *
 * @author x4rb
 */
public class ImageStore {
    private static Map<String, Image> imageCache = new HashMap<>();
    private static Queue<String> imageCacheQueue = new ArrayDeque<>();
    
    public static Image getImage(String path) {
        Image img = imageCache.get(path);
        
        if (img==null) {
            System.out.println("Loading image "+ path);
            
            InputStream stream = ImageStore.class.getResourceAsStream(path);
            if (stream==null) {
                throw new IllegalArgumentException("Cannot find image '"+path+"'");
            }
            
            img = new Image(stream);
            imageCache.put(path, img);
            imageCacheQueue.offer(path);
            
            if (imageCache.size()>10) {
                String s = imageCacheQueue.poll();
                System.out.println("Removing from cache: "+ s);
                imageCache.remove(s);
            }
        } else {
            System.out.println("Reseting position on cache: "+ path);
            imageCacheQueue.remove(path);
            imageCacheQueue.offer(path);
        }
        
        return img;
    }
}
