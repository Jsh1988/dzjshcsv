import com.csvreader.CsvWriter;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class CSVMain {

    public static String Cipher = "Шифр";
    public static String DriverCode = "Код водителя";
    public static String ListCode = "Код путевого листа";
    public static String Dangerous = "Опасный";
    public static String Fragile = "Хрупкий";
    public static String Temp = "Температура";
    public static String Name = "Наименование";

    public static String outputFile = "";
    public static boolean FileExist = false;

    public static CsvWriter csvOutput = null;

    public static JSONObject tempResult = null;

    public CSVMain(String fileName) {
        outputFile = fileName;

        // before we open the file check to see if it already exists
        FileExist = new File(fileName).exists();

        try {
            // use FileWriter constructor that specifies open for appending
            csvOutput = new CsvWriter(new FileWriter(fileName, true), ',');
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void Construct(String[] codes) {

        if(!FileExist) {
            setHeader();
        }

        for(int i=0; i<codes.length;i++) {
            System.out.println("code - " + codes[i]);
            Add2Body(codes[i]);
        }

        csvOutput.close();
    }

    public void setHeader() {
        try {
            csvOutput.write(Cipher);
            csvOutput.write(DriverCode);
            csvOutput.write(ListCode);
            csvOutput.write(Dangerous);
            csvOutput.write(Fragile);
            csvOutput.write(Temp);
            csvOutput.write(Name);
            csvOutput.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Add2Body(String code) {
        Cipher cipher = new Cipher();

        tempResult = cipher.parseCipher(code);

        System.out.println(tempResult.toJSONString());

        if(!tempResult.isEmpty()) {
            try {
                csvOutput.write(code);
                csvOutput.write(tempResult.get("DriveCode").toString());
                csvOutput.write(tempResult.get("ListCode").toString());
                csvOutput.write(tempResult.get("Dangerous").toString());
                csvOutput.write(tempResult.get("Fragile").toString());
                csvOutput.write(tempResult.get("Temperature").toString());
                csvOutput.write(tempResult.get("Name").toString());
                csvOutput.endRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }

            tempResult.clear();
        }

    }

}

