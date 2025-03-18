import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {
    private String fname;

    public DataLoader(String fname) {
        this.fname = fname;
    }

    public List<Point> load(){
        List<Point> data = new ArrayList<Point>();
        String name;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length < 2){
                    continue;
                }

                double[] values = new double[parts.length-1];

                for(int i = 0; i < values.length-1; i++){
                    values[i] = Double.parseDouble(parts[i]);
                }
                name = parts[parts.length-1];
                Point point = new Point(values, name);
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
