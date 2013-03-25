import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
  

public class OptimisedBuildWorld {
   
  /**
   * Read the text file and Builds worlds !! Faster but uglier implementation.
   */
  public static void buildWorld(String fileName, KruskalOptimised kruskalOptimised) {
    File file = new File(fileName);
    FileInputStream fin;
    try {
      fin = new FileInputStream(file);
      FileChannel fc = fin.getChannel();
      MappedByteBuffer mapByteBuff = fc.map(FileChannel.MapMode.READ_ONLY, 0,
          file.length());
      int countLine = 0;
      int nbEdge = 0, nbCity = 0;
      StringBuilder string = new StringBuilder();
      // Pass city and edge count
      while (mapByteBuff.hasRemaining() && countLine < 2) {
        char c = (char)mapByteBuff.get();
        if (c == '\n') {
          countLine++;
          if (countLine == 1) {
            nbCity = Integer.parseInt(string.toString());
          }
          if (countLine == 2) {
            nbEdge = Integer.parseInt(string.toString());
          }
          string.setLength(0);
        } else  {
          string.append(c);          
        }
      }

      kruskalOptimised.cityCount = nbCity;
      kruskalOptimised.edgeCount = nbEdge;
      kruskalOptimised.paths = new AllPath();
      
      int wordIndex = 0;
      String city1Name = "", city2Name = "";
      while (mapByteBuff.hasRemaining()) {
        char c = (char)mapByteBuff.get();
        if (c == ' ') {
          wordIndex++;
          if (wordIndex == 1) {
            city1Name = string.toString(); 
          } else if (wordIndex == 2) {
            city2Name = string.toString(); 
          }
          string.setLength(0);
        } else if (c == '\n') {
          addPath(city1Name, city2Name, string.toString(), kruskalOptimised);
          string.setLength(0);
          wordIndex = 0;
        } else {
          string.append(c);
        }
      }

      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  
  public static void addPath(String city1Name, String city2Name, String weightS,
      KruskalOptimised kruskalOptimised) {
    City city1 = kruskalOptimised.getCity(city1Name);
    int weight = Integer.parseInt(weightS);
    City city2 = kruskalOptimised.getCity(city2Name);
    
    kruskalOptimised.paths.add(new Path(weight, city1, city2));
    
    // kruskalOptimised.paths.add(new Path(weight, city1, city2));
  }
}
