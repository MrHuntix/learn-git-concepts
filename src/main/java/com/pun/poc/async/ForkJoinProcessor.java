package com.pun.poc.async;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ForkJoinProcessor {
    Logger LOG = LoggerFactory.getLogger(ForkJoinProcessor.class);

    @Value("${num.items}")
    int numItems;

    @Value("${num.limit}")
    int numLimit;

    List<Integer> numList;

    @PostConstruct
    public void init() {
        LOG.info("calling process");
        numList = new ArrayList<>(numItems);
        for(int i=0;i<numItems;i++) {
            //some comment to pretend logic
            numList.add(i);
        }
        LOG.info("list initialised");
        long start = System.currentTimeMillis();
        processNormal();

        LOG.info("sync processed {} in {} ms", numItems, (System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        process();
        LOG.info("async processed {} in {} ms", numItems, (System.currentTimeMillis()-start));
    }

    public void process() {
        DoubleTask task = new DoubleTask(numList, numLimit);
        List<Integer> res = task.invoke();
        LOG.info("computed async result size {}", res.size());
    }

    public void processNormal() {
        List<Integer> computedResult = new ArrayList<>();
        for(Integer i:numList) {
            computedResult.add(i*2);
        }

        int sum = computedResult.stream().mapToInt(i->i.intValue()).sum();
        LOG.info("computed sync result size {}", computedResult.size());
        LOG.info("computed sum list {}", sum);
    }

}
