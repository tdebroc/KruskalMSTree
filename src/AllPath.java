import java.util.ArrayList;
import java.util.Iterator;


public class AllPath  implements Iterable<Path> {
  int maxPathWeight = 31;
  ArrayList< ArrayList<Path>> paths = new ArrayList<ArrayList<Path>>(maxPathWeight);
  public AllPath() {
    for (int i = 0; i < maxPathWeight; i++) {
      paths.add(new ArrayList<Path>());
    }
  }
  
  public void add(Path path) {
    paths.get(path.getWeight()).add(path);
  }

  @Override
  public Iterator<Path> iterator() {
    Iterator<Path> it = new Iterator<Path>() {

      private int currentIndex = -1;
      private int currentWeight = 1;

      @Override
      public boolean hasNext() {
        if (currentWeight >= paths.size()) {
          return false;
        }
        if (currentIndex < paths.get(currentWeight).size() - 1) {
          return true;
        }
        int currentWeightGuess = currentWeight;
        while (currentWeightGuess < paths.size()) {
          if (paths.get(currentWeightGuess).size() != 0) {
            return true;
          }
          currentWeightGuess++;
        }
        return false;
      }

      @Override
      public Path next() {
        if (currentIndex < paths.get(currentWeight).size() - 1) {
          currentIndex++;
        } else {
          currentWeight++;
          currentIndex = 0;
          while (currentWeight < paths.size()
              && paths.get(currentWeight).size() == 0) {
            currentWeight++;
          }
        }
        if (currentWeight >= paths.size()) {
          return null;
        }
        return paths.get(currentWeight).get(currentIndex);
      }

      @Override
      public void remove() {
          // TODO Auto-generated method stub
      }
    };
    return it;
  }
  
  
  
}
