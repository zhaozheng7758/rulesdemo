package com.sapient.mapreduce.example.model;


public class EquityTradeV1 extends Trade
{
    private Side   side;
    private String symbol;
    private int    quantity;
    private double price;
    private String customer;

    /**
     * @return the side
     */
    public Side getSide()
    {
        return side;
    }

    /**
     * @param side
     *            the side to set
     */
    public void setSide(Side side)
    {
        this.side = side;
    }

    /**
     * @return the symbol
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    /**
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * @return the customer
     */
    public String getCustomer()
    {
        return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(String customer)
    {
        this.customer = customer;
    }
}
