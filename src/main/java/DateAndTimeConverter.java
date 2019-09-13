/**
 * Class to convert date and time from dd/mm/yyyy and 24 hr format to dd mmm yyyy, 12 hr format
 */
public class DateAndTimeConverter {

    /**
     * Method converts dd/mm/yyyy string to dd mmm yyyy format
     * @param str in dd/mm/yyyy format
     * @return string in appropriate date format
     */
    private String convertDate(String str) {
        String returnStr = "";
        if (str.contains("/")) {
            String[] split = str.split("/");
            String day = split[0], month = split[1], year = split[2];
            int tempDay = Integer.parseInt(day);
            if (tempDay == 1 || tempDay == 21 || tempDay == 31) {
                day += "st";
            } else if (tempDay == 2 || tempDay == 22) {
                day += "nd";
            } else if (tempDay == 3 || tempDay == 23) {
                day += "rd";
            } else {
                day += "th";
            }
            int tempMonth = Integer.parseInt(month);
            String[] months = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"}; //SWITCH TO ENUM
            month = months[tempMonth - 1]; //- 1 cause indexing
            return day + " " + month + " " + year;
        } else {
            returnStr = str;
        }
        return returnStr;
    }

    /**
     * Method converts time from 24hr format to 12hr
     * @param str in 24hr format
     * @return str in 12hr format
     */
    private String convertTime(String str) {
        String returnStr = "";
        if (str.contains("am") || str.contains("pm")) {
            returnStr = str;
        } else {
            String hr = str.substring(0, 2), min = str.substring(2, 4), m = "";
            int hh = Integer.parseInt(hr);
            if (hh < 12) {
                m = "am";
            } else {
                m = "pm";
                hh -= 12;
            }
            if (hh == 0) {
                hh = 12;
            }
            hr = Integer.toString(hh);
            returnStr = hr + ":" + min + m;
        }
        return returnStr;
    }

    /**
     * Method to check if str is numeric
     * @param str to check if is numeric
     * @return true if is numeric, else false
     */
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    /**
     * Method to convert both date and time to correct format by calling convertDate and convertTime
     *
     * @param str the str
     * @return string in correct format
     */
    public String timeAndDateConverter(String str) {
        String returnStr = "";
        String[] split = str.split(" ");
        if (split.length == 2) {
            String date = convertDate(split[0]);
            String time = convertTime(split[1]);
            returnStr += date + ", " + time;
        } else {
            if (isNumeric(split[0])) { //is time
                returnStr = convertTime(split[0]);
            } else {
                returnStr = convertDate(split[0]);
            }
        }
        return returnStr;
    }
}
