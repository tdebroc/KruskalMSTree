


public class Path implements Comparable<Path>{
  
  private int weight;
  private City city1; 
  private City city2;
  
  public Path(int weight, City city1, City city2) {
    super();
    this.weight = weight;
    this.city1 = city1;
    this.city2 = city2;
  }
  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }
  public City getCity1() {
    return city1;
  }
  public void setCity1(City city1) {
    this.city1 = city1;
  }
  public City getCity2() {
    return city2;
  }
  public void setCity2(City city2) {
    this.city2 = city2;
  }
  
  public String toString() {
    return "From " + city1.getName() + " To " + city2.getName() + " Cost " + 
        weight;
  }
  

  @Override
  public int compareTo(Path p) {
    return weight - p.getWeight();
  }
}
