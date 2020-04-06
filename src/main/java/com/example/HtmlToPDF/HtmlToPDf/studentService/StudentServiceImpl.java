package com.example.HtmlToPDF.HtmlToPDf.studentService;
import com.example.HtmlToPDF.HtmlToPDf.studentRepo.StudentRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.spring4.SpringTemplateEngine;
import com.example.HtmlToPDF.HtmlToPDf.studentDao.Student;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    SpringTemplateEngine templateEngine;

    private static StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
     this.studentRepo = studentRepo;
    }

    @Override
    public Student create(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepo.findById(id).orElseThrow(()-> new EntityNotFoundException(""));
    }

    @Override
    public String deleteById(int id) {
         studentRepo.deleteById(id);
        return  "Deleted";
    }

    @Override
    public  List<Student> generatePatientCardPdf() throws IOException, DocumentException {
        Context context = new Context();

        List<Student> students = studentRepo.findAll();

        context.setVariable("students",students);
/*
        context.setVariable("gpa",student.getcGpa());
        context.setVariable("id",student.getId());
*/
        String htmlContentToRender = templateEngine.process("Mango", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources","templates")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        OutputStream outputStream = new FileOutputStream("src//test.pdf");
        renderer.createPDF(outputStream);
        outputStream.close();

        return  students;
    }

    private String xhtmlConvert(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }


}

