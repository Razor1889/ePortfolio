import java.text.DecimalFormat;
/**
 * @author Mirza Baig
 * @version 11/09/2021
 * 
 */
public class Investment 
{
    /**
     * An Investment class
     */
    String symbol = "";
    String name = "";
    int quantity = 0;
    double price = 0.0;
    double bookValue = 0.0;
    double gain = 0.0;
    double payment = 0.0;


    //Most overrides are in Mutual Fund
    //Investment based on Stock
    public Investment()
    {

    }//base

    /**
     * This function initializes the object with values based on param
     * and then calculates the bookValue
     * @param symbol represents the symbol to set
     * @param name represents the name to set
     * @param quantity represents the quantity to set
     * @param price represents the price to set
     */
    public void newInvestment (String symbol, String name, int quantity, double price)
    {
        //Set up a new stock
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }//newInvestment
    
    /**
     * This function calculates the payment on sell
     * @param sellQuantity represents the quanity
     * @param sellPrice represents the sell price
     */
    public void sell (int sellQuantity, double sellPrice)
    {
        this.payment = (sellQuantity * sellPrice -9.99);
        //change bookvalue and quantity
        this.bookValue = (this.bookValue * ((this.quantity - sellQuantity)/this.quantity));
        this.quantity = this.quantity - sellQuantity;
    }//sell(stock)


     /**
     * This function outputs the payment
     * @param sellQuantity represents the quanity
     * @param sellPrice represents the sell price
     * @return the output string
     */
    public String sellOut (int sellQuantity, double sellPrice)
    {
        this.payment = (sellQuantity * sellPrice -9.99);
        DecimalFormat df = new DecimalFormat("#.##");      
        this.payment = Double.valueOf(df.format(this.payment));

        return "\nYour payment is: $" + this.payment;
    }//sellOut

    /**
     * This function returns a value 1 if the investment has 0 or less quantity
     * @param quantity represents the quanity
     * @return whether pruchase is zero'd (1) or not (0)
     */
    public int zero(int quantity)
    {
        if (this.quantity < 0)
        {
            return 1;
        }

        if (this.quantity == 0)
        {
        this.payment = this.price*this.quantity-9.99;
        this.gain = this.payment-this.bookValue;
        return 1;
        }

        if (this.quantity > 0)
        {
        this.bookValue = this.bookValue * ((this.quantity-quantity)/this.quantity);
            return 0;
        }
        return 0;
    }//stocKZero

    /**
     * This function returns a string containing the output for gain and payment when
     * zero'd
     * @param quantity represents the quanity
     * @return string output
     */
    public String zeroOut (int quantity)
    {
        this.payment = this.price*this.quantity-9.99;
        this.gain = this.payment-this.bookValue;
        DecimalFormat df = new DecimalFormat("#.##");      
        this.payment = Double.valueOf(df.format(this.payment));
        this.gain = Double.valueOf(df.format(this.gain));

        return "Your Payment is: $" + this.payment + "\nYour Gain is: $" + this.gain;
    }//zeroOut

    /**
     * This function calcuates the bookValue based on params
     * @param buyQuantity represents the quanity bought
     * @param buyPrice represents the price at which quantity is bought
     */
    public void buy (int buyQuantity, double buyPrice)
    {
        //using buy formula
        this.bookValue += (buyQuantity * buyPrice + 9.99);
        this.quantity += buyQuantity;
        this.price = buyPrice;

    }//buy

    /**
     * This function sets the bookValue
     */
    public void purchaseBookValue()
    {
        //bookValue formula
        this.bookValue = this.quantity * this.price + 9.99;
    }//purchaseBookValue


    /**
     * This function returns a string with the object's attributes formatted.
     * @return a formatted string that represents the object's attributes which can be printed
     */
    public String toString()
    {       
        return "\n" + "Symbol: " + this.symbol + "\n" + "Name: " + this.name + "\n" + "Quantity: " + String.valueOf(this.quantity)
        + "\n" + "Price: " + Double.toString(this.price) + "\n" + "Book Value: " + Double.toString(this.bookValue) + "\n";
    }//toStringInvestment


    /**
     * This function compares this Object (Investment) with param other Object to check
     * if this Investment Object is equal to the other Object
     * @param other represents another object
     * @return boolean false if Object and attributes is not the same, true otherwise
     */
    public boolean equals(Object other)
    {
        if (other == null)
        {
            //if object null
        return false;
        }

        if (this.getClass() != other.getClass())
        {
            //if class type is not the same
            return false;
        }

        if (this.bookValue != ((Investment)other).bookValue)
        {
            //if bookvalue is not equal
            return false;
        }

        if (!this.name.equals(((Investment)other).name))
        {
            //if name is not equal
            return false;
        }

        if (!this.symbol.equals(((Investment)other).symbol))
        {
            //if symbol is not equal
            return false;
        }
        
        if (this.price != ((Investment)other).price)
        {
            //if price is not equal
            return false;
        }

        if (this.quantity != ((Investment)other).quantity)
        {
            //if quantity is not equal
            return false;
        }

    return true;
    }//equals

/**
 * This function returns a string formatted such that it can be printed to a file
 * @return the string being printed to file
 */
    public String printToFile()
    {
        DecimalFormat df = new DecimalFormat("0.00");
        String formatted = df.format(this.price);
        this.price = Double.parseDouble(formatted);
        formatted = df.format(this.bookValue);
        this.bookValue = Double.parseDouble(formatted);

    return "type = \"stock\"\n" + "symbol = \"" + this.symbol + "\"\n" + "name = \"" + this.name + "\"\n"
    + "quantity = \"" + this.quantity + "\"\n" + "price = \"" + this.price + "\"\n" + "bookValue = \"" + this.bookValue + "\"\n" + "\n";
    }//printToFile

    //GettersAndSetters
    //getters
    public String getName() 
    {
        return this.name;
    }

    public String getSymbol() 
    {
        return this.symbol;
    }
     
    public int getQuantity() 
    {
        return this.quantity;
    }
    
    public double getPrice()
    {
        return this.price;
    }

    public double getBookValue()
    {
        return this.bookValue;
    }

    public double getGain()
    {
        return ((this.price*this.quantity-9.99) - this.bookValue);
    }

    public double getPayment()
    {
        return this.payment;
    }

//Setters

public void setName(String name) 
{
    this.name = name;
}

public void setSymbol(String symbol) 
{
    this.symbol = symbol;
}
 
public void setQuantity( int quantity) 
{
    this.quantity = quantity;
}

public void setPrice( double price) 
{
    this.price = price;
}

public void setBookValue( double bookValue) 
{
    this.bookValue = bookValue;
}

public void setGain( double gain) 
{
    this.gain = gain;
}

public void setPayment( double payment) 
{
    this.payment = payment;
}

}//Investment
