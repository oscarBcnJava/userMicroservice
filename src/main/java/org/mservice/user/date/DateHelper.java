package org.mservice.user.date;

import org.mservice.user.constants.FormatType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oscarimac122 on 15/10/16.
 */
public class DateHelper {
    private static DateFormat dfUnderscores;
    private static DateFormat dfAllInRow;

    public static String getDateAsString(FormatType type, Date dateForTransform) {
        String formattedDate = null;
        switch (type) {
            case DD_MM_YYYY:
                if(dfUnderscores == null) {
                    dfUnderscores = new SimpleDateFormat("dd_mm_yyyy");
                }
                formattedDate = dfUnderscores.format(dateForTransform);
                break;
            case DDMMYYYY:
                if (dfAllInRow == null) {
                    dfAllInRow = new SimpleDateFormat("ddmmyyyy");
                }
                formattedDate = dfAllInRow.format(dateForTransform);
                break;
        }
        return formattedDate;
    }
}
