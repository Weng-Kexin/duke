class DateAndTimeConverter { //contains bugs (time/date out of range will be undetected)

    private String convertDate(String str) {
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
        }
        return str;
    }

    private String convertTime(String str) {
        if (str.contains("am") || str.contains("pm")) {
            return str;
        } else {
            String hr = str.substring(0, 2), min = str.substring(2, 4), m = "";
            int hh = Integer.parseInt(hr); //mins = Integer.parseInt(min);
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
            return hr + ":" + min + m;
        }
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    String convert(String str) {
        String temp = "";
        String[] split = str.split(" ");
        if (split.length == 2) {
            String date = convertDate(split[0]);
            String time = convertTime(split[1]);
            temp += date + ", " + time;
        } else {
            if (isNumeric(split[0])) { //is time
                temp = convertTime(split[0]);
            } else {
                temp = convertDate(split[0]);
            }
        }
        return temp;
    }
}
