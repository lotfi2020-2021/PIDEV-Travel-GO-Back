package tn.esprit.TRAVELGO.service;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import tn.esprit.TRAVELGO.entities.Salary;
import tn.esprit.TRAVELGO.entities.Salary;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserPdfExporter {
	private List<Salary> salaries;

	public UserPdfExporter(List<Salary> salaries) {
		super();
		this.salaries = salaries;
	}
	private void WriteTableHeader(PdfPTable table) {
	PdfPCell cell=new PdfPCell();
	cell.setBackgroundColor(Color.BLUE);
	cell.setPadding(5);
	
	cell.setPhrase(new Phrase("SalaryId"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Increanse"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Decrease"));
	table.addCell(cell);
	//cell.setPhrase(new Phrase("UserId"));
	//table.addCell(cell);
	cell.setPhrase(new Phrase("Amount"));
	table.addCell(cell);
	}
	private void WriteTableData(PdfPTable table) {
		for(Salary salary:salaries) {
			table.addCell(String.valueOf(salary.getIdSal()));
			table.addCell(String.valueOf(salary.getIncrease()));
			table.addCell(String.valueOf(salary.getReduction()));
			//table.addCell(salary.getUser().toString());
			table.addCell(String.valueOf(0));
		}
		
	}
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		com.lowagie.text.Font fontTitle= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		Paragraph paragraph=new Paragraph("Details Of  Salaries",fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		Font fontparagraph=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		PdfPTable table=new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		WriteTableHeader(table);
		WriteTableData(table);
		document.add(paragraph);
		document.add(table);
		document.close();
	}
}
