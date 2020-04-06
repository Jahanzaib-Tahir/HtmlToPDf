package com.example.HtmlToPDF.HtmlToPDf.studentService;


import com.example.HtmlToPDF.HtmlToPDf.studentDao.Student;
import com.example.HtmlToPDF.HtmlToPDf.studentRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    private static  StudentRepo studentRepo;

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
    public ResponseEntity<?> generatePatientCardPdf( int id, String userName) {
/*        Student student =studentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("was not found for mr-number " + id));
       // ByteArrayResource contents=new ByteArrayResource(PDFGenerator.generatePatientCard(student));


        //update patient card record

        HttpHeaders headers = new HttpHeaders();
        *//*headers.setContentType(MediaType.parseMediaType("application/pdf"));*//*
        headers.add("Content-Disposition", "inline; filename=patientCard.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .contentLength(contents.getByteArray().length)
                .headers(headers)
                .body(contents);*/
  return null;
    }


}

