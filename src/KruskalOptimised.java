import java.util.HashMap;
import java.util.Iterator;




public class KruskalOptimised {
  AllPath paths;
  HashMap<String, City> cities = new HashMap<String, City>();
  int totalWeight;
  int cityCount;
  int edgeCount;
  
  /**
   * Read the text file and Builds worlds !!
   * Classic implementation.
   */
  public void buildWorld(String fileName) {
    OptimisedBuildWorld.buildWorld(fileName, this);
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
    int count = 0;
    boolean hasNext = true;
    // We don't use hasNext() for faster result, next() do the job for both.
    while (hasNext) {
      path = pathIterator.next();
      if (path != null ) {
        count+=path.getWeight();
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
        
        if (path.getCity1().getTree().getCities().size() == cityCount ) {
           return;
        }
      } else {
        hasNext = false;
      }
    }
    System.out.println(count);
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



