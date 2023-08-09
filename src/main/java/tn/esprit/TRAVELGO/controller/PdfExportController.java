package tn.esprit.TRAVELGO.controller;

import com.lowagie.text.DocumentException;
import tn.esprit.TRAVELGO.service.PdfGeneratorService;
import tn.esprit.TRAVELGO.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PdfExportController {
	@Autowired
	SalaryService salaryservice;
private final PdfGeneratorService pdfgeneratorservice;
public  PdfExportController(PdfGeneratorService pdfgeneratorservice) {
	this.pdfgeneratorservice=pdfgeneratorservice;
}
@GetMapping("/pdf/generate")
@ResponseBody
public void PdfGenerate(HttpServletResponse response) throws DocumentException, IOException {
	response.setContentType("application/pdf");
	DateFormat dateformatter=new SimpleDateFormat("yyyy-mm-dd");
	String currentDateTime= dateformatter.format(new Date());
	String headerkey="Content-Disposition";
	String headervalue="Attachement; filename:pdf" + currentDateTime +".pdf";
	response.setHeader(headerkey, headervalue);
	//List<Salary> listsalary=salaryservice.listAll();
	
	this.pdfgeneratorservice.export(response);
}
}
