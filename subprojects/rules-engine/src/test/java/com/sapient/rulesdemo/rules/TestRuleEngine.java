package com.sapient.rulesdemo.rules;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import com.sapient.rulesdemo.cache.Cache;
import com.sapient.rulesdemo.cache.CacheManager;
import com.sapient.rulesdemo.model.Customer;
import com.sapient.rulesdemo.model.RegulatoryTradeEligibilityRequest;
import com.sapient.rulesdemo.model.Trade;
import com.sapient.rulesdemo.rules.RuleEngine;
import com.sapient.rulesdemo.util.DataGenerator;

public class TestRuleEngine
{
    private static final String US           = "US";
    private static final String AU           = "AU";
    private static final String US_PERSON    = "US Person";
    private static final String TRUE         = "TRUE";
    private static final String FALSE        = "FALSE";
    private static final int    REPEAT_LIMIT = 1000;
    private static final double MM           = 1000000;

    @Test
    public void testRuleEngine()
    {
        try
        {
            // Step 1 : Create a Trade Generator, a Customer Cache, and Rules Engine
            DataGenerator generator = new DataGenerator();
            RuleEngine ruleEngine = new RuleEngine();
            Cache<String, Customer> customerCache = CacheManager.getInstance().getCache("customer");

            // Step 2 : Compile our rules
            ruleEngine.compileResource("drl/trading/eligibility/regulatory/RequestValidation.drl");
            ruleEngine.compileResource("drl/trading/eligibility/regulatory/CFTC/USPerson/USPerson.drl");

            // Step 3 : Generate a fictitious trade and a request
            RegulatoryTradeEligibilityRequest request = createRequest(generator, customerCache);

            // Step 4 : Ensure the counterparty is in the US
            request.getCustomer().setPrincipalPlaceOfBusiness(US);

            // Step 5 : Start the clock, execute rules, and calculate the duration
            long startTime = System.nanoTime();
            ruleEngine.executeStateless(request, true);
            long duration1 = System.nanoTime() - startTime;

            // Step 6 : Check that the USPerson property was set correctly by the rule engine
            //assertEquals(TRUE, request.getRuleResultsMap().get("isComplete"));
            assertEquals(TRUE, request.getRuleResultsMap().get("US Person"));

            // Next : Repeat, but ensure US Person is false
            request = createRequest(generator,customerCache);
            request.getCustomer().setPrincipalPlaceOfBusiness(AU);
            startTime = System.nanoTime();
            ruleEngine.executeStateless(request);
            long duration2 = System.nanoTime() - startTime;
            assertEquals(FALSE, request.getRuleResultsMap().get(US_PERSON));

            request = createRequest(generator,customerCache);
            request.getCustomer().setLei(generator.randomLEI());
            startTime = System.nanoTime();
            ruleEngine.executeStateless(request);
            long duration3 = System.nanoTime() - startTime;
            assertEquals(FALSE, request.getRuleResultsMap().get("isValid"));

            // Run a bunch more times just to gauge performance
            long duration4 = 0;
            for (int i = 0; i < REPEAT_LIMIT; i++)
            {
                request = createRequest(generator,customerCache);
                startTime = System.nanoTime();
                ruleEngine.executeStateless(request);
                duration4 = duration4 + System.nanoTime() - startTime;
            }

            duration4 = duration4 / REPEAT_LIMIT;

            double duration1dbl = (double) duration1 / MM;
            double duration2dbl = (double) duration2 / MM;
            double duration3dbl = (double) duration3 / MM;
            double duration4dbl = (double) duration4 / MM;

            System.out.println("#######################################################################");
            System.out.println("#  Rule Execution Timing");
            System.out.println("#  #1. " + duration1dbl + " ms : Validation + US Person Rule");
            System.out.println("#  #2. " + duration2dbl + " ms : Validation + Non-US Person Rule");
            System.out.println("#  #3. " + duration3dbl + " ms : Validation + Incorrect LEI Rule");
            System.out.println("#  Average duration over " + REPEAT_LIMIT + " iterations: " + duration4dbl + " ms");
            System.out.println("#  Validation + US Person or Non US Person rules");
            System.out.println("#######################################################################");

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    private RegulatoryTradeEligibilityRequest createRequest(DataGenerator generator, Cache<String, Customer> customerCache)
    {
        Trade trade = generator.createEquityTradeV1();
        RegulatoryTradeEligibilityRequest request = new RegulatoryTradeEligibilityRequest();
        request.setTrade(trade);
        Customer customer = SerializationUtils.clone(customerCache.get(trade.getCustomer()));
        request.setCustomer(customer);
        return request;
    }
}
