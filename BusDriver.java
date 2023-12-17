import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A driver for the buses (har har har)...
 */
public class BusDriver {
  public static void main(String[] args) {
    // Load the raw bus data.
    System.out.print("Loading bus data... ");
    ArrayList<BusRoute> rawData;
    try {
      rawData = BusDataLoader.loadAllRoutes();
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't read: " + e);
      System.exit(1);
      return; // satisfy the compiler
    }
    System.out.println("loaded!");

    // Scanner to get keyboard input.
    Scanner sc = new Scanner(System.in);

    // Get the requested departure stop id.
    System.out.print("Enter departure stop ID: ");
    int stopId = sc.nextInt();

    sc.close();

    // Construct the BST.
    BusStopTree bst = new BusStopTree(stopId);

    // Iterate over all routes and only add Bus-es that run today and stop on the given stop at some
    // point during the route.
    BusStop userStop = BusStop.getStop(stopId);
    LocalDate today = LocalDate.now();
    for (BusRoute route : rawData) {
      if (route.runsOnDate(today) && route.getArrivalTimeAtStop(userStop) != null) {
        bst.addBus(new Bus(userStop, route));
      }
    }

    // Print the next few stops...
    System.out.println("Upcoming buses at this stop...");
    LocalTime current = LocalTime.now();
    int i = 10;
    for (Iterator<Bus> it = bst.getNextBuses(current); it.hasNext() && i > 0; --i) {
      Bus bus = it.next();
      System.out.println(bus.getArrivalTime() + "\t" + bus.getName());
    }
  }
}
