/*
package com.example.HtmlToPDF.HtmlToPDf.studentDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.HtmlToPDF.HtmlToPDf.studentDao.Student;
import com.example.HtmlToPDF.HtmlToPDf.studentRepo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Component
public class PDFGenerator {

    public static String PATIENT_CARD_HTML;
    public static String MOD_PATIENT_CARD;

    private static StudentRepo studentRepo;

    @Autowired
    public PDFGenerator(StudentRepo patientDocumentRepository) {
        this.studentRepo = patientDocumentRepository;
    }

   @Value("${templates/Mango}")
    public void setPatientCard(String patientCardHtml) {
        PATIENT_CARD_HTML = patientCardHtml;
    }

   @Value("${templates}")
    public void setModPatientCard(String modCard) {
        MOD_PATIENT_CARD = modCard;
    }


    public static void convertToPDF(String htmlFileString, String pdfFileName) {
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlFileString);
            renderer.layout();

            try (FileOutputStream fos = new FileOutputStream(pdfFileName)) {
                renderer.createPDF(fos);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static byte[] generatePatientCard(Student patient) {

        byte[] contents = null;
        try {
            if (!MOD_PATIENT_CARD.endsWith("/"))
                MOD_PATIENT_CARD = MOD_PATIENT_CARD + "/";

            String htmlFileString;

            String profilePictureName = "nopicture.png";

*/
/*
           byte[] profilePictureBytes = profile==null ? null : profile.getFile().getData();
            File fileProfilePic = null;

            if (profilePictureBytes != null) {
                profilePictureName = patient.getMrNumber()+".png";
                fileProfilePic = new File(MOD_PATIENT_CARD + profilePictureName);
                Files.write(fileProfilePic.toPath(),profilePictureBytes);
            }
*//*


            htmlFileString = readFile(PATIENT_CARD_HTML);
            String patientName = patient.getFirstName();
            String cGpa = String.valueOf(patient.getcGpa());
            Integer a;
            */
/*
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				registrationDate = formatter.format(regDate);*//*


            // replace dynamic data in html template
            htmlFileString = htmlFileString.replace("_firstName", patientName).replace("_cGpa", cGpa);

            String pDFFilePath = MOD_PATIENT_CARD + patientName + ".pdf";
            convertToPDF(htmlFileString, pDFFilePath);
            contents = readFileToByteArray(pDFFilePath);

            // deleting extra generated files

            File filePDF = new File(pDFFilePath);
            if (filePDF != null) {
                filePDF.delete();
            }

        } catch (Exception e) {
           }

        return contents;
    }

    private static String getPatientName(String firstName, String middleName, String lastName) {
        String patientName = "";

        if (firstName != null && firstName != "")
            patientName += firstName;
        if (middleName != null && middleName != "")
            patientName += " " + middleName;
        if (lastName != null && lastName != "")
            patientName += " " + lastName;
        return patientName;

    }

    private static byte[] readFileToByteArray(String fileName) {
        File file = new File(fileName);
        // init array with file length
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytesArray); // read file into bytes[]
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesArray;
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream in = new FileInputStream(fileName);
        byte[] b = new byte[(int) file.length()];
        int len = b.length;
        int total = 0;

        while (total < len) {
            int result = in.read(b, total, len - total);
            if (result == -1) {
                break;
            }
            total += result;
        }
        in.close();
        return new String(b, StandardCharsets.UTF_8);
    }

}*/
