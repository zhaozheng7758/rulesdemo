package com.sapient.rulesdemo.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegulatoryTradeEligibilityRequest
{
    private String              id;
    private String              jurisdiction;
    private Trade               trade;
    private Customer            customer;
    private Map<String, String> ruleResultsMap = new ConcurrentHashMap<String, String>();

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the jurisdiction
     */
    public String getJurisdiction()
    {
        return jurisdiction;
    }

    /**
     * @param jurisdiction
     *            the jurisdiction to set
     */
    public void setJurisdiction(String jurisdiction)
    {
        this.jurisdiction = jurisdiction;
    }

    /**
     * @return the trade
     */
    public Trade getTrade()
    {
        return trade;
    }

    /**
     * @param trade
     *            the trade to set
     */
    public void setTrade(Trade trade)
    {
        this.trade = trade;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    /**
     * @return the ruleResults
     */
    public Map<String, String> getRuleResultsMap()
    {
        return ruleResultsMap;
    }

    /**
     * @param ruleResults
     *            the ruleResults to set
     */
    public void setRuleResultsMap(Map<String, String> ruleResults)
    {
        this.ruleResultsMap = ruleResults;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((jurisdiction == null) ? 0 : jurisdiction.hashCode());
        result = prime * result + ((ruleResultsMap == null) ? 0 : ruleResultsMap.hashCode());
        result = prime * result + ((trade == null) ? 0 : trade.hashCode());
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
        RegulatoryTradeEligibilityRequest other = (RegulatoryTradeEligibilityRequest) obj;
        if (customer == null)
        {
            if (other.customer != null)
                return false;
        }
        else if (!customer.equals(other.customer))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (jurisdiction == null)
        {
            if (other.jurisdiction != null)
                return false;
        }
        else if (!jurisdiction.equals(other.jurisdiction))
            return false;
        if (ruleResultsMap == null)
        {
            if (other.ruleResultsMap != null)
                return false;
        }
        else if (!ruleResultsMap.equals(other.ruleResultsMap))
            return false;
        if (trade == null)
        {
            if (other.trade != null)
                return false;
        }
        else if (!trade.equals(other.trade))
            return false;
        return true;
    }
}
