package com.example.HtmlToPDF.HtmlToPDf.studentController;

import com.example.HtmlToPDF.HtmlToPDf.studentDao.Student;
import com.example.HtmlToPDF.HtmlToPDf.studentService.StudentServiceImpl;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static StudentServiceImpl studentServiceImpl;
    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
    }



    /**
     * Create Student
     * @param student Object
     * */
    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student)
    {
        return studentServiceImpl.create(student);
    }


    /**
     * Get All Records of Student
     *
     * */
    @GetMapping("/allStudent")
    public ModelAndView getAllStudents(Model model)
    {
        List<Student> students = studentServiceImpl.findAll();
        model.addAttribute("students",students);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Mango");
        return modelAndView;

    }




    /**
     * Get Index.html
     *
     *
     * @return*/
    @RequestMapping("/index")
    public  @ResponseBody List<Student> getIndexHtml(@RequestParam(name = "id",defaultValue = "1",required = true) int id, @RequestParam(name = "name",defaultValue = "world",required = true) String cGpa, Model Model) throws IOException, DocumentException {
      return studentServiceImpl.generatePatientCardPdf();

    }



    /**
     * Get Mango.html
     *
     * */
    @RequestMapping("/mango")
    public ModelAndView getMangoHtml(@RequestParam(name = "name",defaultValue = "mango",required = true) String name , @RequestParam(name = "marks",defaultValue = "10" ,required = true) int marks  ,Model Model)
    {
        Model.addAttribute("name",name);
        Model.addAttribute("marks",marks);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Mango");
        return modelAndView;
    }



    /**
     * Get Mango.html
     *
     * */
    @RequestMapping("/mangoPdf")
    public ModelAndView getMangoHtmlToPdf(@RequestParam(name = "name",defaultValue = "mango",required = true) String name , @RequestParam(name = "marks",defaultValue = "10" ,required = true) int marks  ,Model Model)
    {
      //  ByteArrayResource contents = new ByteArrayResource(PDFGenerator.generatePatientCard(patient));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Mango");
        return modelAndView;
    }

}
