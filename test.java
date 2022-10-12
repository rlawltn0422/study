public class test {

	public static String getAge(String JuminNO) {
		if (JuminNO == null)
			return "";
		if (JuminNO.length() == 0)
			return "";
		if (JuminNO.length() < 13)
			return "";

		JuminNO = JuminNO.replaceAll("-", "");

		int nAge = 0;

		// 1.생년월일 계산
		int gubun = toInt(JuminNO.substring(6, 7));
		String ssn1 = JuminNO.substring(0, 6);

		switch (gubun) {
		case 1:
		case 2:
		case 5:
		case 6:
			JuminNO = "19" + ssn1;
			break;
		case 3:
		case 4:
		case 7:
		case 8:
			JuminNO = "20" + ssn1;
			break;
		default:
			JuminNO = "-1";
			break;
		}
		if (JuminNO.equals("-1"))
			return "";

		int nBirthYear = toInt(JuminNO.substring(0, 4));
		int nBirthMonth = toInt(JuminNO.substring(4, 6));
		int nBirthDay = toInt(JuminNO.substring(6, 8));

		// 2.현재일자
		String strCurrentDate = getCurrentTime("yyyyMMdd");
		int nCurrentYear = Integer.parseInt(strCurrentDate.substring(0, 4));
		int nCurrentMonth = Integer.parseInt(strCurrentDate.substring(4, 6));
		int nCurrentDay = Integer.parseInt(strCurrentDate.substring(6, 8));
		int nMonth = 0;

		if (nCurrentYear > nBirthYear) {
			nAge = nCurrentYear - 1 - nBirthYear;
		}

		if (nCurrentMonth > nBirthMonth) {
			nAge += 1;

			if (nCurrentDay > nBirthDay) {
				nMonth = nCurrentMonth - nBirthMonth;
			} else {
				nMonth = nCurrentMonth - 1 - nBirthMonth;
			}
		} else {
			if (nCurrentMonth == nBirthMonth) {
				if (nCurrentDay > nBirthDay) {
					nAge += 1;
				} else {
					if (nCurrentYear != nBirthYear) {
						nMonth = 11;
					}
				}
			} else {
				nMonth = (nCurrentMonth + 12) - nBirthMonth;

				if (nCurrentDay <= nBirthDay) {
					nMonth -= 1;
				}
			}
		}

		return "" + nAge;
	}

	public static int toInt(String arg) {
		return new Integer(arg.trim()).intValue();
	}

	public static String getCurrentTime(String format) {
		String currDate = "";

		if (format == null) {
			format = "yyyyMMddHHmmss";
		}

		format = format.replaceAll("HH24", "HH");
		format = format.replaceAll("MI", "mm");
		format = format.replaceAll("SS", "ss");
		format = format.replaceAll("DY", "E");
		currDate = getTime(new Date(), format);

		return currDate;
	}

	public static String getTime(java.util.Date date, String format) {

		if (date == null) {
			return "";
		}

		if (format == null || format.equals("")) {
			format = "yyyy'년'MM'월'dd'일 'HH'시'mm'분'dd'초'";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static void main(String[] args) {
		System.out.println(getAge("801211-1092835")); // 40
		System.out.println(getAge("900611-2124921")); // 31
	}
}