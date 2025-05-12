import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

/**
 * @author Mirza Baig
 * @version 30/09/2021
 * 
 */

public class ePortfolio 
{
    /**
     * GUI Implementation for ePortfolio
     */

    static JFrame frame = new JFrame("ePortfolio");
    static JPanel panel = new JPanel();
    static ArrayList <Investment> investments = new ArrayList<Investment>();
    static HashMap<String,ArrayList<Integer>> hashMap = new HashMap<String,ArrayList<Integer>>();
    static ArrayList <Integer> intArray = new ArrayList<Integer>();
    static String mapDelimiters = "[,\\s\\-:\\?\\.]";
    static JPanel buyPanel = new JPanel();
    static JPanel sellPanel = new JPanel();
    static JPanel updatePanel = new JPanel();
    static JPanel gainPanel = new JPanel();
    static JPanel searchPanel = new JPanel();

    /**
     * This function checks if the input is numeric
     * @param isNum the string to check 
     * @return true if input is numeric, false otherwise.
     */
    public static boolean isNumeric(String isNum) 
    {
        if (isNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(isNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }//isNumeric

    static JMenuItem quit = new JMenuItem( new AbstractAction("Quit")
    {
        //exit when clicked on quit
        @Override
        public void actionPerformed( ActionEvent e )
        {
            System.exit(0);
        }
    });//Exit Option

    private static JMenuItem buy = new JMenuItem( new AbstractAction("Buy")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            sellPanel.setVisible(false);
            updatePanel.setVisible(false);
            gainPanel.setVisible(false);
            searchPanel.setVisible(false);
            panel.setVisible(false);
            buyPanel.setLayout(null);
            buyPanel.setSize(750,500);
            //set other panels to not visible and add to buyPanel

            //setting up bounds for components on buyPanel
            JLabel intro = new JLabel("Buying an Investment");
            intro.setBounds(10, 0, 400, 25);
            buyPanel.add(intro);
 
            JLabel typeLabel = new JLabel("Type");
            typeLabel.setBounds(10,30,80,25);
            buyPanel.add(typeLabel);
            String[] choices = {"Stock","Mutual Fund"};
            JComboBox<String> choice = new JComboBox<String>(choices);
            choice.setSelectedIndex(0);
            choice.setBounds(100, 30, 100, 25);
            buyPanel.add(choice);

            JLabel symLabel = new JLabel("Symbol");
            symLabel.setBounds(10,60,80,25);
            buyPanel.add(symLabel);
            JTextField symText = new JTextField();
            symText.setBounds(100,60,125,25);
            buyPanel.add(symText);
            //symbol

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setBounds(10,90,80,25);
            buyPanel.add(nameLabel);
            JTextField nameeText = new JTextField();
            nameeText.setBounds(100,90,200,25);
            buyPanel.add(nameeText);
            //name
            
            JLabel qLabel = new JLabel("Quantity");
            qLabel.setBounds(10,120,80,25);
            buyPanel.add(qLabel);
            JTextField qText = new JTextField();
            qText.setBounds(100,120,100,25);
            buyPanel.add(qText);
            //quantity

            JLabel pLabel = new JLabel("Price");
            pLabel.setBounds(10,150,80,25);
            buyPanel.add(pLabel);
            JTextField pText = new JTextField();
            pText.setBounds(100,150,100,25);
            buyPanel.add(pText);
            //price

            
            JLabel message = new JLabel("Messages");
            message.setBounds(10, 180, 500,25);
            buyPanel.add(message);

            //textarea with scrollpane
            JTextArea text = new JTextArea(100, 100);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setBounds(10, 200, 725,200);
            JScrollPane scroll = new JScrollPane(text);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10, 200, 725, 200);
            text.setEditable(false);
            //buyPanel.add(text);
            buyPanel.add(scroll);
            text.setBackground(Color.WHITE);
            text.setVisible(true);

            //Message Panel

            JButton submit = new JButton( new AbstractAction("Buy")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    //retrieve data
                    Boolean exists = false;
                    Boolean correct = false;
                    String choiceInput = (String)choice.getSelectedItem();
                    String isymbol = symText.getText();
                    String iname = nameeText.getText();
                    String iprice = pText.getText();
                    String iquantity = qText.getText();

                    //error checking
                    if (!isNumeric(iquantity) || !isNumeric(iprice))
                    {
                        text.setText("Quantity and Price input must be numeric.\n");
                        correct = false;
                    }

                    if (isNumeric(iquantity) && isNumeric(iprice))
                    {
                       if ( (Double.valueOf(iquantity) <= 0 ) || (Double.valueOf(iprice) <= 0))
                       {
                           text.setText("Quantity and Price must be non-negative");
                           correct = false;
                       }
                    }

                    if (isymbol.isEmpty() || iname.isEmpty() || iprice.isEmpty() || iquantity.isEmpty())
                    {
                        text.setText("All fields must be filled to create or add to an Investment.\n");
                        correct = false;
                    }

                    if (!isymbol.isEmpty() && !iname.isEmpty() && !iprice.isEmpty() && !iquantity.isEmpty() && isNumeric(iquantity) && isNumeric(iprice)
                    && Double.valueOf(iquantity)>=0 && Double.valueOf(iprice)>=0)
                    {
                        correct = true;
                    }

                    if (correct == true)
                    {
                        if (choiceInput.equalsIgnoreCase("Stock"))
                        {
                            for (int element = 0; element < investments.size(); element++) 
                            {
                                if (investments.get(element) instanceof Stock)
                                {
                                    String temp = investments.get(element).getSymbol();
                                    if (temp.equals(isymbol))
                                    {
                                        exists = true;
                                        investments.get(element).buy(Integer.valueOf(iquantity), Double.valueOf(iprice));
                                        String temp2 = investments.get(element).toString();
                                        text.setText("Symbol Exists in Stock Previously.\n"
                                        +"Purchasing Additional Stock.\n"
                                        +temp2);

                                    }//if stock exists
                                }//get instance of stock
                            }//loop for stock exists and purchase

                            if (exists == false)
                            {
                            //if stock does not exist make a stock
                            Stock newStock = new Stock();
                            newStock.setSymbol(isymbol);
                            newStock.setName(iname);
                            newStock.setQuantity(Integer.valueOf(iquantity));
                            newStock.setPrice(Double.valueOf(iprice));
                            newStock.purchaseBookValue();
                            text.setText("Creating new Stock\n"+ newStock.toString());

                            //add to list
                            investments.add(newStock);

                            //add to hash
                            iname = iname.toLowerCase();
                            String[] splitbuy = iname.split(mapDelimiters);
                
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

                        if (choiceInput.equalsIgnoreCase("Mutual Fund"))
                        {
                            for (int element = 0; element < investments.size(); element++) 
                            {
                                if (investments.get(element) instanceof MutualFund)
                                {
                                    String temp = investments.get(element).getSymbol();
                                    if (temp.equals(isymbol))
                                    {
                                        exists = true;
                                        investments.get(element).buy(Integer.valueOf(iquantity), Double.valueOf(iprice));
                                        String temp2 = investments.get(element).toString();
                                        text.setText("Symbol Exists in Mutual Fund Previously.\n"
                                        +"Purchasing Additional Mutual Fund.\n"
                                        +temp2);
                                    }//if mf exists
                                }//get instance of mf
                            }//loop for mf exists and purchase

                            if (exists == false)
                            {
                            //if mf does not exist make a mf
                            MutualFund newMF = new MutualFund();
                            newMF.setSymbol(isymbol);
                            newMF.setName(iname);
                            newMF.setQuantity(Integer.valueOf(iquantity));
                            newMF.setPrice(Double.valueOf(iprice));
                            newMF.purchaseBookValue();
                            text.setText("Creating new Mutual Fund\n"+ newMF.toString());

                            //add to list
                            investments.add(newMF);

                            //add to hash
                            iname = iname.toLowerCase();
                            String[] splitbuy = iname.split(mapDelimiters);
                
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
                            }//if does not exist previously (make new mf)
                        }//if mf
                }//correct
                }//actionPerformed
            });

            JButton reset = new JButton( new AbstractAction("Reset")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    choice.setSelectedIndex(0);
                    symText.setText("");
                    nameeText.setText("");
                    qText.setText("");
                    pText.setText("");
                }
            });

