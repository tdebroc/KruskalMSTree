import java.util.Date;


public class HomeRun {
  
  
  public static void main(String[] args) {
    playBench("world.txt", 11);
    playBench("wikipedia", 39);
    playBench("data_10_40.txt", 57);
    playBench("data_100_125.in", 1260);
    playBench("data_100_1000.in", 222);
    playBench("data_200_500.in", 1451);
    playBench("data_2000_50000.in", 2587);
  }
  
  public static void playBench(String fileName, int expectedResult) {
    System.out.println("For input : " + fileName);
    Kruskal resolver = new Kruskal();
    Date totalStart = new Date();
    OptimiseBuildWorldTest.buildWorld2("inputFile/" + fileName, resolver);
    printTiming("Build World ", totalStart);
    Date MSTStart = new Date();
    resolver.getMinimalSpanningTree();
    printTiming("MS Tree     ", MSTStart);
    printTiming("Total       ", totalStart);
    System.out.print("Result is : ");
    System.out.println((expectedResult == resolver.totalWeight) ? "Well DONE ☺ !" : "Oh nooo ☻");
    System.out.println("Weight is : " + resolver.totalWeight);
    System.out.println("\n--------- ");
  }
  
  
  public static void printTiming (String Title, Date start) {
    Date end = new Date();
    System.out.println("⌚ " + Title + " took " + (end.getTime() - (start.getTime()) + " ms"));
  }
}
