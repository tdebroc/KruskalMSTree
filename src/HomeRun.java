import java.util.Date;


public class HomeRun {
  static int theTotalTimeForMethod = 0;  
  
  public static void main(String[] args) {
    playBench("world.txt", 11);
    playBench("wikipedia", 39);
    playBench("data_10_40.txt", 57);
    playBench("data_100_125.in", 1260);
    playBench("data_100_1000.in", 222);
    playBench("data_200_500.in", 1451);
    playBench("data_2000_50000.in", 2587);
    // testBuildWorldPerf();
  }
  
  public static void playBench(String fileName, int expectedResult) {
    System.out.println("For input : " + fileName);
    KruskalOptimised resolver = new KruskalOptimised();
    Date totalStart = new Date();
    resolver.buildWorld("inputFile/" + fileName);
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
  
  public static void testBuildWorldPerf() {
    KruskalOptimised resolver;
    int total = 1;
    Date start = new Date();
    for (int i = 0; i < total; i++) {
      resolver = new KruskalOptimised();
      OptimisedBuildWorld.buildWorld("inputFile/data_2000_50000.in", resolver);
      System.out.print(i + ", ");
    }
    System.out.println("");
    Date end = new Date();
    int totalTime = (int) (end.getTime() - start.getTime());
    System.out.println("⌚  " + total + " BuildWorld took " + totalTime + " ms ");
    System.out.println("⌚   Average BuildWorld took " + (totalTime / total )+ " ms");
    System.out.println("⌚   theTotalTime " + theTotalTimeForMethod);
  }
}
