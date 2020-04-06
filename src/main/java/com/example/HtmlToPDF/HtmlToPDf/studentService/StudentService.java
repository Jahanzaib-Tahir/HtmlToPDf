package com.example.HtmlToPDF.HtmlToPDf.studentService;

import com.example.HtmlToPDF.HtmlToPDf.studentDao.Student;
import com.example.HtmlToPDF.HtmlToPDf.studentRepo.StudentRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;


public interface StudentService {

    Student create(Student student);
    List<Student> findAll();
    Student findById(int id);
    String deleteById(int id);
    List<Student> generatePatientCardPdf() throws IOException, DocumentException;


}
