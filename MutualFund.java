import java.text.DecimalFormat;
/**
 * @author Mirza Baig
 * @version 11/09/2021
 * 
 */
public class MutualFund extends Investment
{
    /**
     * A Mutual Fund class Extended by superclass Investment
     * Base constructor
     */
    public MutualFund()
    {

    }

    /**
     * Override
     * This function initializes the object with values based on param
     * and then calculates the bookValue
     * @param symbol represents the symbol to set
     * @param name represents the name to set
     * @param quantity represents the quantity to set
     * @param price represents the price to set
     */
    @Override
    public void newInvestment (String symbol, String name, int quantity, double price)
    {
        //Set up a new Mutual Fund
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = quantity * price;

    }//newMutualFund


    /**
     * Override
     * This function calculates the payment on sell
     * @param sellQuantity represents the quanity
     * @param sellPrice represents the sell price
     */
    @Override
    public void sell (int sellQuantity, double sellPrice)
    {
        this.payment = (sellQuantity * sellPrice - 45);

        //change bookValue and quantity
        this.bookValue = (this.bookValue * ((this.quantity - sellQuantity)/this.quantity));
        this.quantity = this.quantity - sellQuantity;
    }//sell

     /**
     * This function outputs the payment
     * @param sellQuantity represents the quanity
     * @param sellPrice represents the sell price
     * @return the output string
     */
    @Override
    public String sellOut (int sellQuantity, double sellPrice)
    {
        this.payment = (sellQuantity * sellPrice -45);
        DecimalFormat df = new DecimalFormat("#.##");      
        this.payment = Double.valueOf(df.format(this.payment));

        return "\nYour payment is: $" + this.payment;
    }//sellOut



    /**
     * Override
     * This function returns a value 1 if the investment has 0 or less quantity
     * @param quantity represents the quanity
     * @return whether pruchase is zero'd (1) or not (0)
     */
    @Override
    public int zero(int quantity)
    {
        if (this.quantity < 0)
        {
            return 1;
        }
        if (this.quantity == 0)
        {
            this.payment = this.price*this.quantity-45;
            this.gain = this.payment-this.bookValue;
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
    }//mutualFundZero

    /**
     * Override
     * This function returns a string containing the output for gain and payment when
     * zero'd
     * @param quantity represents the quanity
     * @return string output
     */
    @Override
    public String zeroOut (int quantity)
    {
        this.payment = this.price*this.quantity-45;
        this.gain = this.payment-this.bookValue;
        double outP = this.price*this.quantity-9.99;
        this.payment = this.price*this.quantity-9.99;
        this.gain = this.payment-this.bookValue;

        DecimalFormat df = new DecimalFormat("#.##");      
        outP = Double.valueOf(df.format(outP));
        this.gain = Double.valueOf(df.format(this.gain));

        return "Your Payment is: $" + outP + "\nYour Gain is: $" + this.gain;
    }//zeroOut

    /**
     * Override
     * This function calcuates the bookValue based on params
     * @param buyQuantity represents the quanity bought
     * @param buyPrice represents the price at which quantity is bought
     */
    @Override
    public void buy (int buyQuantity, double buyPrice)
    {
        //using buy formula
        this.bookValue += (buyQuantity * buyPrice);
        this.quantity += buyQuantity;
        this.price = buyPrice;
    }//buy

    /**
     * Override
     * This function sets the bookValue
     */
    @Override
    public void purchaseBookValue()
    {
        //bookValue formula
        this.bookValue = this.quantity * this.price;
    }//purchaseBookValue



/**
 * Override
 * This function returns a string formatted such that it can be printed to a file
 * @return the string being printed to file
 */
@Override
    public String printToFile()
    {
        DecimalFormat df = new DecimalFormat("0.00");
        String formatted = df.format(this.price);
        this.price = Double.parseDouble(formatted);
        formatted = df.format(this.bookValue);
        this.bookValue = Double.parseDouble(formatted);

    return "type = \"mutualfund\"\n" + "symbol = \"" + this.symbol + "\"\n" + "name = \"" + this.name + "\"\n"
    + "quantity = \"" + this.quantity + "\"\n" + "price = \"" + this.price + "\"\n" + "bookValue = \"" + this.bookValue + "\"\n" + "\n";
    }//printToFile

}//MutualFund
