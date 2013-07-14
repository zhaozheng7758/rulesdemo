package com.sapient.mapreduce.example.model;

public class FXSpotTradeV1 extends Trade
{
    private String pair;
    private Side   side;
    private String price;
    private double quantity;
    private String customer;

    /**
     * @return the pair
     */
    public String getPair()
    {
        return pair;
    }

    /**
     * @param pair
     *            the pair to set
     */
    public void setPair(String pair)
    {
        this.pair = pair;
    }

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
     * @return the price
     */
    public String getPrice()
    {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(String price)
    {
        this.price = price;
    }

    /**
     * @return the qty
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * @param qty
     *            the qty to set
     */
    public void setQuantity(double qty)
    {
        this.quantity = qty;
    }

    /**
     * @return the account
     */
    public String getCustomer()
    {
        return customer;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setCustomer(String account)
    {
        this.customer = account;
    }
}
