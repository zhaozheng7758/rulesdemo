package com.sapient.mapreduce.example.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sapient.mapreduce.example.cache.Cache;
import com.sapient.mapreduce.example.cache.CacheManager;
import com.sapient.mapreduce.example.model.Customer;
import com.sapient.mapreduce.example.model.EquityTradeV1;
import com.sapient.mapreduce.example.model.EquityTradeV2;
import com.sapient.mapreduce.example.model.FXSpotTradeV1;
import com.sapient.mapreduce.example.model.Fee;
import com.sapient.mapreduce.example.model.Side;

public class DataGenerator
{
    private static final int        NUM_TRADES_EACH_TYPE = 5000;
    private static final int        MAX_CUSTOMERS        = 1000;
    private JsonFactory             jsonFactory          = null;
    private JsonGenerator           generator            = null;
    private Random                  random               = new Random();
    private int                     tradeId              = 0;
    private int                     customerId           = 9999;
    private Cache<String, Customer> customerCache        = null;
    private String[]                countryCodes         = { "US", "UK", "CA", "AU", "BM" };
    private String[]                ccyPairs             = { "USD/CAD", "EUR/USD", "USD/CHF", "GBP/USD", "NZD/USD", "AUD/USD", "USD/JPY", "EUR/CAD", "EUR/AUD", "EUR/JPY", "EUR/CHF", "EUR/GBP" };
    private final DateFormat        dateFormat           = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final DateFormat        fileDateFormat       = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
    private Date                    fileDate             = null;
    private int                     MAX_FILE_SIZE        = 64 * 1024 * 1024;
    private int                     fileSequence         = 0;
    private ByteArrayOutputStream   outputStream         = new ByteArrayOutputStream();
    private File                    outputFile           = null;
    private String                  outputFilePrefix     = "TradeData";
    private FileOutputStream        outputFileStream     = null;

