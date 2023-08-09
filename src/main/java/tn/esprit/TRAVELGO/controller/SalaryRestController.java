package tn.esprit.TRAVELGO.controller;

import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.TRAVELGO.entities.Salary;
import tn.esprit.TRAVELGO.service.SalaryService;
import tn.esprit.TRAVELGO.service.UserPdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class SalaryRestController {
@Autowired
SalaryService salaryservice;
@PutMapping("/company/increase/{iduser}")
@ResponseBody
public void augmentation(@PathVariable("iduser") Long idUser ) {
	salaryservice.increase(idUser);
}
@PostMapping("/company/add-salary")
@ResponseBody
public Salary addbusiness(@RequestBody Salary sal) {
	return salaryservice.addSalary(sal);
	
}
@PutMapping("/company/affect-salary/{idsal}/{idemp}")
@ResponseBody
public void AffectSal(@PathVariable("idsal") Long idSal,@PathVariable("idemp") Long idEmp) {
	salaryservice.AffectSalary(idSal, idEmp);
}
@PutMapping("/company/decrease/{iduser}")
@ResponseBody
public void reduction(@PathVariable("iduser") Long idUser ) {
	salaryservice.decrease(idUser);
}
@GetMapping("/company/retrieve-all-salaries")
@ResponseBody
public List<Salary> RetrieveAllSalaries(){
	List<Salary> salarys=salaryservice.listAll();
	return salarys;
}
@GetMapping("/company/salaries/export")
public void PdfGenerator(HttpServletResponse response) throws DocumentException, IOException {
	response.setContentType("application/pdf");
	DateFormat dateformatter=new SimpleDateFormat("yyyy-mm-dd");
	String currentDateTime= dateformatter.format(new Date());
	String headerkey="Content-Disposition";
	String headervalue="Attachement; filename:pdf" + currentDateTime +".pdf";
	response.setHeader(headerkey, headervalue);
	List<Salary>listsalaries=salaryservice.listAll();
	UserPdfExporter exporter=new UserPdfExporter(listsalaries);
	exporter.export(response);
	
}
}
