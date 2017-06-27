import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cipher {

    //public static String cipherRegexp = "([a-zA-Z]{4})+((R|r)+?((d|f+)[0-9]{3}|[0-9]{4})([a-zA-Z]{4})?((\\+|\\-)[0-9]{3})?([0-9]*))";
    public static String cipherRegexp = "([a-zA-Z]{4})+.*((R|r)+?([\\w]{4})([a-zA-Z]{4})?((\\+|\\-)[0-9]{3})?([0-9]*))";

    public JSONObject parseCipher(String cipher) {

        JSONObject result = new JSONObject();

        //ArrayList<String> list = new ArrayList<String>();

        String g1 = "", g3 = "", g4 = "", g5 = "", g6 = "", g7 = "", g8 = "", g9 = "";

        Pattern p = Pattern.compile(cipherRegexp);
        Matcher m = p.matcher(cipher);

        if(m.find()) {
            if(m.group(1) != null && m.group(2) != null) {

                g1 = m.group(1);

                result.put("DriveCode", g1);

                g3 = m.group(3);

                //result.put("DriveCode", g1);

                if(m.group(4) != null) {

                    g4 = m.group(4);

                    result.put("ListCode", g4);

                    if(g4.contains("d")) {
                        result.put("Dangerous", true);
                    } else {
                        result.put("Dangerous", "");
                    }

                    if(g4.contains("f")) {
                        result.put("Fragile", true);
                    } else {
                        result.put("Fragile", "");
                    }
                }

                // Drive Code From List
                if(m.group(5) != null) {
                    g5 = m.group(5);
                    result.put("DriveCodeFromList", g5);
                }

                // Temperature
                if(m.group(6) != null) {

                    g6 = m.group(6);

                    result.put("Temperature", g6);

                    if(m.group(7) != null) {
                        g8 = m.group(7);
                    }

                } else {
                    result.put("Temperature", "");
                }

                // Name
                if(m.group(8) != null) {
                    g8 = parseWord(m.group(8));
                    result.put("Name", g8);
                }

            }

            /*System.out.println(g1);
            System.out.println(g3);
            System.out.println(g4);
            System.out.println(g5);
            System.out.println(g6);
            System.out.println(g7);
            System.out.println(g8);
            System.out.println(g9);*/

        } else {
            System.out.println("Cipher is incorrect!");
        }

        return result;

    }

    public String parseWord(String cipherNumber) {

        String[] c = cipherNumber.split("(?<=\\G.{3})");

        c = checkWorkCode(c);

        String word = "";

        for(int i=0; i <= c.length-1;i++) {
            if(c[i].length() == 3) {
                int oct = Integer.valueOf(c[i], 8);
                char ch = (char) oct;
                word = word + ch;
            }
        }

        return word;
    }

    public String[] checkWorkCode(String[] Array) {

        int index = 0;

        String[] newArray = new String[Array.length-1];

        for(int i=0;i<Array.length-1;i++) {
            if(Array[i].length() == 3) {
                newArray[index] = Array[i];
                index++;
            }
        }

        return newArray;
    }
}