    public static void main(String args[])
    {
        try
        {
            DataGenerator gen = new DataGenerator();
            gen.generateTradeFile();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public DataGenerator() throws IOException
    {
        jsonFactory = new JsonFactory();
        generator = jsonFactory.createJsonGenerator(outputStream, JsonEncoding.UTF8);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        generator.setCodec(mapper);
        customerCache = CacheManager.getInstance().createCache("customer");
    }

    private void advanceFile(String prefix) throws FileNotFoundException
    {
        outputFilePrefix = prefix;
        advanceFile();
    }

    private void advanceFile() throws FileNotFoundException
    {
        fileSequence++;
        if (null == fileDate)
        {
            fileDate = new Date();
        }
        outputFile = new File(outputFilePrefix + "_" + fileDateFormat.format(fileDate) + "_" + fileSequence + ".json");
        outputFileStream = new FileOutputStream(outputFile);
    }

    public void generateTradeFile() throws IOException
    {
        fileDate = new Date();
        advanceFile("EquityTradesV1");
        for (int i = 0; i < NUM_TRADES_EACH_TYPE; i++)
        {
            writeObjectToBlockFile(createEquityTradeV1());
        }
        advanceFile("EquityTradesV2");
        for (int i = 0; i < NUM_TRADES_EACH_TYPE; i++)
        {
            writeObjectToBlockFile(createEquityTradeV2());
        }
        advanceFile("FXSpotTradesV1");
        for (int i = 0; i < NUM_TRADES_EACH_TYPE; i++)
        {
            writeObjectToBlockFile(createFXSpotTrade());
        }

        advanceFile("Customers");
        Set<String> customerKeys = customerCache.getKeys();
        Iterator<String> keyIt = customerKeys.iterator();
        while (keyIt.hasNext())
        {
            writeObjectToBlockFile(customerCache.get(keyIt.next()));
        }
        generator.close();
    }

    public String generateEquityV1JSON() throws JsonProcessingException, IOException
    {
        return objectToJSON(createEquityTradeV1());
    }
    
    public String generateEquityV2JSON() throws JsonProcessingException, IOException
    {
        return objectToJSON(createEquityTradeV2());
    }

    public String generateFXSpotJSON() throws JsonProcessingException, IOException
    {
        return objectToJSON(createFXSpotTrade());
    }
    
    public String generateCustomerJSON() throws JsonProcessingException, IOException
    {
        return objectToJSON(createCachedCustomer());
    }

    public String objectToJSON(Object object) throws JsonProcessingException, IOException
    {
        generator.writeObject(object);
        byte[] outputBytes = outputStream.toByteArray();
        outputStream.reset();
        return new String(outputBytes);
    }

    public void writeObjectToBlockFile(Object trade) throws JsonProcessingException, IOException
    {
        generator.writeObject(trade);
        generator.writeRaw("\n");
        byte[] outputBytes = outputStream.toByteArray();
        outputStream.reset();
        checkFileSize(outputBytes.length);
        outputFileStream.write(outputBytes);
    }

    private void checkFileSize(int inputSize) throws FileNotFoundException
    {
        long size = outputFile.length();
        if ((size + inputSize) > MAX_FILE_SIZE)
        {
            advanceFile();
        }
    }

    private int nextTradeId()
    {
        tradeId++;
        return tradeId;
    }

    private int getNextCustomerId()
    {
        customerId++;
        return customerId;
    }

    protected Customer createCachedCustomer()
    {
        Customer cust = null;
        if (customerCache.size() < MAX_CUSTOMERS)
        {
            cust = new Customer();
            cust.setId(getNextCustomerId());
            String country = countryCodes[random.nextInt(countryCodes.length)];
            cust.setName("Customer " + cust.getId() + " " + country);
            cust.setPrincipalPlaceOfBusiness(country);
            cust.setLei(randomLEI());
            customerCache.put(cust.getName(), cust);
        }
        else
        {
            Set<String> keys = customerCache.getKeys();
            String custKeys[] = keys.toArray(new String[keys.size()]);
            String key = custKeys[random.nextInt(custKeys.length)];
            cust = customerCache.get(key);
        }
        return cust;
    }

    public EquityTradeV1 createEquityTradeV1()
    {
        EquityTradeV1 trade = new EquityTradeV1();
        createEquityTradeV1(trade);
        return trade;
    }

    public EquityTradeV2 createEquityTradeV2()
    {
        EquityTradeV2 trade = new EquityTradeV2();
        createEquityTradeV1(trade);
        trade.setFees(randomFees());
        return trade;
    }

    public FXSpotTradeV1 createFXSpotTrade()
    {
        FXSpotTradeV1 trade = new FXSpotTradeV1();
        trade.setId(nextTradeId());
        trade.setPair(randomCCYPair());
        trade.setPrice(Double.toString(random4DecPrice()));
        trade.setQuantity(randomMMDouble());
        trade.setSide(randomSide());
        trade.setTradeDate(new Date());
        trade.setSettleDate(new Date());
        trade.setCustomer(createCachedCustomer().getName());
        return trade;
    }

    protected void createEquityTradeV1(EquityTradeV1 trade)
    {
        trade.setId(nextTradeId());
        trade.setTradeDate(new Date());
        trade.setSettleDate(new Date());
        trade.setSide(randomSide());
        trade.setQuantity((random.nextInt(10) + 1) * 100);
        trade.setPrice(random3DecPrice() * 1000);
        trade.setCustomer(createCachedCustomer().getName());
        trade.setSymbol(randomSymbol());
    }

    private List<Fee> randomFees()
    {
        List<Fee> fees = new ArrayList<Fee>();
        fees.add(new Fee("Fee 1", random.nextDouble() * 100));
        fees.add(new Fee("Fee 2", random.nextDouble() * 100));
        fees.add(new Fee("Fee 3", random.nextDouble() * 100));
        return fees;
    }

    private double randomMDouble()
    {
        double result;
        int amt = random.nextInt(100);
        result = (double) (amt * 100);
        return result;
    }

    private double randomMMDouble()
    {
        return randomMDouble() * 10;
    }

    private double random3DecPrice()
    {
        int amt = random.nextInt(1000);
        return (double) amt / 1000;
    }

    private double random4DecPrice()
    {
        int amt = random.nextInt(1000);
        return (double) amt / 1000;
    }

    private Side randomSide()
    {
        Side side;
        if (random.nextBoolean())
        {
            side = Side.BUY;
        }
        else
        {
            side = Side.SELL;
        }
        return side;
    }

    private String randomCCYPair()
    {
        return ccyPairs[random.nextInt(ccyPairs.length)];
    }

    private String randomSymbol()
    {
        char symbol[] = { randomChar(), randomChar(), randomChar() };
        return new String(symbol);
    }

    public String randomLEI()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 16; i++)
        {
            sb.append(randomChar());
        }
        for (int i = 0; i <= 4; i++)
        {
            sb.append(Integer.toString(random.nextInt(9)));
        }
        return sb.toString();
    }

    private char randomChar()
    {
        return (char) (random.nextInt(26) + 'A');
    }

}
