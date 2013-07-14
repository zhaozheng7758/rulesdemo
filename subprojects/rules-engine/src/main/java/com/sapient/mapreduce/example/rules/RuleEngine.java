package com.sapient.mapreduce.example.rules;

import java.util.Collection;
import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class RuleEngine
{
    private KnowledgeBase       kbase;

    private static final String DIALECT_COMPILER_PROP = "drools.dialect.java.compiler";
    private static final String JANINO                = "JANINO";

    public void compileResource(String resourceName) throws RuntimeException
    {
        ResourceType resourceType = ResourceType.determineResourceType(resourceName);
        Properties props = new Properties();
        props.setProperty(DIALECT_COMPILER_PROP, JANINO);
        KnowledgeBuilderConfiguration kconfig = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(props, RuleEngine.class.getClassLoader());
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(kconfig);
        Resource drlResource = ResourceFactory.newClassPathResource(resourceName, RuleEngine.class);
        kbuilder.add(drlResource, resourceType);
        if (kbuilder.hasErrors())
        {
            System.out.println(kbuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile resource " + resourceName);
        }
        else
        {
            final Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
            synchronized (this)
            {
                if (null == kbase)
                {
                    kbase = KnowledgeBaseFactory.newKnowledgeBase();
                }
            }
            kbase.addKnowledgePackages(pkgs);
            /*
             * For some reason, this next statement makes the first rule execution run faster.
             * Appears to be some sort of lazy initialization happening that only occurs when
             * an object is inserted and rules are fired;
             */
            kbase.newStatelessKnowledgeSession().execute(new Object());
        }
    }

    public void executeStateless(Object object)
    {
        executeStateless(object, false);
    }

    public void executeStateless(Object object, boolean enableDebug)
    {
        final StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        if (enableDebug)
        {
            ksession.addEventListener(new DebugAgendaEventListener());
            ksession.addEventListener(new DebugWorkingMemoryEventListener());
        }
        ksession.execute(object);
    }
}