            //add all to panel then add panel to frame
            buyPanel.add(reset);
            reset.setBounds(600, 50, 100, 40);
            buyPanel.add(submit);
            submit.setBounds(600, 100, 100, 40);
            frame.getContentPane().add(buyPanel);
            frame.setLocationRelativeTo(null);
            buyPanel.setVisible(true);
            frame.setVisible(true);

        }
    });//Buy Option

    static JMenuItem sell = new JMenuItem( new AbstractAction("Sell")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            gainPanel.setVisible(false);
            searchPanel.setVisible(false);
            panel.setVisible(false);
            sellPanel.setLayout(null);
            sellPanel.setSize(750,500);
            //set other panels to not visible and add to sellpanel

            //sellPanel gui 
            JLabel intro = new JLabel("Selling an Investment");
            intro.setBounds(10, 0, 400, 25);
            sellPanel.add(intro);

            JLabel symLabel = new JLabel("Symbol");
            symLabel.setBounds(10,40,80,25);
            sellPanel.add(symLabel);
            JTextField symText = new JTextField(0);
            symText.setBounds(100,40,125,25);
            sellPanel.add(symText);
            //symbol
            
            JLabel qLabel = new JLabel("Quantity");
            qLabel.setBounds(10,70,80,25);
            sellPanel.add(qLabel);
            JTextField qText = new JTextField();
            qText.setBounds(100,70,100,25);
            sellPanel.add(qText);
            //quantity

            JLabel pLabel = new JLabel("Price");
            pLabel.setBounds(10,100,80,25);
            sellPanel.add(pLabel);
            JTextField pText = new JTextField();
            pText.setBounds(100,100,100,25);
            sellPanel.add(pText);
            //price

            JLabel message = new JLabel("Messages");
            message.setBounds(10, 180, 400,25);
            sellPanel.add(message);

            //sellPanel textarea
            JTextArea text = new JTextArea(100, 100);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setBounds(10, 200, 725,200);
            JScrollPane scroll = new JScrollPane(text);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,200,725,200);
            text.setEditable(false);
            //sellPanel.add(text);
            sellPanel.add(scroll);
            text.setBackground(Color.WHITE);
  

            //Message Panel

            JButton submit = new JButton( new AbstractAction("Sell")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    Boolean exists = false;
                    Boolean correct = false;
                    String isymbol = symText.getText();
                    String iprice = pText.getText();
                    String iquantity = qText.getText();
                    //data retrieve and error checking
                    if (!isNumeric(iquantity) || !isNumeric(iprice))
                    {
                        text.setText("Quantity and Price input must be numeric");
                        correct = false;
                    }

                    
                    if (isNumeric(iquantity) && isNumeric(iprice))
                    {
                       if ( (Double.valueOf(iquantity) <= 0 ) || (Double.valueOf(iprice) <= 0))
                       {
                           text.setText("Quantity and Price must be non-negative");
                           correct = false;
                       }
                    }

                    if (isymbol.isEmpty() || iprice.isEmpty() || iquantity.isEmpty())
                    {
                        text.setText("All fields must be filled to create or add to an Investment.\n");
                        correct = false;
                    }

                    if (!isymbol.isEmpty() && !iprice.isEmpty() && !iquantity.isEmpty() && isNumeric(iquantity) && isNumeric(iprice)
                    && Double.valueOf(iquantity)>=0 && Double.valueOf(iprice)>=0)
                    {
                        correct = true;
                    }

                    if (correct == true)
                    {
                        for (int element = 0; element < investments.size(); element++) 
                        {
                                String temp = investments.get(element).getSymbol();
                                if (temp.equals(isymbol))
                                {
                                    exists = true;
                                    if (investments.get(element) instanceof Stock)
                                    {
                                        text.setText("Stock Exists matching Symbol; Selling");
                                        if (Integer.valueOf(iquantity) > investments.get(element).getQuantity())
                                        {
                                            text.append("\nCannot sell, Not enough quantity");
                                        }//check for quantity
                                        else
                                        {
                                            investments.get(element).sell(Integer.valueOf(iquantity), Double.valueOf(iprice));
                                            text.append(investments.get(element).sellOut(Integer.valueOf(iquantity), Double.valueOf(iprice)));
                                            text.append("\nInvestment Sold. Printing Stock Attributes" + investments.get(element).toString());
                                            if (investments.get(element).zero(Integer.valueOf(iquantity)) == 1)
                                            {
                                                text.append(investments.get(element).zeroOut(Integer.valueOf(iquantity)));
                                                //String investmentName = investments.get(element).getName().toLowerCase();
                                                //String[] sellSplit = investmentName.split(mapDelimiters);
                
                                                text.append("\nRemoving Stock from List");
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
                                        text.setText("Mutual Fund Exists matching Symbol; Selling");
                                        if (Integer.valueOf(iquantity) > investments.get(element).getQuantity())
                                        {
                                            text.append("\nCannot sell, Not enough quantity");
                                        }//check for quantity
                                        else
                                        {
                                            investments.get(element).sell(Integer.valueOf(iquantity), Double.valueOf(iprice));
                                            text.append(investments.get(element).sellOut(Integer.valueOf(iquantity), Double.valueOf(iprice)));
                                            text.append("\nInvestment Sold. Printing Mutual Fund Attributes" + investments.get(element).toString());
                                            if (investments.get(element).zero(Integer.valueOf(iquantity)) == 1)
                                            {
                                                text.append(investments.get(element).zeroOut(Integer.valueOf(iquantity)));
                                                //String investmentName = investments.get(element).getName().toLowerCase();
                                                //String[] sellSplit = investmentName.split(mapDelimiters);
                
                                                text.append("\nRemoving Mutual Fund from List");
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
                            text.setText("Investment does not exist\n");
                        }
                    }//correct
                }//actionPerformed
            });//submit

            JButton reset = new JButton( new AbstractAction("Reset")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    symText.setText("");
                    qText.setText("");
                    pText.setText("");
                }
            });//reset

            //add panel to frame
            sellPanel.add(reset);
            reset.setBounds(600, 50, 100, 40);
            sellPanel.add(submit);
            submit.setBounds(600, 100, 100, 40);
            frame.getContentPane().add(sellPanel);
            frame.setLocationRelativeTo(null);
            sellPanel.setVisible(true);
            frame.setVisible(true);

        }
    });//Sell Option


    static JMenuItem update = new JMenuItem( new AbstractAction("Update")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            gainPanel.setVisible(false);
            searchPanel.setVisible(false);
            panel.setVisible(false);
            updatePanel.setLayout(null);
            updatePanel.setSize(750,500);
            //set other panels to non visible and add to update panel

            JLabel intro = new JLabel("Updating Investments");
            intro.setBounds(10, 0, 400, 25);
            updatePanel.add(intro);

            JLabel symLabel = new JLabel("Symbol");
            symLabel.setBounds(10,40,80,25);
            updatePanel.add(symLabel);
            JTextField symText = new JTextField();
            symText.setBounds(100,40,125,25);
            symText.setEditable(false);
            updatePanel.add(symText);
            symText.setText(investments.get(0).getSymbol());
            //symbol
            
            JLabel nameLabel = new JLabel("Name");
            nameLabel.setBounds(10,70,80,25);
            updatePanel.add(nameLabel);
            JTextField nameText = new JTextField();
            nameText.setBounds(100,70,200,25);
            nameText.setEditable(false);
            updatePanel.add(nameText);
            nameText.setText(investments.get(0).getName());
            //name

            JLabel pLabel = new JLabel("Price");
            pLabel.setBounds(10,100,80,25);
            updatePanel.add(pLabel);
            JTextField pText = new JTextField();
            pText.setBounds(100,100,100,25);
            updatePanel.add(pText);
            pText.setText(String.valueOf(investments.get(0).getPrice()));
            //price

            JLabel message = new JLabel("Messages");
            message.setBounds(10, 180, 100,25);
            updatePanel.add(message);

            JTextArea text = new JTextArea(100, 100);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setBounds(10, 200, 725,200);
            JScrollPane scroll = new JScrollPane(text);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,200,725,200);
            text.setEditable(false);
            text.setBackground(Color.WHITE);
            //panel.add(text);
            updatePanel.add(scroll);
            //textarea


            //loop through investments to find a match for symbol
            //when match found, prev sets values to match symbol-1 elment
            //next sets values to match symbol + 1 element
            JButton prev = new JButton( new AbstractAction("Prev")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    String elementSym = symText.getText();
                    String firstEle = investments.get(0).getSymbol();
                    int prevEle = 0;
                    if (elementSym.equalsIgnoreCase(""))
                    {
                        //account for two ends of list
                        symText.setText(investments.get(investments.size()-1).getSymbol());
                        nameText.setText(investments.get(investments.size()-1).getName());
                        pText.setText(String.valueOf(investments.get(investments.size()-1).getPrice()));
                    }
                    else if (elementSym.equals(firstEle))
                    {
                        symText.setText(investments.get(investments.size()-1).getSymbol());
                        nameText.setText(investments.get(investments.size()-1).getName());
                        pText.setText(String.valueOf(investments.get(investments.size()-1).getPrice()));
                    }
                    else
                    {
                        for (int element = 0; element < investments.size(); element++)
                        {
                            if (investments.get(element).getSymbol().equals(elementSym))
                            {
                                prevEle = element - 1;
                            }
                        }
                        symText.setText(investments.get(prevEle).getSymbol());
                        nameText.setText(investments.get(prevEle).getName());
                        pText.setText(String.valueOf(investments.get(prevEle).getPrice()));
                    }
                }
            });

            JButton next = new JButton( new AbstractAction("Next")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    String elementSym = symText.getText();
                    String lastEle = investments.get(investments.size()-1).getSymbol();
                    int nextEle = 0;
                    if (elementSym.equalsIgnoreCase(""))
                    {
                        //account for two ends of the list
                        symText.setText(investments.get(0).getSymbol());
                        nameText.setText(investments.get(0).getName());
                        pText.setText(String.valueOf(investments.get(0).getPrice()));
                    }
                    else if (elementSym.equals(lastEle))
                    {
                        symText.setText(investments.get(0).getSymbol());
                        nameText.setText(investments.get(0).getName());
                        pText.setText(String.valueOf(investments.get(0).getPrice()));
                    }
                    else
                    {
                        for (int element = 0; element < investments.size(); element++)
                        {
                            if (investments.get(element).getSymbol().equals(elementSym))
                            {
                                nextEle = element + 1;
                            }
                        }
                        symText.setText(investments.get(nextEle).getSymbol());
                        nameText.setText(investments.get(nextEle).getName());
                        pText.setText(String.valueOf(investments.get(nextEle).getPrice()));
                    }
                }
            });

            JButton save = new JButton( new AbstractAction("Save")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    String elementSym = symText.getText();
                    int nextEle = 0;
                    //get element num
                    for (int element = 0; element < investments.size(); element++)
                    {
                        String temp = investments.get(element).getSymbol();
                        if (temp.equals(elementSym))
                        {
                            nextEle = element;
                        }
                    }

                    //setPrice at calculated element num
                    String elementP = pText.getText();
                    if (isNumeric(elementP))
                    {
                        investments.get(nextEle).setPrice(Double.valueOf(elementP));
                    }
                    if (!isNumeric(elementP))
                    {
                        text.setText("Price input must be numeric and non-negative");
                    }
                }
            });

            //add to panel then add panel to frame
            next.setBounds(600, 70, 100, 40);
            updatePanel.add(next);
            updatePanel.add(prev);
            prev.setBounds(600, 20, 100, 40);
            updatePanel.add(save);
            save.setBounds(600, 120, 100, 40);
            frame.getContentPane().add(updatePanel);
            frame.setLocationRelativeTo(null);
            updatePanel.setVisible(true);
            frame.setVisible(true);

        }
    });//Update Option


    static JMenuItem gain = new JMenuItem( new AbstractAction("Get Gain")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            double gain = 0.0;

            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            sellPanel.setVisible(false);
            searchPanel.setVisible(false);
            panel.setVisible(false);
            //set other panels to not visible, add to updatePanel

            gainPanel.setLayout(null);
            gainPanel.setSize(750,500);

            //updatePanel gui
            JLabel intro = new JLabel("Getting Total Gain");
            intro.setBounds(10, 0, 400, 25);
            gainPanel.add(intro);

            JLabel gainLabel = new JLabel("Total Gain");
            gainLabel.setBounds(10,40,80,25);
            gainPanel.add(gainLabel);
            JTextField gainText = new JTextField();
            gainText.setBounds(100,40,125,25);
            gainText.setEditable(false);
            gainPanel.add(gainText);
            

            JLabel message = new JLabel("Individual Gains");
            message.setBounds(10, 80, 100,25);
            gainPanel.add(message);

            JTextArea text = new JTextArea(100, 100);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setBounds(10, 100, 725,200);
            JScrollPane scroll = new JScrollPane(text);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            text.setEditable(false);
            text.setBackground(Color.WHITE);
            //outputPanel.add(text);
            scroll.setBounds(10, 100, 725, 200);
            gainPanel.add(scroll);

            double tempDouble = 0.0;
            //loop through investments and get max gain while adding gain per investment to display
            text.setText("Gain from each Investment: \n");
            for (int element = 0; element < investments.size(); element++) 
            {
                int tempInt = element+1;
                text.append("\nInvestment Number: " + tempInt);
                text.append(investments.get(element).toString());
                text.append("Gain for this Investment: ");
                tempDouble = investments.get(element).getGain();
                //round
                DecimalFormat df = new DecimalFormat("#.##");      
                tempDouble = Double.valueOf(df.format(tempDouble));
                text.append(String.valueOf(tempDouble));
                text.append("\n");
                gain += investments.get(element).getGain();
                text.append("\n");
            }//loop for investment gain

            //round 
            DecimalFormat df = new DecimalFormat("#.##");      
            gain = Double.valueOf(df.format(gain));
            gainText.setText("$" + gain);
            //add to panel then add panel to frame
            frame.getContentPane().add(gainPanel);
            frame.setLocationRelativeTo(null);
            gainPanel.setVisible(true);
            frame.setVisible(true);
        }
    });//Gain Option

    static JMenuItem search = new JMenuItem( new AbstractAction("Search")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            sellPanel.setVisible(false);
            gainPanel.setVisible(false);
            panel.setVisible(false);
            //set other panels to not visible then add to searchPanel

            searchPanel.setLayout(null);
            panel.setSize(750,500);

            //searchPanel gui
            JLabel intro = new JLabel("Searching Investments");
            intro.setBounds(10, 0, 400, 25);
            searchPanel.add(intro);

            JLabel symLabel = new JLabel("Symbol");
            symLabel.setBounds(10,40,80,25);
            searchPanel.add(symLabel);
            JTextField symText = new JTextField();
            symText.setBounds(130,40,125,25);
            symText.setText("");
            searchPanel.add(symText);
            
            JLabel nameLabel = new JLabel("Name/Keywords");
            nameLabel.setBounds(10,70,100,25);
            searchPanel.add(nameLabel);
            JTextField nameText = new JTextField();
            nameText.setBounds(130,70,200,25);
            nameText.setText("");
            searchPanel.add(nameText);

            JLabel pLabel = new JLabel("Low Price");
            pLabel.setBounds(10,100,80,25);
            searchPanel.add(pLabel);
            JTextField pText = new JTextField();
            pText.setBounds(130,100,100,25);
            pText.setText("");
            searchPanel.add(pText);
            
            JLabel p2Label = new JLabel("High Price");
            p2Label.setBounds(10,130,80,25);
            searchPanel.add(p2Label);
            JTextField p2Text = new JTextField();
            p2Text.setBounds(130,130,100,25);
            p2Text.setText("");
            searchPanel.add(p2Text);

            
            JLabel message = new JLabel("Search Results");
            message.setBounds(10, 160, 100,25);
            searchPanel.add(message);

            JTextArea text = new JTextArea(100, 100);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setBounds(10, 180, 725,200);
            JScrollPane scroll = new JScrollPane(text);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,180, 725, 200);
            text.setEditable(false);
            text.setBackground(Color.WHITE);
            searchPanel.add(scroll);
            //textarea formatting

            JButton reset = new JButton( new AbstractAction("Reset")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    symText.setText("");
                    p2Text.setText("");
                    pText.setText("");
                    nameText.setText("");
                    //reset all values to null
                }
            });


            JButton sea = new JButton( new AbstractAction("Search")
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    boolean correct = true;
                    String temp1 = p2Text.getText();
                    String temp2 = pText.getText();
                    //error checking
                    if (temp1.isEmpty())
                    {
                        p2Text.setText("0");
                    }

                    if (temp2.isEmpty())
                    {
                        pText.setText("0");
                    }

                    if (!temp1.isEmpty() || !temp2.isEmpty()) 
                    {
                        if (isNumeric(temp1) && isNumeric(temp2) && Double.valueOf(temp1) >= 0 && Double.valueOf(temp2) >= 0)
                        {
                            correct = true;
                        }
                        else if (!isNumeric(temp1) || !isNumeric(temp2))
                        {
                            correct = false;
                            text.setText("Please enter numeric values for prices");
                        }
                    }

                    if (correct == false)
                    {
                        text.setText("Please ensure price values are numbers, non-negative and both values filled");
                    }

                    if (correct == true)
                    {

                    String sym = symText.getText();
                    double high = Double.valueOf(p2Text.getText());
                    double low = Double.valueOf(pText.getText());
                    String name = nameText.getText();
                    //data retrieve

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

                    if (!temp1.equalsIgnoreCase("") && !temp2.equalsIgnoreCase(""))
                    {
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
                        text.setText("No matching investments found!");
                    }//if none found
                    else
                    {
                        text.setText("Matches Found: \n");
                        for (Investment curr: match)
                        {
                            text.append(curr.toString());
                        }//print found search
                    }//if found

                    }//correct
                }
            });

            searchPanel.add(reset);
            reset.setBounds(600, 20, 100, 40);
            searchPanel.add(sea);
            sea.setBounds(600, 120, 100, 40);
            frame.getContentPane().add(searchPanel);
            frame.setLocationRelativeTo(null);
            searchPanel.setVisible(true);
            frame.setVisible(true);

        }
    });//Search Option

    /**
     * @param args contains command line arguments passed to main
     * 
     * The main runs a GUI with a menubar and 6 choices. The program contains 5 panels
     * which are rotated (i.e. set visible or not visible) based on the menuItem chosen
     * <p>
     * ArrayList of investments is used to store multiple investment objects
     * A HashMap of Strings and an Integer Arraylist is used to store keywords
     * and the element numbers of ArrayList investments that contain those
     * keywords.
     * <p>
     * Buy: User enters input and creates a type of investment
     * The investment is added to the ArrayList and the name is broken down into
     * keywords and added to the hashmap (if investment exists previously,
     * quantity is increased and bookValue is recalculated)
     * <p>
     * Sell: User enters amount to sell and the price. Depending on the userinput of
     * quantity, an error message is given for userQuantity is greater than investmentQuantity
     * If appropirate quantity, the sell is performed and bookValue, quantity is updated.
     * if the sell is zero'd (i.e. no quantity left) the investment is removed and the
     * hashmap is recreated to account for it.
     * <p>
     * Update: Loops through investment ArrayList using buttons and updates the prices based on user
     * input.
     * <p>
     * Gain: Calculates gain from each investment based on the arithmetic provided by
     * the professor.
     * <p>
     * Search: Get user input for search values. Loop through investment list for 
     * symbol, name (broken into keywords using HashMap and Integer ArrayList to match)
     *  and price range (user can decide to input nothing for any of the terms).
     * <p>
     * Quit: Exits the program. The X button on the frame does the same
     * 
     * Main does not return anything
     */
    public static void main(String[] args)
    {
        //set layouts and form for main frame and welcome panel
        frame.setSize(760,510);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        
        panel.setSize(750,500);
        panel.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        //creating mennuBar and adding MenuItems
        JMenuBar menuBar = new JMenuBar();
        JMenu command = new JMenu("Commands");
        command.add(buy);
        command.add(sell);
        command.add(update);
        command.add(gain);
        command.add(search);
        command.add(quit);
        menuBar.add(command);
        frame.setJMenuBar(menuBar);

        //welcome message generation
        JTextArea welcome = new JTextArea();
        welcome.setText("\n\n\nWelcome to ePortfolio.\n\n\nChoose a command from "+
        "the \"Commands\" menu to buy or sell an investment, update prices for"+
        " all the investments, get gain for the portfolio, search for relevant investments, or quit the program");
        welcome.setRows(5);
        welcome.setColumns(5);
        welcome.setBackground(Color.WHITE);
        welcome.setEditable(false);
        welcome.setLineWrap(true);
        welcome.setWrapStyleWord(true);
        welcome.setSize(535,144);

        //editing base text for textarea
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        welcome.setFont(font);
        welcome.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(welcome);

        //adding to frame (scrollpane not needed so overlapped)
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(welcome, BorderLayout.CENTER);
        frame.add(panel);
        frame.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        frame.setVisible(true);
    }//main



}//portfolio gui
