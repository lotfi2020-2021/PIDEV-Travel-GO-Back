package tn.esprit.TRAVELGO.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Salary;
import tn.esprit.TRAVELGO.repository.SalaryRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class PdfGeneratorService {
	@Autowired
	SalaryService salaryservice;
	@Autowired
	SalaryRepository salaryrepository;
	private List<Salary> salaries;
	
public PdfGeneratorService(List<Salary> salaries) {
		super();
		this.salaries = salaries;
	}
private void WriteTableHeader() {
	
}
private void WriteTableData() {
	
}

public void export(HttpServletResponse response) throws DocumentException, IOException {
	Document document=new Document(PageSize.A4);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	Font fontTitle= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	fontTitle.setSize(18);
	Paragraph paragraph=new Paragraph("Details Of your Salary",fontTitle);
	paragraph.setAlignment(Paragraph.ALIGN_CENTER);
	Font fontparagraph=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	fontTitle.setSize(18);
	
	document.add(paragraph);
	
	document.close();
}
}
