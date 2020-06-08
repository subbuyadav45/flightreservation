package com.subbu.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.subbu.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT = "Itinerary for your Flight:";

	@Value("${com.subbu.flightreservation.itinerary.eamil.body}")
	private String EMAIL_BODY = "Please Find Your Itinerary Attached....";

	@Autowired
	private JavaMailSender sender;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

	public void sendItinerary(String toAddress, String filePath) {    /*1st parameter:Email address of passenger,2nd: The path to the generated itinerary which the PdfGenerator generates*/
		LOGGER.info("Inside the sendItinerary()");

		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);  //It will help us to build the email_ message along with attachment
			/*
			 * true: This boolean flag tells, if there are multiple parts in this message,since we want to send out attachments we should pass in true,
			 *        otherwise we need not pass this parameter at all,by default it will be false
			 */
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinerary", new File(filePath));
	/*1St Parameter:: Name for our attachment=Itinerary,This will not show up to the client,but it is MimeMultipart,when we look at the message that goes out,it is the part of the email.
      2nd Param: What ever we pass here new File(filePath) that will be attached to the email*/
			sender.send(message);   /*It will send out the email,by using the information that we have configured in the properties*/
		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary:" + e);

		}

	}

}
