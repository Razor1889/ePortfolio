import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Mirza Baig
 * @version 11/09/2021
 * 
 */


public class Portfolio 
{
    /**
     * @param args contains command line arguments passed to main
     * 
     * The main runs a menu system and stores the user option input
     * when the user enters "quit", the menu ends.
     * 
     * ArrayList of investments is used to store multiple investment objects
     * A HashMap of Strings and an Integer Arraylist is used to store keywords
     * and the element numbers of ArrayList investments that contain those
     * keywords.
     * 
     * The menu option is sent into multiple if statements to access the different
     * options presented
     * 
     * Buy: User enters input and creates a type of investment
     * The investment is added to the ArrayList and the name is broken down into
     * keywords and added to the hashmap (if investment exists previously,
     * quantity is increased and bookValue is recalculated)
     * 
     * Sell: User enters amount to sell and the price. Depending on the userinput of
     * quantity, an error message is given for userQuantity is greater than investmentQuantity
     * If appropirate quantity, the sell is performed and bookValue, quantity is updated.
     * if the sell is zero'd (i.e. no quantity left) the investment is removed and the
     * hashmap is recreated to account for it.
     * 
     * Update: Loops through investment ArrayList and updates the prices based on user
     * input.
     * 
     * Gain: Calculates gain from each investment based on the arithmetic provided by
     * the professor.
     * 
     * Save: Save/Write to a file whose name is provided in the commandline args[0].
     * 
     * Load: Load/Read from a file whose name is provided in the commandline args[0]
     * 
     * Search: Get user input for search values. Loop through investment list for 
     * symbol, name (broken into keywords using HashMap and Integer ArrayList to match)
     *  and price range (user can decide to input nothing for any of the terms).
     * 
     * Print: Not specified in the menu, a simple command to print all the investments in
     * the ArrayList.
     * 
     * Main does not return anything
     */
    public static void main (String[] args)
    {
    Scanner input = new Scanner(System.in);
    boolean run = true;
    ArrayList<Investment> investments = new ArrayList<Investment>();
    HashMap<String,ArrayList<Integer>> hashMap = new HashMap<String,ArrayList<Integer>>();
    String mapDelimiters = "[,\\s\\-:\\?\\.]";
    int tempQuantity = 0;
    double tempPrice = 0.00;
    boolean exists = false;
    double gain = 0.0;
    boolean conditionMet = false;
    while(run == true)
    {
        gain = 0.0;
        conditionMet = false;
        //Command Loop Menu
        System.out.println(
        "\nWelcome to your Portfolio!\n"
                        + "Here are the commands:\n"
                        + "Buy: Own a new investment or add more quantity to an existing investment. \n"
                        + "Sell: Reduce some quantity of an investment \n"
                        + "Update: Refresh prices of all existing investments\n"
                        + "Gain: Compute Total Gain by accumulating gains of all investments\n"
                        + "Search: Find a set of stocks based on keywords\n"
                        + "Save: Save Investments to a file passed in command line\n"
                        + "Load: Load Investments from a file passed in command line\n"
                        + "Quit: Exit the program\n"
                        );
    //run a loop based on user input
    String option = input.nextLine();

    if (option.equalsIgnoreCase("buy") || option.equalsIgnoreCase("b"))
    {
        conditionMet = true;
        exists = false;
        System.out.println("Which type of investment would you like to buy? Stock or MutualFund? (Followed by a Symbol");
        System.out.println("Format Example: MutualFund XXXX");
        String split[] = input.nextLine().split(" ");
        if (split[0].equalsIgnoreCase("Stock") || split[0].equalsIgnoreCase("s"))
        {
            for (int element = 0; element < investments.size(); element++) 
            {
                if (investments.get(element) instanceof Stock)
                {
                    String temp = investments.get(element).getSymbol();
                    if (temp.equals(split[1]))
                    {
                        exists = true;
                        System.out.println("Symbol Exists in Stock Previously");
                        System.out.println("Purchasing Additional Stock");
                        System.out.println("Enter Purchase Quantity: ");
                        tempQuantity = input.nextInt();
                        System.out.println("Enter Purchase Price: ");
                        tempPrice = input.nextDouble();
                        investments.get(element).buy(tempQuantity, tempPrice);
                        System.out.println("Investment Purchased. Printing Stock Attributes");
                        System.out.println(investments.get(element).toString());
                    }//if stock exists
                }//get instance of stock
            }//loop for stock exists and purchase

            if (exists == false)
            {
            //if stock does not exist make a stock
            System.out.println("Creating new Stock");
            Stock newStock = new Stock();
            newStock.setSymbol(split[1]);
            System.out.println("Enter Name");
            String tempName = input.nextLine();
            newStock.setName(tempName);
            System.out.println("Enter Quantity");
            newStock.setQuantity(input.nextInt());
            System.out.println("Enter Price");
            newStock.setPrice(input.nextDouble());
            newStock.purchaseBookValue();
            System.out.println(newStock.toString());
            //add to lsit
            investments.add(newStock);
            //add to hash
            tempName = tempName.toLowerCase();
            String[] splitbuy = tempName.split(mapDelimiters);

            for (String thisSplit: splitbuy)
            {
                if (hashMap.containsKey(thisSplit))
                {
                    ArrayList<Integer> arr = hashMap.get(thisSplit);
                    arr.add(investments.size()-1);
                    hashMap.put(thisSplit,arr);
                }//if hash contain
                else
                {

                }//if hash does not contain
            }//loop through keywords
            }//if does not exist previously (make new stock)
        }//if stock

        if (split[0].equalsIgnoreCase("MutualFund") || split[0].equalsIgnoreCase("mf"))
        {
            for (int element = 0; element < investments.size(); element++) 
            {
                if (investments.get(element) instanceof MutualFund)
                {
                    String temp = investments.get(element).getSymbol();
                    if (temp.equals(split[1]))
                    {
                        exists = true;
                        System.out.println("Symbol Exists in Mutual Fund Previously");
                        System.out.println("Purchasing Additional Mutual Funds");
                        System.out.println("Enter Purchase Quantity: ");
                        tempQuantity = input.nextInt();
                        System.out.println("Enter Purchase Price: ");
                        tempPrice = input.nextDouble();
                        investments.get(element).buy(tempQuantity, tempPrice);
                        System.out.println("Investment Purchased. Printing Mutual Fund Attributes");
                        System.out.println(investments.get(element).toString());
                    }//if mutual fund exists
                }//get instance of mutual fund
            }//loop for mutual fund exists and purchase

            if (exists == false)
            {
            //if stock does not exist make a stock
            System.out.println("Creating new MutualFund");
            MutualFund newFund = new MutualFund();
            newFund.setSymbol(split[1]);
            System.out.println("Enter Name");
            String tempName = input.nextLine();
            newFund.setName(tempName);
            System.out.println("Enter Quantity");
            newFund.setQuantity(input.nextInt());
            System.out.println("Enter Price");
            newFund.setPrice(input.nextDouble());
            newFund.purchaseBookValue();
            System.out.println(newFund.toString());
            //add to lsit
            investments.add(newFund);
            //add to hash
            tempName = tempName.toLowerCase();
            String[] splitbuy = tempName.split(mapDelimiters);

            for (String thisSplit: splitbuy)
            {
                if (hashMap.containsKey(thisSplit))
                {
                    ArrayList<Integer> arr = hashMap.get(thisSplit);
                    arr.add(investments.size()-1);
                    hashMap.put(thisSplit,arr);
                }//if hash contain
                else
                {

                }//if hash does not contain
            }//loop through keywords
            }//if does not exist previously (make new fund)
        }//if mutualfund
    }//if buy

    else if (option.equalsIgnoreCase("sell") || option.equalsIgnoreCase("s"))
    {
        conditionMet = true;
        exists = false;
        System.out.println("Which investment would you like to sell? Enter the Symbol");
        System.out.println("Format Example: XXXX");
        String userSymbol = input.nextLine();
        for (int element = 0; element < investments.size(); element++) 
        {
                String temp = investments.get(element).getSymbol();
                if (temp.equals(userSymbol))
                {
                    exists = true;
                    if (investments.get(element) instanceof Stock)
                    {
                        System.out.println("Stock Exists matching Symbol; Selling");
                        System.out.println("Enter Sell Quantity: ");
                        tempQuantity = input.nextInt();
                        if (tempQuantity > investments.get(element).getQuantity())
                        {
                            System.out.println("Cannot sell, Not enough quantity");
                        }//check for quantity
                        else
                        {
                            System.out.println("Enter Sell Price: ");
                            tempPrice = input.nextDouble();
                            investments.get(element).sell(tempQuantity, tempPrice);
                            System.out.println("Investment Sold. Printing Stock Attributes");
                            System.out.println(investments.get(element).toString());
                            if (investments.get(element).zero(tempQuantity) == 1)
                            {
                                //String investmentName = investments.get(element).getName().toLowerCase();
                                //String[] sellSplit = investmentName.split(mapDelimiters);

                                System.out.println("Removing Stock from List");
                                investments.remove(element);
                                //clear map
                                hashMap.clear();
                                //regenerate map
                                int sellElement = 0;
                                for(Investment curr: investments)
                                {
                                    String tempName = curr.getName().toLowerCase();
                                    String[] nameWords = tempName.split(mapDelimiters);
                
                                    for(String thisName: nameWords)
                                    {
                                        if(hashMap.containsKey(thisName))
                                        {
                                            ArrayList<Integer> arr = hashMap.get(thisName);
                                            arr.add(sellElement);
                                            hashMap.put(thisName, arr);
                                        }
                                        else
                                        {
                                            ArrayList<Integer> arr = new ArrayList<Integer>();
                                            arr.add(sellElement);
                                            hashMap.put(thisName, arr);
                                        }
                                    }
                                    sellElement++;
                                }//recreate map
                            }//remove if zero
                        }//if have quantity

                    }//if stock

                    else if (investments.get(element) instanceof MutualFund)
                    {
                        System.out.println("Mutual Fund Exists matching Symbol; Selling");
                        System.out.println("Enter Sell Quantity: ");
                        tempQuantity = input.nextInt();
                        if (tempQuantity > investments.get(element).getQuantity())
                        {
                            System.out.println("Cannot sell, Not enough quantity");
                        }//check for quantity
                        else
                        {
                            System.out.println("Enter Sell Price: ");
                            tempPrice = input.nextDouble();
                            investments.get(element).sell(tempQuantity, tempPrice);
                            System.out.println("Investment Sold. Printing Mutual Fund Attributes");
                            System.out.println(investments.get(element).toString());
                            if (investments.get(element).zero(tempQuantity) == 1)
                            {
                                System.out.println("Removing Mutual Fund from List");
                                investments.remove(element);
                                //clear map
                                hashMap.clear();
                                //regenerate map
                                int sellElement = 0;
                                for(Investment curr: investments)
                                {
                                    String tempName = curr.getName().toLowerCase();
                                    String[] nameWords = tempName.split(mapDelimiters);
                
                                    for(String thisName: nameWords)
                                    {
                                        if(hashMap.containsKey(thisName))
                                        {
                                            ArrayList<Integer> arr = hashMap.get(thisName);
                                            arr.add(sellElement);
                                            hashMap.put(thisName, arr);
                                        }
                                        else
                                        {
                                            ArrayList<Integer> arr = new ArrayList<Integer>();
                                            arr.add(sellElement);
                                            hashMap.put(thisName, arr);
                                        }
                                    }
                                    sellElement++;
                                }//recreate map
                            }//remove if zero
                        }//if have quantity
                    }//if Mutual Fund

                }//if investment exists

        }//loop for investments sell
        if (exists == false)
        {
            System.out.println("Investment does not exist");
        }//if investment not exist
    }//if sell

    else if (option.equalsIgnoreCase("update") || option.equalsIgnoreCase("u"))
    {
        conditionMet = true;
        System.out.println("Running update for investments");
        for (int element = 0; element < investments.size(); element++) 
        {
            System.out.printf("Investment %d: \n" , element+1);
            System.out.println(investments.get(element).toString());
            System.out.println("What is the new price?");
            tempPrice = input.nextDouble();
            investments.get(element).setPrice(tempPrice);
        }//loop for investment update
    }//if update

    else if (option.equalsIgnoreCase("gain") || option.equalsIgnoreCase("g"))
    {
        conditionMet = true;
        System.out.println("Getting gain from Investments");
        for (int element = 0; element < investments.size(); element++) 
        {
            System.out.println("Investment: ");
            System.out.printf("%d \n" , element+1);
            System.out.println("The gain for this Investment is: ");
            System.out.printf("$%.2f\n", investments.get(element).getGain());
            gain += investments.get(element).getGain();
        }//loop for investment gain

        System.out.printf("Total Portfolio Gain: $%.2f\n", gain);
    }//if gain

    else  if (option.equalsIgnoreCase("Save") || option.equalsIgnoreCase("Write"))
    {
        conditionMet = true;
        String filename = args[0];
                File f = new File(filename);
                if (f.length() != 0)
                { 
                  System.out.println("Warning! File will be overwritten!");
                }
                try 
                {
                  PrintWriter fileWriter = new PrintWriter(f);
                  for (int element = 0; element < investments.size(); element++)
                  {
                    fileWriter.printf(investments.get(element).printToFile());
                  }//write to file
                  fileWriter.close();
                } catch (Exception e) 
                {
                  System.out.println("Failed to Write");
                }//catch exception
    }//if write

    else  if (option.equalsIgnoreCase("Load") || option.equalsIgnoreCase("Read"))
    {
        conditionMet = true;
        int counter = 0;
        String tempType = "";
        String tempSym = "";
        String tempName = "";
        String tempQuantityFile = "";
        String tempPriceFile = "";
        String tempBookValue = "";
        String filename2 = args[0];
                try 
                {
                  File file = new File(filename2);
                  Scanner scanner = new Scanner(file);
                  //file testing
                  if (file.length() == 0)
                  { 
                    System.out.println("Warning! File is empty!");
                  }
                    while (scanner.hasNextLine())
                    {
                      String temp = scanner.nextLine();
                      String split[] = temp.split(" = ");
                      String fileData = split[1];

                      fileData = fileData.substring(1,fileData.length()-1);

                      if (counter == 0)
                      {
                            tempType = fileData;
                      }//type

                      else if (counter == 1)
                      {
                            tempSym = fileData;
                      }//symbol

                      else if (counter == 2)
                      {
                            tempName = fileData;
                      }//name

                      else if (counter == 3)
                      {
                          tempQuantityFile = fileData;
                      }//quantity

                      else if (counter == 4)
                      {
                          tempPriceFile = fileData;
                      }//price

                      else if (counter == 5)
                      {
                          tempBookValue = fileData;
                      }//bookValue

                      counter++;
                      if (counter > 5)
                      {
                          counter = 0;
                          if (tempType.equalsIgnoreCase("Stock"))
                          {
                            Stock fileStock = new Stock();
                            fileStock.setSymbol((tempSym));
                            fileStock.setName(tempName);
                            fileStock.setQuantity(Integer.valueOf(tempQuantityFile));
                            fileStock.setPrice(Double.valueOf(tempPriceFile));
                            fileStock.setBookValue(Double.valueOf(tempBookValue));
                            investments.add(fileStock);
                              //add to hashmap keywords of name
                            tempName = tempName.toLowerCase();
                            String[] nameBreak = tempName.split(mapDelimiters);
    
                            //need to loop through string
                            for (String thisName: nameBreak)
                            {
                                if (hashMap.containsKey(thisName))
                                {
                                    //if key exists
                                    ArrayList<Integer> arr = hashMap.get(thisName);
                                    arr.add(investments.size()-1);
                                    hashMap.put(thisName, arr);
                                }
                                else
                                {
                                    //if not exist make new key
                                    ArrayList<Integer> arr = new ArrayList<Integer>();
                                    arr.add(investments.size()-1);
                                    hashMap.put(thisName, arr);
                                }
                                
                            }
                          }//if type stock
    
                          else if (tempType.equalsIgnoreCase("MutualFund"))
                          {
                            MutualFund fileMF = new MutualFund();
                            fileMF.setSymbol((tempSym));
                            fileMF.setName(tempName);
                            fileMF.setQuantity(Integer.valueOf(tempQuantityFile));
                            fileMF.setPrice(Double.valueOf(tempPriceFile));
                            fileMF.setBookValue(Double.valueOf(tempBookValue));
                            investments.add(fileMF);
                            //add to hashmap keywords of name
                          tempName = tempName.toLowerCase();
                          String[] nameBreak = tempName.split(mapDelimiters);
    
                          //need to loop through string
                          for (String thisName: nameBreak)
                          {
                              if (hashMap.containsKey(thisName))
                              {
                                  //if key exists
                                  ArrayList<Integer> intarr = hashMap.get(thisName);
                                  intarr.add(investments.size()-1);
                                  hashMap.put(thisName, intarr);
                              }
                              else
                              {
                                  //if not exist make new key
                                  ArrayList<Integer> intarr = new ArrayList<Integer>();
                                  intarr.add(investments.size()-1);
                                  hashMap.put(thisName, intarr);
                              }
                          }
                          }//if type mutualfund
                      }

                    }//while scanner can read next line
                    scanner.close();
                } catch (Exception e) 
                {
                  System.out.println("Could not open file.");
                }


    }//if load
    else if (option.equalsIgnoreCase("Search"))
    {
        conditionMet = true;
        System.out.println("What is the symbol of the search investment?");
        String sym = input.nextLine();

        System.out.println("What are the keywords or name of the search investment?");
        String name = input.nextLine();

        System.out.println("What is the price range of the search investment? Format: XX XX");
        String prices = input.nextLine();
        String[] range = prices.split(mapDelimiters);

        ArrayList<Investment> match = new ArrayList<Investment>();
        if (!sym.equalsIgnoreCase(""))
        {
            for (Investment curr: investments)
            {
                if (curr.getSymbol().equalsIgnoreCase(sym) && !match.contains(curr))
                {
                    match.add(curr);
                }//if match and not already in list, add
            }//loop through investments with current
        }//symbol match

        if (!prices.equalsIgnoreCase(""))
        {
            double low = Double.valueOf(range[0]);
            double high = Double.valueOf(range[1]);
            for (Investment curr: investments)
            {
                if (curr.getPrice() >= low && curr.getPrice() <= high && !match.contains(curr))
                {
                    match.add(curr);
                }//if within range and not in list, add
            }//loop through investments
        }//price match

        if (!name.equalsIgnoreCase(""))
        {
            name = name.toLowerCase();
            String searchKey[] = name.split(mapDelimiters);
            ArrayList<Integer> foundElements = new ArrayList<Integer>();

            for (String thisKey: searchKey)
            {
                if (hashMap.containsKey(thisKey))
                {
                    ArrayList<Integer> arr = hashMap.get(thisKey);
                    foundElements.addAll(arr);
                }//if map contains key, add all to arraylist of type int
            }//loop through keywords

            for (Integer currElement: foundElements)
            {
                Investment temp = investments.get(currElement);
                //get investment at element curr
                if (!match.contains(temp))
                {
                    match.add(temp);
                }//if not in list, add
            }//loop through element number array
        }//keyword search

        //found all, print resulting list
        if (match.isEmpty())
        {
            System.out.println("No matching investments found!");
        }//if none found
        else
        {
            for (Investment curr: match)
            {
                System.out.println(curr.toString());
            }//print found search
        }//if found

    }//if search

    else if (option.equalsIgnoreCase("Print") || option.equalsIgnoreCase("p"))
    {
        conditionMet = true;

        for (int element = 0; element < investments.size(); element++)
        {
            String print = investments.get(element).toString();
            System.out.println(print);
        }
    }//to print list for testing

    else if (option.equalsIgnoreCase("Quit") || option.equalsIgnoreCase("Q"))
    {
        conditionMet = true;
        System.out.println("Thank you for using this program");
        run = false;
    }//if quit
    else if (conditionMet == false)
    {
        System.out.println("Invalid Command, Please try again");
    }//if no conditions met


    }//command loop

    input.close();
    }//main
}//Portfolio
