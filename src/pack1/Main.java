//Ronald August
//CSC164 - BalloonProject
//24 Feb 2016
//Version 1.0

package pack1;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    //Menu Options
    //These were established in a 2D array to allow for further expansion of Administrative Options
    public static String[][] optionsArray = {{"Print Options", "Editing Operations", "Quit"},
            {"Print All Existing Balloons", "Print Quantity Of Existing Balloons", "Quit"},
            {"Create Balloon", "Destroy Balloon", "Inflate Balloon", "Deflate Balloon", "Quit"}};
    // Maximum Inventory of Balloons
    public static int MAX_SIZE = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOption = 0, numMenu = 0, numCount = 0;
        String tempString;
        boolean testLoop = false;
        //Balloon Inventory Array
        Balloon[] balloonArray = new Balloon[MAX_SIZE];
        //Prepopulate already existing inventory
        prepopulateInventory(balloonArray);
        System.out.println("Welcome to the Balloon Inventory Program!\n");
        // Do-While Loop Allows For Multiple Passes Through Program
        do {
            // Reset variables after each action is successfully performed
            if (numCount >= 1) {
                numMenu = 0;
                numOption = 0;
                numCount = 0;
            }
            // Do-While Loop Allows For Re-entry after Invalid Input (Continue with correct input or quit option)
            do {
                System.out.println("Which of the following options do you wish to perform?");
                for (int i = 0; i < optionsArray[numMenu].length; i++)
                    System.out.printf("%d. %s%n", (i + 1), optionsArray[numMenu][i]);
                System.out.print("\nPlease Select The Number Of The Desired Option:  ");
                tempString = input.nextLine();
                if (testNumInput(tempString, numMenu)) {
                    numCount++;
                    numOption = convertStrToInt(tempString);
                    //Switch Statement to test numOption
                    //If numOption is 0, then the first input was invalid, and so the default would cause an exception
                    switch (numOption) {
                        case 0:
                            testLoop = true;
                            break;
                        default:
                            if (!Objects.equals(optionsArray[numMenu][numOption - 1], "Quit") && numCount <= 1) {
                                testLoop = true;
                                numMenu = numOption;
                            } else testLoop = false;
                            break;
                    }
                } else printInvalidEntry();
            } while (testLoop);
            //Switch Statement same as above, but numCount condition in default is not necessary at this step
            switch (numOption) {
                case 0:
                    testLoop = true;
                    break;
                default:
                    if (!Objects.equals(optionsArray[numMenu][numOption - 1], "Quit")) {
                        testLoop = true;
                        menuSwitch(numMenu, numOption, balloonArray);
                    } else testLoop = false;
                    break;
            }
            // while Quit is not entered, loop through to ask for further actions
        } while (testLoop);
    }

    public static void prepopulateInventory(Balloon[] array1) {
        array1[0] = new Balloon(6, "yellow", true);
        array1[1] = new Balloon();
        array1[2] = new Balloon(8, "green", false);
    }

    public static void menuSwitch(int menu, int option, Balloon[] array1) {
        switch (menu) {
            case 1:
                switch (option) {
                    case 1: //Print List of Balloons
                        printBalloonsArray(array1);
                        break;
                    case 2: //Print Inventory Quantity
                        printQuantity();
                        break;
                    default: //Invalid Entry
                        printInvalidEntry();
                        break;
                }
                break;
            case 2:
                switch (option) {
                    case 1: //Create a Balloon
                        createBalloon(array1);
                        break;
                    case 2: //Destroy a Balloon
                        destroyBalloon(array1);
                        break;
                    case 3: //Inflate a Balloon
                        inflateBalloon(array1);
                        break;
                    case 4: //Deflate a Balloon
                        deflateBalloon(array1);
                        break;
                    default: //Invalid Entry
                        printInvalidEntry();
                        break;
                }
                break;
            default: //Invalid Entry
                printInvalidEntry();
                break;
        }
    }

    public static int[] printBalloonsArray (Balloon[] array1) {
        int numCount = 1, numCount2 = 1, numCount3 = 0;
        int[] elementArray = new int[MAX_SIZE];
        System.out.println();
        if (!isArrayFull(array1)) {
            for (Balloon each : array1) {
                if (each != null) {
                    System.out.printf("%d: %s [Index/Element %d]%n", numCount, each, numCount2);
                    numCount++;
                }
                numCount2++;
                if (each != null) {
                    elementArray[numCount3] = numCount2 - 2;
                    numCount3++;
                }
            }
        }
        else System.out.println("There Are Currently No Balloons In The Inventory");
        System.out.println();
        return elementArray;
    }

    public static void printQuantity () { System.out.printf("Quantity of Balloons: %d%n%n", Balloon.getQuantity()); }

    public static void printInvalidEntry () { System.out.println("Invalid Option Entered!\n"); }

    public static void createBalloon(Balloon[] array1) {
        Scanner input = new Scanner(System.in);
        String tempString, tempColor;
        int numCount, tempSize;
        boolean testInflate, ifInflated;
        if (!isArrayFull(array1)) {
            do {
                System.out.print("Please Enter Size Of Balloon (6, 8, 10, or 12):  ");
                tempString = input.nextLine();
            } while (!isValidSize(tempString));
            tempSize = convertStrToInt(tempString);
            do {
                System.out.print("Please Enter Color Of Balloon (red, blue, green, or yellow):  ");
                tempString = input.nextLine();
            } while (!isValidColor(tempString));
            tempColor = tempString.toLowerCase();
            do {
                System.out.print("Is The Balloon Inflated or Deflated (Y/N):  ");
                tempString = input.nextLine();
                testInflate = testCharInput(tempString);
            } while (!testInflate);
            ifInflated = tempString.toLowerCase().charAt(0) == 'y';
            numCount = 0;
            for (Balloon each : array1) {
                if (each == null) break;
                numCount++;
            }
            if (numCount <= Balloon.getQuantity()) {
                array1[numCount] = new Balloon(tempSize, tempColor, ifInflated);
            }
        }
        else System.out.println("No More Balloons May Be Created At This Time\nThe Inventory Is Full\n");
    }

    public static void destroyBalloon(Balloon[] array1) {
        Scanner input = new Scanner(System.in);
        String tempString;
        int[] elementArray;
        if (!isArrayEmpty(array1)) {
            do {
                System.out.println("Which Of The Following Balloons Do You Wish To Destroy?\n");
                elementArray = printBalloonsArray(array1);
                System.out.print("Please Enter The Number Of Your Choice " +
                        "(The Number At The Beginning of the Line):  ");
                tempString = input.nextLine();
            } while (!isValidBalloon(array1, tempString, elementArray));
            Balloon.destruct(elementArray[Integer.parseInt(tempString) - 1], array1);
        }
        else System.out.println("There Are Currently No Balloons In The Inventory\nNone Exist To Be Destroyed\n");
    }

    public static void inflateBalloon(Balloon[] array1) {
        Scanner input = new Scanner(System.in);
        String tempString;
        int[] elementArray;
        do {
            System.out.println("Which Of The Following Balloons Do You Wish To Inflate?\n");
            elementArray = printBalloonsArray(array1);
            System.out.print("Please Enter The Number Of Your Choice" +
                    "(The Number At The Beginning of the Line):  ");
            tempString = input.nextLine();
            if (isBalloonInflated(array1, tempString, elementArray) && !Objects.equals(tempString, null)) {
                System.out.println("\nThe specified balloon is already inflated\n");
            }
        } while(!(isValidBalloon(array1, tempString, elementArray) && !isBalloonInflated(array1, tempString, elementArray)));
        array1[elementArray[Integer.parseInt(tempString) - 1]].setInflated(true);
    }

    public static void deflateBalloon(Balloon[] array1) {
        Scanner input = new Scanner(System.in);
        String tempString;
        int[] elementArray;
        do {
            System.out.println("Which Of The Following Balloons Do You Wish To Deflate?\n");
            elementArray = printBalloonsArray(array1);
            System.out.print("Please Enter The Number Of Your Choice" +
                    "(The Number At The Beginning of the Line):  ");
            tempString = input.nextLine();
            if (!isBalloonInflated(array1, tempString, elementArray) && !Objects.equals(tempString, null)) {
                System.out.println("\nThe specified balloon is already deflated\n");
            }
        } while(!(isValidBalloon(array1, tempString, elementArray) && isBalloonInflated(array1, tempString, elementArray)));
        array1[elementArray[Integer.parseInt(tempString) - 1]].setInflated(false);
    }

    public static boolean isArrayFull(Balloon[] array1) {
        for (Balloon each : array1) if (each == null) return false;
        return true;
    }

    public static boolean isArrayEmpty (Balloon[] array1) {
        for (Balloon each : array1) if (each != null) return false;
        return true;
    }

    public static boolean isBalloonInflated (Balloon[] array1, String testStr, int[] elementArray) {
        return array1[elementArray[Integer.parseInt(testStr) - 1]].isInflated();
    }

    public static int convertStrToInt(String convertStr) { return Integer.parseInt(convertStr); }

    public static boolean testNumInput (String testStr, int menu) {
        return testNumInput(testStr) && !(Integer.parseInt(testStr) < 1 || Integer.parseInt(testStr) > optionsArray[menu].length);
    }

    public static boolean testNumInput (String testStr) {
        int i = 0;
        if (testStr == null || testStr.length() == 0 || (testStr.charAt(0) == '-' && testStr.length() == 1)) return false;
        if (testStr.charAt(0) == '-') i = 1;
        for (; i < testStr.length(); i++) if (testStr.charAt(i) < '0' || testStr.charAt(i) > '9') return false;
        return true;
    }

    public static boolean testCharInput (String testStr) {
        char testChar = testStr.toLowerCase().charAt(0);
        return testChar == 'Y' || testChar == 'y' || testChar == 'N' || testChar == 'n';
    }

    public static boolean isValidBalloon (Balloon[] array1, String testStr, int[] elementArray) {
        return testNumInput(testStr) && !(Integer.parseInt(testStr) < 1 &&
                Integer.parseInt(testStr) > Balloon.getQuantity()) &&
                array1[elementArray[Integer.parseInt(testStr) - 1]] != null;
    }

    public static boolean isValidSize(String testStr) {
        int[] sizeArray = {6, 8, 10, 12};
        for (int each : sizeArray) if (Objects.equals(testStr, Integer.toString(each))) return true;
        System.out.println("Invalid Size Entered!\nIt Must Be 6, 8, 10, or 12.\n");
        return false;
    }

    public static boolean isValidColor(String testStr) {
        String[] colorArray = {"red", "blue", "green", "yellow"};
        for (String each : colorArray) if (Objects.equals(testStr.toLowerCase(), each.toLowerCase())) return true;
        System.out.println("Invalid Color Entered!\nIt Must Be red, blue, green, or yellow.\n");
        return false;
    }
}

class Balloon {
    // properties of balloons
    private int size;
    private String color;
    private boolean inflated;
    private static int quantity = 0;

    //Default Constructor
    Balloon() {
        size = 10;
        color = "blue";
        inflated = false;
        quantity++;
    }

    //Custom Constructor
    Balloon(int newSize, String newColor, boolean ifInflated) {
        size = newSize;
        color = newColor;
        inflated = ifInflated;
        quantity++;
    }

    //Return color of the object
    String getColor() { return color; }

    //Return size of the object
    int getSize() { return size; }

    //Return quantity of balloons made
    static int getQuantity() { return quantity; }

    //Destruct a balloon
    static void destruct(int element, Balloon[] array1) {
        array1[element] = null;
        quantity--;
    }

    // Went to Code/Generate/toString and selected size and color
    @Override
    public String toString() {
        return "Balloon {" +
                "size = " + size +
                ", color = " + color +
                ", " + (inflated ? "inflated" : "not inflated") +
                '}';
    }

    void setInflated(boolean arg) { inflated = arg; }

    public boolean isInflated() { return inflated; }
}