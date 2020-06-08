package com.subbu.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.subbu.flightreservation.entities.Reservation;

@Component
public class PDFGenerator {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(EmailUtil.class);

	
	public void generateItinerary(Reservation reservation,String filepath) { /*filepath:To which we want to save this,So that We can use this filepath later on, when we send the email attachment*/
		LOGGER.info("generateItinerary()");
		
		Document document=new Document();
	    try {
			PdfWriter.getInstance(document, new FileOutputStream(filepath));  //To write this document to a pdf file we need PdfWriter.
			document.open();  //It will open up the pdf document
			document.add(generateTable(reservation)); /*We can add any type of data to the pdf,like element,text data etc., I want to add a new table  [document.add(new PdfPTable(2));] that will contain Passenger,flight information in it,and also i want two columns */
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			LOGGER.error(" Exception in generateItinerary()"+e);
		}
		
	}

	private PdfPTable generateTable(Reservation reservation) {
		PdfPTable table = new PdfPTable(2);   //2 indicates column
		
		PdfPCell cell;                //Each cell represents a column
		
		
		cell =new PdfPCell(new Phrase("FLight Itinerary"));    //Phrase: Represents text
		cell.setColspan(2);            //It span the entire row.
		table.addCell(cell);
		
		
		cell =new PdfPCell(new Phrase("FLight Details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Operating Airlines");
		table.addCell(reservation.getFlight().getOperatingAirLines());
		
		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());
		
		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());

		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());
		
		table.addCell("DateOfDeparture");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());
		
		table.addCell("EstimatedDepartureTime");
		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
		
		cell =new PdfPCell(new Phrase("PassengerDetails"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstname());
		
		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastname());
		
		table.addCell("Middle Name");
		table.addCell(reservation.getPassenger().getMiddlename());
		
		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());
		
		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());
		
		
		return table;
	}
	

}
