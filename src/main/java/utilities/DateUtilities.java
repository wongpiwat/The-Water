package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

	public static String getDateNumber() {
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date date = new Date();
		return dateTimeFormat.format(date);
	}

	public static Date getDateForm(String string) {
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
		try {
			return dateTimeFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getMonth(Date date) {
		return new SimpleDateFormat("MMMM").format(date);
	}

	public static String getYear(Date date) {
		return new SimpleDateFormat("YYYY").format(date);
	}

	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_MONTH);
	}
}