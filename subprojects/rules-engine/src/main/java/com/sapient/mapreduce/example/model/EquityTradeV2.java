package com.sapient.mapreduce.example.model;

import java.util.ArrayList;
import java.util.List;

public class EquityTradeV2 extends EquityTradeV1
{
    private List<Fee> fees = new ArrayList<Fee>();
    
    /**
     * @return the fees
     */
    public List<Fee> getFees()
    {
        return fees;
    }

    /**
     * @param fees the fees to set
     */
    public void setFees(List<Fee> fees)
    {
        this.fees = fees;
    }
}
