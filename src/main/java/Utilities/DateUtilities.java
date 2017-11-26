package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {

	public static String getDateNumber() {
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date date = new Date();
		return dateTimeFormat.format(date);
	}
}