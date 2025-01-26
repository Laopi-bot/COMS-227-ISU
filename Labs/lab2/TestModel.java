package lab2;

public class TestModel
{
  public static void main(String[] args)
  {
    RabbitModel model = new RabbitModel();

    // Check that the initial population is 2
    System.out.println(model.getPopulation());
    System.out.println("Expected 1");

    // A year goes by...
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 2");

 // A year goes by...
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 3");
    
 // A year goes by...
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 5");
    
    // Start over
    model.reset();
    System.out.println(model.getPopulation());
    System.out.println("Expected 1");
  }
}
