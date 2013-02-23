import java.util.ArrayList;


public class SortedPathList extends ArrayList<Path>{
  
  private static final long serialVersionUID = 1L;

  public boolean add(Path path) {
    
      if (this.size() == 0) {
        this.add(0, path);
        return true;
      }
      int index = BinarySearch(path);
      this.add(index, path);
      return true;
  }
  
  public int BinarySearch(Path path) {
    return BinarySearch(path, 0, this.size());
  }
  
  public int BinarySearch(Path path, int begin, int end) {
    int index = (end + begin) / 2;
    if (index >= this.size() || index < 0) {
      return index;
    }
    if (end <= begin) {
      return this.get(index).getWeight() < path.getWeight() ?
          index + 1: index;
    } else {
      if (this.get(index) == path) {
        return index;
      } else if (this.get(index).getWeight() >= path.getWeight()) {
        return BinarySearch(path, begin, index - 1);
      } else {
        return BinarySearch(path, index + 1, end);
      }      
    }    
  }

}
