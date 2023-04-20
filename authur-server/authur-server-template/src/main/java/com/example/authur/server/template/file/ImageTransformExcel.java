package com.example.authur.server.template.file;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;


/**
 * @author jibing.li
 * @Description 图片转成Excel
 * @date 2023/4/14 18:35
 */
public class ImageTransformExcel {


        static {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        }

    public static BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            Mat mat = Imgcodecs.imread(path);
            int type = BufferedImage.TYPE_BYTE_GRAY;
            if (mat.channels() > 1) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            img = new BufferedImage(mat.width(), mat.height(), type);
            mat.get(0, 0, ((DataBufferByte) img.getRaster().getDataBuffer()).getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }



    public static void createExcel(BufferedImage img, String outputFileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        for (int y = 0; y < img.getHeight(); y++) {
            Row row = sheet.createRow(y);

            for (int x = 0; x < img.getWidth(); x++) {
                Cell cell = row.createCell(x);

                int rgb = img.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                // 将RGB值设置为单元格的值
                cell.setCellValue(((red << 16) | (green << 8) | (blue)));
            }
        }

        FileOutputStream out = new FileOutputStream(outputFileName);
        workbook.write(out);
        out.close();
    }



    public static void main(String[] args) throws IOException {
        BufferedImage img = loadImage("test.png");
        createExcel(img, "test.xlsx");
    }




}
