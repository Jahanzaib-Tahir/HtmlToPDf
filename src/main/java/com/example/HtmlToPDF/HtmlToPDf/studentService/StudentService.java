package com.example.HtmlToPDF.HtmlToPDf.studentService;

import com.example.HtmlToPDF.HtmlToPDf.studentDao.Student;
import com.example.HtmlToPDF.HtmlToPDf.studentRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface StudentService {

    Student create(Student student);
    List<Student> findAll();
    Student findById(int id);
    String deleteById(int id);
    ResponseEntity<?> generatePatientCardPdf(int  id, String userName);


}
