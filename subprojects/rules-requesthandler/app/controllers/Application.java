package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.codehaus.jackson.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.sapient.mapreduce.example.cache.Cache;
import com.sapient.mapreduce.example.cache.CacheManager;
import com.sapient.mapreduce.example.model.Customer;
import com.sapient.mapreduce.example.model.RegulatoryTradeEligibilityRequest;
import com.sapient.mapreduce.example.model.Trade;
import com.sapient.mapreduce.example.rules.RuleEngine;
import com.sapient.mapreduce.example.util.DataGenerator;

public class Application extends Controller
{

    private static final Logger          logger       = LoggerFactory.getLogger(Application.class);

    private static DataGenerator         generator;
    private static Cache<Integer, Trade> tradeCache   = CacheManager.getInstance().createCache("trades");
    private static RuleEngine            ruleEngine[];
    private static final int NUM_ENGINES = 4;

    private static Random                random       = new Random();
    private static final String          tradeTypes[] = { "EquityTradeV1", "EquityTradeV2", "FXSpotTrade" };

    public static Result index()
    {
        return ok(index.render());
    }

    public static Result getTrades()
    {
        checkForGenerator();
        int tradesPerPage = 100;
        int page = 1;

        try
        {
            page = Integer.parseInt(Form.form().bindFromRequest().get("pageNum"));
            tradesPerPage = Integer.parseInt(Form.form().bindFromRequest().get("tradesPerPage"));
        }
        catch (Exception e)
        {
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Set<Integer> tradeIdSet = tradeCache.getKeys();
        logger.debug("Trade ID Set has " + tradeIdSet.size());
        List<Integer> tradeIdList = new ArrayList<Integer>();
        tradeIdList.addAll(tradeIdSet);
        Collections.sort(tradeIdList);

        int min = tradesPerPage * (page - 1);
        int max = (tradesPerPage * page) < tradeIdList.size() ? (tradesPerPage * page) : tradeIdList.size();

        logger.debug("Returning JSON with trades in pos " + min + " to " + max);

        for (int i = min; i < max; i++)
        {
            try
            {
                sb.append(generator.objectToJSON(tradeCache.get(tradeIdList.get(i))));
                if (i != (max - 1))
                {
                    sb.append(",");
                }
            }
            catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        sb.append("]");
        return ok(sb.toString()).as("application/json");
    }

    public static Result resetTradeCache()
    {
        tradeCache.clear();
        return ok();
    }

    public static Result generateTrades()
    {
        checkForGenerator();
        logger.debug("Inside generateTrades()");
        try
        {
            final int numTrades = Integer.parseInt(Form.form().bindFromRequest().get("numTrades"));
            final String tradeType = Form.form().bindFromRequest().get("tradeType");

            logger.debug("Num Trades: " + numTrades);
            logger.debug("Trade Type: " + tradeType);

            for (int i = 0; i < numTrades; i++)
            {
                String tradeTypeStr = tradeType;
                if ("random".equals(tradeType))
                {
                    tradeTypeStr = tradeTypes[random.nextInt(tradeTypes.length)];
                }

                Trade trade = null;
                if ("EquityTradeV1".equals(tradeTypeStr))
                {
                    trade = generator.createEquityTradeV1();
                }
                if ("EquityTradeV2".equals(tradeTypeStr))
                {
                    trade = generator.createEquityTradeV2();
                }
                if ("FXSpotTrade".equals(tradeTypeStr))
                {
                    trade = generator.createFXSpotTrade();
                }
                if (null != trade)
                {
                    logger.debug("Inserting " + trade.getId());
                    tradeCache.put(trade.getId(), trade);
                }
            }
        }
        catch (Exception e)
        {
        }
        return ok();
    }

    private static void checkForGenerator()
    {
        synchronized (Application.class)
        {
            if (null == generator)
            {
                try
                {
                    generator = new DataGenerator();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Result getCustomers()
    {
        return ok();
    }

    public static Result applyRules()
    {
        logger.debug("Inside applyRules()");
        checkRuleEngine();
        Cache<String, Customer> customerCache = CacheManager.getInstance().getCache("customer");
        RegulatoryTradeEligibilityRequest request = new RegulatoryTradeEligibilityRequest();
        Set<Integer> keys = tradeCache.getKeys();
        List<Integer> keyList = new ArrayList<Integer>();
        keyList.addAll(keys);
        Collections.sort(keyList);
        Iterator<Integer> keyIterator = keyList.iterator();
        long startTime = System.currentTimeMillis();
        
        int engNum = 0;
        while (keyIterator.hasNext())
        {
            request.setTrade(tradeCache.get(keyIterator.next()));
            request.setCustomer(customerCache.get(request.getTrade().getCustomer()));
            ruleEngine[engNum].executeStateless(request);            
            request.getTrade().getParams().putAll(request.getRuleResultsMap());
            request.getRuleResultsMap().clear();
        }
        double totalSeconds = (float) (System.currentTimeMillis() - startTime) / 1000.0;
        String message = "Completed rules for " + keyList.size() + " trades in " + totalSeconds + " seconds";
        logger.debug(message);
        return ok(message);
    }

    public static Result clearTrades()
    {
        tradeCache.clear();
        return ok();
    }

    public static void checkRuleEngine()
    {
        if (null == ruleEngine)
        {
            ruleEngine = new RuleEngine[NUM_ENGINES];
            for (int i = 0; i < NUM_ENGINES; i++)
            {
                ruleEngine[i] = new RuleEngine();
                ruleEngine[i].compileResource("drl/trading/eligibility/regulatory/RequestValidation.drl");
                ruleEngine[i].compileResource("drl/trading/eligibility/regulatory/CFTC/USPerson/USPerson.drl");
            }
        }
    }

}
