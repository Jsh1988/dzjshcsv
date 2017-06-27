import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        CSVMain csv = new CSVMain("Cipher.csv");

        String[] codes = {"RMuiRdf01160141151156164", "lims8r3860lims1631411561441", "GZQRyr6870GZQR+0041431501451451631455A", "qkMfPjrd0561411551551651561511641511571567", "EOcTkerf3891511431450551431621451411550"};

        csv.Construct(codes);

    }

}
