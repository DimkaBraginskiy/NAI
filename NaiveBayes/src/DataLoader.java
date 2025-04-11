import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    public static List<Point> load(String fname) {
        List<Point> data = new ArrayList<Point>();
        String name;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");


                String[] values = new String[parts.length-1];

                for(int i = 0; i < values.length; i++){
                    values[i] = String.valueOf(parts[i]);
                }

                name = String.valueOf(parts[0]);
                Point point = new Point(name, values);
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
