import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static java.util.List<Point> load(String fname) {
        List<Point> data = new ArrayList<Point>();

        String name;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            while((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }


                String[] parts = line.split(",", 2);
                if (parts.length < 2) {
                    continue;
                }

                String label = parts[0];
                String text = parts[1];
                System.out.println("Label: " + label);
                System.out.println("Text: " + text);

                double[] occurrences = new double[26];


                text = text.toLowerCase();
                for (int i = 0; i < text.length(); i++) {
                    char c = text.charAt(i);
                    if (c >= 'a' && c <= 'z') {
                        occurrences[c - 'a']++;
                    }
                }


                Point point = new Point(occurrences, label);
                data.add(point);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

}
