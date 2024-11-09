package iot.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

public class ImageUtil {
    private static final String folderPath = "src/main/resources/static/images";

    public static void saveImage(String base64Image) {
        String outputFilePath = folderPath + "/" + LocalDateTime.now() + ".jpeg";

        String imageData = base64Image.substring(base64Image.indexOf(":") + 3, base64Image.length() - 4);
        try (OutputStream outputStream = new FileOutputStream(outputFilePath)) {
            byte[] imageBytes = java.util.Base64.getDecoder().decode(imageData);
            outputStream.write(imageBytes);
            outputStream.flush();
            System.out.println("##### Ghi anh thanh cong #####");
        } catch (Exception e) {
            System.out.println("##### Loi luu anh #####");
        }
    }
}
