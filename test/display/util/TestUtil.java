package display.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestUtil {
	
	private static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
	    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
	        for (int x = 0; x < img1.getWidth(); x++) {
	            for (int y = 0; y < img1.getHeight(); y++) {
	                if (img1.getRGB(x, y) != img2.getRGB(x, y))
	                    return false;
	            }
	        }
	    } else {
	        return false;
	    }
	    return true;
	}

	public static boolean diffImages(BufferedImage img, String path){
		BufferedImage temp = null;
		try{
			temp = ImageIO.read(new File(path));
		}catch(IOException | NullPointerException e){
			System.out.println("Error");
			e.printStackTrace();
		}
		if(img == null){
			if(temp == null){
				return true;
			}
			return false;
		}else if(temp == null){
			return false;
		}
		
		return TestUtil.bufferedImagesEqual(img, temp);
	}
}
