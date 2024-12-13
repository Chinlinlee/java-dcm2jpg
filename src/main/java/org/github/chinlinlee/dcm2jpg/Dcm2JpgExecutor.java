package org.github.chinlinlee.dcm2jpg;

import org.dcm4che3.image.ICCProfile;

import java.io.File;

public class Dcm2JpgExecutor {
    public static class ConvertStatus {
        public Boolean status;
        public String message;
    }

    public static class Dcm2JpgOptions {
        public String iccProfileName="no";
        public int frameNumber=1;
        public Number jpgQuality= 1.0;
        public int windowCenter=Integer.MAX_VALUE;
        public int windowWidth=Integer.MAX_VALUE;
        public String format = "JPEG";
        public String writerClass = "com.sun.imageio.plugins.*";
        public String compressionType = null;
    }

    public static ConvertStatus convertDcmToJpgFromFilename(String dcmFilename, String jpgFilename, Dcm2JpgOptions dcm2JpgOptions) {
        ConvertStatus status = new ConvertStatus();
        try {
            Dcm2Jpg dcm2Jpg = new Dcm2Jpg();

            dcm2Jpg.initImageWriter(dcm2JpgOptions.format, null, dcm2JpgOptions.writerClass, dcm2JpgOptions.compressionType, dcm2JpgOptions.jpgQuality);
            dcm2Jpg.setReadImage(dcm2Jpg::readImageFromImageInputStream);

            // Set window level
            if (dcm2JpgOptions.windowCenter!=Integer.MAX_VALUE && dcm2JpgOptions.windowWidth!=Integer.MAX_VALUE) {
                   dcm2Jpg.setWindowCenter(dcm2JpgOptions.windowCenter);
                   dcm2Jpg.setWindowWidth(dcm2JpgOptions.windowWidth);
            }

            dcm2Jpg.setFrame(dcm2JpgOptions.frameNumber);
            dcm2Jpg.setICCProfile(ICCProfile.Option.valueOf(dcm2JpgOptions.iccProfileName));


            File dcmFile = new File(dcmFilename);
            File imageFile = new File(jpgFilename);
            dcm2Jpg.convert(dcmFile, imageFile);
            status.status = true;
            status.message = "convert DICOM to JPEG successful";
            return status;
        } catch (Exception e) {
            status.status = false;
            status.message = "Failure: " + e.toString();
            return status;
        }
    }
}
