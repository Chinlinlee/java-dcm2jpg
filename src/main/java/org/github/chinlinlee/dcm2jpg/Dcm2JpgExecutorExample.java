package org.github.chinlinlee.dcm2jpg;

import org.github.chinlinlee.dcm2jpg.Dcm2JpgExecutor.Dcm2JpgOptions;

public class Dcm2JpgExecutorExample {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Usage: java -jar dcm2jpg.jar <dcm_file_path> <jpg_file_path>");
                System.exit(1);
            }

            String dcmPath = args[0];
            String jpgPath = args[1];

            String format = getFormatFromFilename(jpgPath);

            Dcm2JpgOptions opt = new Dcm2JpgOptions();
            opt.format = format;

            if (format.equals("tiff")) {
                opt.compressionType = "JPEG";
            }

            Dcm2JpgExecutor.convertDcmToJpgFromFilename(dcmPath, jpgPath, opt);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
    }

    private static String getFormatFromFilename(String filename) {
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        return ext.toLowerCase();
    }
}
