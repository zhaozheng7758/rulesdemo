package com.sapient.mapreduce.example.model;

import java.io.Serializable;

public class Customer implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    int    id;
    String name;
    String lei;
    String principalPlaceOfBusiness;

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
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the lei
     */
    public String getLei()
    {
        return lei;
    }

    /**
     * @param lei
     *            the lei to set
     */
    public void setLei(String lei)
    {
        this.lei = lei;
    }

    /**
     * @return the principalPlaceOfBusiness
     */
    public String getPrincipalPlaceOfBusiness()
    {
        return principalPlaceOfBusiness;
    }

    /**
     * @param principalPlaceOfBusiness
     *            the principalPlaceOfBusiness to set
     */
    public void setPrincipalPlaceOfBusiness(String principalPlaceOfBusiness)
    {
        this.principalPlaceOfBusiness = principalPlaceOfBusiness;
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
        result = prime * result + ((lei == null) ? 0 : lei.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((principalPlaceOfBusiness == null) ? 0 : principalPlaceOfBusiness.hashCode());
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
        Customer other = (Customer) obj;
        if (id != other.id)
            return false;
        if (lei == null)
        {
            if (other.lei != null)
                return false;
        }
        else if (!lei.equals(other.lei))
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (principalPlaceOfBusiness == null)
        {
            if (other.principalPlaceOfBusiness != null)
                return false;
        }
        else if (!principalPlaceOfBusiness.equals(other.principalPlaceOfBusiness))
            return false;
        return true;
    }
}
