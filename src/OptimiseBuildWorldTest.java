import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class OptimiseBuildWorldTest {

  /**
   * Read the text file and Builds worlds !!
   * Faster but uglier implementation.
   */
  public static void buildWorld2(String fileName, Kruskal kruskal) {
    BufferedReader br = null; 
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    String line;
    StringBuilder string = new StringBuilder();
    int j;
    try {
      br.readLine();
      br.readLine();
      while ((line = br.readLine()) != null) {
        string.setLength(0);
        j = 0;
        char p;
        String city1name = "";
        String city2name = "";
        String weightname = "";
        for (int i = 0; i < line.length(); i++ ) {
          p =line.charAt(i);
          if (p == ' ') {
            if (j ==0) {
              city1name = string.toString();
            } else {
              city2name = string.toString();
            }
            j++;
            string.setLength(0);
          } else {
            string.append(p);
          }
        }
        weightname = string.toString();
        // String[] lineSplitted = line.split(" ");
        City city1 = kruskal.getCity(city1name);
        City city2 = kruskal.getCity(city2name);
        int weight = Integer.parseInt(weightname);
        kruskal.paths.add(new Path(weight, city1, city2));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
