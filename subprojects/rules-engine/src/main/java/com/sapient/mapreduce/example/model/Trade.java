package com.sapient.mapreduce.example.model;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Trade
{
    private int                 id;
    private Date                tradeDate;
    private Date                settleDate;
    private String              customer;
    private Map<String, String> params = new ConcurrentHashMap<String, String>();

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the tradeDate
     */
    public Date getTradeDate()
    {
        return tradeDate;
    }

    /**
     * @param tradeDate
     *            the tradeDate to set
     */
    public void setTradeDate(Date tradeDate)
    {
        this.tradeDate = tradeDate;
    }

    /**
     * @return the settleDate
     */
    public Date getSettleDate()
    {
        return settleDate;
    }

    /**
     * @param settleDate
     *            the settleDate to set
     */
    public void setSettleDate(Date settleDate)
    {
        this.settleDate = settleDate;
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

    /**
     * @return the params
     */
    public Map<String, String> getParams()
    {
        return params;
    }

    public String getParam(String key)
    {
        return params.get(key);
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }

    public void setParam(String key, String value)
    {
        params.put(key, value);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trade other = (Trade) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
