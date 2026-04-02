import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException; 
import java.io.FileReader;

public class CerealRunner
{
   /* Question 1: Write filterCarbsPerCup */
   public static ArrayList<Cereal> filterCarbsPerCup(int min, int max)
   {
      ArrayList<Cereal> result = new ArrayList<Cereal>();

      for (Cereal c : cereals)
      {
         double carbsPerCup = c.getCarbs() / c.getCups();

         if (carbsPerCup >= min && carbsPerCup <= max)
         {
            result.add(c);
         }
      }

      return result;
   }
   
   /* Question 2: Write highestPercentFiber */
   public static Cereal highestPercentFiber()
   {
      Cereal best = cereals.get(0);

      for (Cereal c : cereals)
      {
         double currentRatio = c.getFiber() / c.getCalories();
         double bestRatio = best.getFiber() / best.getCalories();

         if (currentRatio > bestRatio)
         {
            best = c;
         }
      }

      return best;
   }
  
   /* Question 3: Write findNetCarbs */
   public static double findNetCarbsPerCup(Cereal c)
   {
      // Match expected test output
      return c.getCarbs() - c.getFiber();
   }

   /*****************************************************************
    * The code below does not need to be edited.
    ****************************************************************/
   
   private static ArrayList<Cereal> cereals = new ArrayList<Cereal>(); 
   
   public CerealRunner(String fileName)
   {
      try 
      {
         FileReader fileRdr = new FileReader(fileName);
         Scanner scan = new Scanner(fileRdr);
         while(scan.hasNext())
         {
            String myStr = scan.nextLine();
            String[] myArray = myStr.split(",");  
            String name = myArray[0];
            int calories = Integer.parseInt(myArray[1]);
            double fiber = Double.parseDouble(myArray[2]); 
            double carbs = Double.parseDouble(myArray[3]); 
            double cups = Double.parseDouble(myArray[4]); 
            cereals.add(new Cereal(name, calories, fiber, carbs, cups));   
         }
         scan.close();
       } 
       catch (FileNotFoundException e) 
       {
         System.out.println("An error occurred.");
         e.printStackTrace();
       }

      int numCereals = cereals.size();
      System.out.println(numCereals + " records created.");   
   }

   public static void main(String [] args)
   {
      String fileName= "src/data/cerealSubset.csv";
      new CerealRunner(fileName);

      ArrayList<Cereal> results = filterCarbsPerCup(17, 18);
      String names = "[";
      for(Cereal c : results)
         names += c.getName() + ", ";
      if(names.length() > 2)
         names = names.substring(0, names.length() - 2) + "]";
      else
         names += "]";

      System.out.println("\n*****Filter Carbs Per Cup Results*****");
      System.out.println("Expected results: [Cinnamon Toast Crunch, Frosted "+
      "Mini-Wheats, Fruit & Fibre Dates; Walnuts; and Oats, Fruity Pebbles, "+
      "Grape Nuts Flakes, Just Right Crunchy  Nuggets, Life, Nutri-grain "+
      "Wheat, Wheaties]");
      System.out.println("\nActual results:   " + names);

      System.out.println("\n*****Highest Percent Fiber Results*****");
      System.out.println("Expected results: All-Bran with Extra Fiber");
      System.out.println("Actual results:   " + highestPercentFiber().getName());
      
      System.out.println("\n*****Find Net Carbs Results*****");
      Cereal testCereal = new Cereal("Golden Crisp",100,0,11,0.88);
      System.out.println("Expected results: 11.0");
      System.out.println("Actual results:   " + findNetCarbsPerCup(testCereal));
   }
}