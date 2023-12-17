

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to hold tester methods for the BusStopTree class
 * 
 * @author Grant Hohol
 *
 */
public class BusStopTreeTester {
  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {

    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    // arrival time of 09:00
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    // arrival time of 11:00
    Bus bus2 = new Bus(BusStop.getStop(3), route1);
    Bus bus3 = new Bus(BusStop.getStop(3), route1);

    boolean compare1 = bus1.compareTo(bus2) < 0;
    boolean compare2 = bus2.compareTo(bus1) > 0;
    boolean compare3 = bus3.compareTo(bus2) == 0;

    return compare1 && compare2 && compare3;

  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {

    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    // arrival time of 09:00
    Bus bus1 = new Bus(BusStop.getStop(2), route1);

    BusRoute route2 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    boolean compare1 = bus1.compareTo(bus2) < 0;
    boolean compare2 = bus2.compareTo(bus1) > 0;

    return compare1 && compare2;


  }

  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    // arrival time of 09:00
    Bus bus1 = new Bus(BusStop.getStop(2), route1);

    BusRoute route2 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    boolean compare1 = bus1.compareTo(bus2) > 0;
    boolean compare2 = bus2.compareTo(bus1) < 0;

    return compare1 && compare2;


  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   *
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0
    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {

    BusStopTree tree1 = new BusStopTree(1);
    return BusStopTree.isValidBST(tree1.getRoot());
  }

  /**
   * Tests that isValidBST returns false for an invalid BST.
   *
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    // spec example
    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
        "09:00", "10:00", "11:00", "12:00"};
    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    Bus bus1 = new Bus(BusStop.getStop(6), route1);
    Bus bus2 = new Bus(BusStop.getStop(10), route1);
    Bus bus3 = new Bus(BusStop.getStop(9), route1);
    Bus bus4 = new Bus(BusStop.getStop(12), route1);
    Bus bus5 = new Bus(BusStop.getStop(5), route1);
    Bus bus6 = new Bus(BusStop.getStop(2), route1);
    Bus bus7 = new Bus(BusStop.getStop(8), route1);

    // error on left side
    Node<Bus> root1 = new Node<Bus>(bus1);
    root1.setRight(new Node<Bus>(bus2));
    root1.setLeft(new Node<Bus>(bus5));
    root1.getRight().setLeft(new Node<Bus>(bus3));
    root1.getRight().setRight(new Node<Bus>(bus4));
    root1.getLeft().setRight(new Node<Bus>(bus7)); // INVALID BST
    root1.getLeft().setLeft(new Node<Bus>(bus6));

    // error on right side
    Node<Bus> root2 = new Node<Bus>(bus1);
    root2.setRight(new Node<Bus>(bus2));
    root2.setLeft(new Node<Bus>(bus5));
    root2.getRight().setLeft(new Node<Bus>(bus6)); // INVALID BST
    root1.getRight().setRight(new Node<Bus>(bus4));



    // right child is smaller
    Node<Bus> root3 = new Node<Bus>(bus7);
    root3.setLeft(new Node<Bus>(bus1));
    root3.getLeft().setLeft(new Node<Bus>(bus6));
    root3.getLeft().setRight(new Node<Bus>(bus5)); // INVALID BST

    // left child is bigger
    Node<Bus> root4 = new Node<Bus>(bus7);
    root4.setLeft(new Node<Bus>(bus1));
    root4.setRight(new Node<Bus>(bus2));
    root4.getRight().setLeft(new Node<Bus>(bus4)); // INVALID BST

    return !(BusStopTree.isValidBST(root1) || BusStopTree.isValidBST(root2)
        || BusStopTree.isValidBST(root3) || BusStopTree.isValidBST(root4));

  }

  /**
   * Tests that isValidBST returns true for a valid BST.
   *
   * Should use a tree with depth > 2.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
        "09:00", "10:00", "11:00", "12:00"};
    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute route2 = BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute route3 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute route4 = BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);


    // varying types of buses for most cases
    Bus bus1 = new Bus(BusStop.getStop(6), route3);
    Bus bus2 = new Bus(BusStop.getStop(9), route2);
    Bus bus3 = new Bus(BusStop.getStop(6), route4);
    Bus bus4 = new Bus(BusStop.getStop(9), route4);
    Bus bus5 = new Bus(BusStop.getStop(12), route1);
    Bus bus6 = new Bus(BusStop.getStop(6), route1);
    Bus bus7 = new Bus(BusStop.getStop(4), route1);

    // have different times, incoming/outgoing, route name etc
    Node<Bus> root = new Node<Bus>(bus1);
    root.setRight(new Node<Bus>(bus2));
    root.getRight().setLeft(new Node<Bus>(bus3));
    root.getRight().setRight(new Node<Bus>(bus4));
    root.getRight().getRight().setRight(new Node<Bus>(bus5));
    root.setLeft(new Node<Bus>(bus6));
    root.getLeft().setLeft(new Node<Bus>(bus7));

    return BusStopTree.isValidBST(root);

  }

  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {

    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"06:00"});

    BusStopTree tree1 = new BusStopTree(1);

    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    return tree1.addBus(bus1) && tree1.size() == 1 && BusStopTree.isValidBST(tree1.getRoot());

  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   *
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {

    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


    // create different routes with different specifications at the same stop for each bus
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"09:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"09:00"});
    BusRoute route5 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"12:00"});
    BusRoute route6 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"06:00"});
    BusRoute route7 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"04:00"});

    BusStopTree tree1 = new BusStopTree(1);

    // create the buses with their assigned routes
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    Bus bus6 = new Bus(BusStop.getStop(1), route6);
    Bus bus7 = new Bus(BusStop.getStop(1), route7);

    // check that each add returns true, the tree is still valid, and the size correctly increments
    if (tree1.addBus(bus1) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 1) {
      return false;
    }
    if (tree1.addBus(bus2) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 2) {
      return false;
    }
    if (tree1.addBus(bus3) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 3) {
      return false;
    }
    if (tree1.addBus(bus4) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 4) {
      return false;
    }
    if (tree1.addBus(bus5) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 5) {
      return false;
    }
    if (tree1.addBus(bus6) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 6) {
      return false;
    }
    if (tree1.addBus(bus7) != true || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 7) {
      return false;
    }

    return true;

  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {

    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


    // create different routes with different specifications at the same stop for each bus
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"09:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"09:00"});
    BusRoute route5 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"12:00"});
    BusRoute route6 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"06:00"});
    BusRoute route7 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"04:00"});

    BusStopTree tree1 = new BusStopTree(1);

    // create the buses with their assigned routes
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    Bus bus6 = new Bus(BusStop.getStop(1), route6);
    Bus bus7 = new Bus(BusStop.getStop(1), route7);

    tree1.addBus(bus1);
    tree1.addBus(bus2);
    tree1.addBus(bus3);
    tree1.addBus(bus4);
    tree1.addBus(bus5);
    tree1.addBus(bus6);
    tree1.addBus(bus7);
    // add duplicate bus
    if (tree1.addBus(bus3) != false || BusStopTree.isValidBST(tree1.getRoot()) != true
        || tree1.size() != 7) {
      return false;
    }

    return true;

  }

  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {

    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


    // create different routes with different specifications at the same stop for each bus
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"09:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"09:00"});
    BusRoute route5 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"12:00"});
    BusRoute route6 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, new String[] {"06:00"});
    BusRoute route7 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"04:00"});

    BusStopTree tree1 = new BusStopTree(1);

    // create the buses with their assigned routes
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    Bus bus6 = new Bus(BusStop.getStop(1), route6);
    Bus bus7 = new Bus(BusStop.getStop(1), route7); // bus will not be added

    tree1.addBus(bus1);
    tree1.addBus(bus2);
    tree1.addBus(bus3);
    tree1.addBus(bus4);
    tree1.addBus(bus5);
    tree1.addBus(bus6);

    return tree1.contains(bus5) && !(tree1.contains(bus7));

  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   *
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {

    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    BusRoute route1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"06:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"12:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, new String[] {"04:00"});

    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    BusStopTree tree1 = new BusStopTree(1);

    tree1.addBus(bus1);
    tree1.addBus(bus2);
    tree1.addBus(bus3);

    LocalTime time;
    time = LocalTime.of(6, 0);
    LocalTime time2 = LocalTime.of(5, 0);


    return tree1.getFirstNodeAfter(time, tree1.getRoot()).getValue().equals(bus1)
        && tree1.getFirstNodeAfter(time2, tree1.getRoot()).getValue().equals(bus1);

  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   *
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"04:00", "08:00"});
    BusRoute route2 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"02:00", "05:00"});
    BusRoute route3 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"05:00", "02:00"});
    BusRoute route4 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"08:00", "07:00"});
    BusRoute route5 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"09:00", "10:00"});
    BusRoute route6 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"04:00", "12:00"});

    BusStopTree tree1 = new BusStopTree(2);

    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    Bus bus6 = new Bus(BusStop.getStop(2), route6);

    tree1.addBus(bus1);
    tree1.addBus(bus2);
    tree1.addBus(bus3);
    tree1.addBus(bus4);
    tree1.addBus(bus5);
    tree1.addBus(bus6);

    LocalTime time = LocalTime.of(1, 0);
    LocalTime time2 = LocalTime.of(6, 0);

    return tree1.getFirstNodeAfter(time, tree1.getRoot()).getValue().equals(bus3)
        && tree1.getFirstNodeAfter(time2, tree1.getRoot()).getValue().equals(bus4);

  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   *
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    int[] stopIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"04:00", "08:00"});
    BusRoute route2 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"02:00", "05:00"});
    BusRoute route3 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"05:00", "02:00"});
    BusRoute route4 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"08:00", "07:00"});
    BusRoute route5 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"09:00", "10:00"});
    BusRoute route6 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"04:00", "12:00"});
    BusRoute route7 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds,
        new String[] {"10:00", "09:00"});

    BusStopTree tree1 = new BusStopTree(2);

    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    Bus bus6 = new Bus(BusStop.getStop(2), route6);
    Bus bus7 = new Bus(BusStop.getStop(2), route7);


    tree1.addBus(bus1);
    tree1.addBus(bus2);
    tree1.addBus(bus3);
    tree1.addBus(bus4);
    tree1.addBus(bus5);
    tree1.addBus(bus6);
    tree1.addBus(bus7);

    LocalTime time1 = LocalTime.of(11, 0);
    LocalTime time2 = LocalTime.of(8, 30);
    return tree1.getFirstNodeAfter(time1, tree1.getRoot()).getValue().equals(bus6)
        && tree1.getFirstNodeAfter(time2, tree1.getRoot()).getValue().equals(bus7);

  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    return false;
  }

  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {

    int[] stopIds = {1, 2, 3, 4};
    String[] stopTimes = {"06:00", "08:00", "10:00", "12:00"};

    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);

    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route1);
    Bus bus3 = new Bus(BusStop.getStop(3), route1);
    Bus bus4 = new Bus(BusStop.getStop(4), route1);

    ArrayList<Bus> buses = new ArrayList<Bus>();
    buses.add(bus1);
    buses.add(bus2);
    buses.add(bus3);
    buses.add(bus4);

    // iterator with list of buses
    Iterator<Bus> busIterator = buses.iterator();

    // destination that WILL NOT be met
    BusStop dest = BusStop.getStop(100);

    // creating filtered iterator
    BusFilteredIterator filteredIterator = new BusFilteredIterator(busIterator, dest);

    // method call should return false
    return !(filteredIterator.hasNext());

  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {


    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, new int[] {1},
        new String[] {"01:00"});
    BusRoute route2 = BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, new int[] {2},
        new String[] {"02:00"});
    BusRoute route4 = BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, new int[] {4},
        new String[] {"04:00"});


    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route2);
    Bus bus4 = new Bus(BusStop.getStop(4), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route2);
    Bus bus6 = new Bus(BusStop.getStop(2), route2);

    ArrayList<Bus> buses = new ArrayList<Bus>();
    buses.add(bus1);
    buses.add(bus2);
    buses.add(bus3);
    buses.add(bus4);
    buses.add(bus5);
    buses.add(bus6);

    // iterator with list of buses
    Iterator<Bus> busIterator = buses.iterator();

    // destination that WILL NOT be met
    BusStop dest = BusStop.getStop(2);

    // creating filtered iterator
    BusFilteredIterator filteredIterator = new BusFilteredIterator(busIterator, dest);

    int counter = 0; // will count the number of valid buses

    while (filteredIterator.hasNext()) {
     Bus next = filteredIterator.next();
     if (next != null)
        counter++;
    }

    return counter == 4;

  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    int[] stopIds = {1, 2, 3, 4};
    String[] stopTimes = {"06:00", "08:00", "10:00", "12:00"};

    BusRoute route1 = BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute route2 = BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);


    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route1);
    Bus bus4 = new Bus(BusStop.getStop(2), route1);
    Bus bus5 = new Bus(BusStop.getStop(2), route2);
    Bus bus6 = new Bus(BusStop.getStop(2), route1);

    ArrayList<Bus> buses = new ArrayList<Bus>();
    buses.add(bus1);
    buses.add(bus2);
    buses.add(bus3);
    buses.add(bus4);
    buses.add(bus5);
    buses.add(bus6);

    // iterator with list of buses
    Iterator<Bus> busIterator = buses.iterator();

    // destination that WILL NOT be met
    BusStop dest = BusStop.getStop(2);

    // creating filtered iterator
    BusFilteredIterator filteredIterator = new BusFilteredIterator(busIterator, dest);

    int counter = 0; // will count the number of valid buses

    while (filteredIterator.hasNext()) {
      counter++;
      filteredIterator.next();
    }

    return counter == 6;

  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();
    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus: " + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }
}

