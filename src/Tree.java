import java.util.ArrayList;


public class Tree {

  private ArrayList<City> cities = new ArrayList<City>();

  public Tree(City city1, City city2) {
    cities.add(city1);
    cities.add(city2);
  }

  public ArrayList<City> getCities() {
    return cities;
  }

  public void setCities(ArrayList<City> cities) {
    this.cities = cities;
  }
  
  public static void UnionTreeFromPath(Path path) {
    Tree tree1 = path.getCity1().getTree(),
        tree2 = path.getCity2().getTree();
   boolean isTree1Bigger = tree1.getCities().size() >
       tree2.getCities().size();
   unionTreeInFirstTree(isTree1Bigger ? tree1 : tree2,
       isTree1Bigger ? tree2 : tree1);
  }
  
  public static void unionTreeInFirstTree(Tree tree1, Tree tree2) {
    //unionCount++;
    tree1.getCities().addAll(tree2.getCities());
    for (int i = 0; i < tree2.getCities().size(); i++) {
      // mergeCount++;
      tree2.getCities().get(i).setTree(tree1);
    }
  }
}
