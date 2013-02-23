import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;



public class Kruskal {
  SortedPathList paths = new SortedPathList();
  HashMap<String, City> cities = new HashMap<String, City>();
  int totalWeight;
  
  /**
   * Read the text file and Builds worlds !!
   * Classic implementation.
   */
  public void buildWorld(String fileName) {
    BufferedReader br = null; 
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    String line;
    try {
      br.readLine(); // City count
      br.readLine(); // Edge count
      while ((line = br.readLine()) != null) {
        String[] lineSplitted = line.split(" ");
        City city1 = getCity(lineSplitted[0]);
        City city2 = getCity(lineSplitted[1]);
        int weight = Integer.parseInt(lineSplitted[2]);
        paths.add(new Path(weight, city1, city2));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  
  /**
   * Gets the reference of a city in a the world.
   * @param city
   */
  City getCity(String cityName) {
    City city;
    if (!cities.containsKey(cityName)) {
      city = new City(cityName);
      cities.put(city.getName(), city);
    } else {
      city = cities.get(cityName);
    }
    return city;
  } 
  
  /**
   * Find Minimum Spanning Tree.
   */
  public void getMinimalSpanningTree() {
    totalWeight = 0;
    Iterator<Path> pathIterator = paths.iterator();
    Path path;
    while (pathIterator.hasNext()) {
      path = pathIterator.next();
      if (path.getCity1().getTree() == null
          && path.getCity2().getTree() == null) {
        addTwoAloneCities(path.getCity1(), path.getCity2(), path);
      } else if (path.getCity1().getTree() == null) {
        addOneAloneCity(path.getCity1(), path, path.getCity2().getTree());
      } else if (path.getCity2().getTree() == null) {
        addOneAloneCity(path.getCity2(), path, path.getCity1().getTree());
      } else if (!path.getCity1().getTree().equals(path.getCity2().getTree())) {
        addCitiesFromDifferentTree(path);
      }
    }
  }
  
  /**
   * Adds path to Minimal Path list.
   * @param path
   */
  private void addToMinimalPathList(Path path) {
    // minimalPathList.add(path);
    totalWeight += path.getWeight();
  }
  
  /**
   * Adds an alone city to a tree.
   * @param city
   * @param path
   * @param tree
   */
  private void addOneAloneCity(City city, Path path, Tree tree) {
    tree.getCities().add(city);
    city.setTree(tree);
    addToMinimalPathList(path);
  }
  
  /**
   * Adds two alone cities to a new same tree.
   * @param city1
   * @param city2
   * @param path
   */
  private void addTwoAloneCities(City city1, City city2, Path path) {
    Tree tree = new Tree(city1, city2);
    city1.setTree(tree);
    city2.setTree(tree);
    addToMinimalPathList(path);
  }
  
  /**
   * Add 2 cities from different tree.
   * @param path
   */
  private void addCitiesFromDifferentTree(Path path) {
    Tree.UnionTreeFromPath(path);
    addToMinimalPathList(path);
  }
  

}



