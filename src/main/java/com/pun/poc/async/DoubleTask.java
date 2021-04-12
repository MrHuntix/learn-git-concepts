package com.pun.poc.async;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DoubleTask extends RecursiveTask<List<Integer>> {
    Logger LOG = LoggerFactory.getLogger(DoubleTask.class);
    List<Integer> numList;
    int numLimit;

    public DoubleTask(List<Integer> numList, int numLimit) {
        this.numList = numList;
        this.numLimit = numLimit;
    }

    @Override
    protected List<Integer> compute() {
        if(CollectionUtils.isNotEmpty(numList) && numList.size()>numLimit) {
            List<ForkJoinTask<List<Integer>>> tasks = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            List<List<Integer>> partitionList = ListUtils.partition(numList, numLimit);
            //LOG.info("created {} partitions. Creating tasks", partitionList.size());
            for(List<Integer> partition:partitionList){
                DoubleTask task = new DoubleTask(partition, numLimit);
                tasks.add(task.fork());
            }
            for(ForkJoinTask<List<Integer>> task: tasks) {
                try {
                    List<Integer> res = task.get();
                    result.addAll(res);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            //LOG.info("{} is processing list of size {}", Thread.currentThread().getName(), numList.size());
            List<Integer> computedResult = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(numList)) {
                for(Integer i:numList) {
                    computedResult.add(i*2);
                }
                return computedResult;
            }
            return computedResult;
        }
    }
}
