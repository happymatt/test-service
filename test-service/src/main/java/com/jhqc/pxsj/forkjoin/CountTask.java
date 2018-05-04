package com.jhqc.pxsj.forkjoin;

import java.math.BigInteger;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = -3611254198265061729L;
	
	static long max = Integer.MAX_VALUE;

	public static final long threshold = (max * 50) /8;
	private long start;
	private long end;

	public CountTask(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long sum = 0;

		// 如果任务足够小就计算任务
		boolean canCompute = (end - start) <= threshold;
		if (canCompute) {
			for (long i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// 如果任务大于阈值，就分裂成两个子任务计算
			long middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);

			// 执行子任务
			leftTask.fork();
			rightTask.fork();

			// 等待任务执行结束合并其结果
			long leftResult = leftTask.join();
			long rightResult = rightTask.join();

			// 合并子任务
			sum = leftResult + rightResult;

		}

		return sum;
	}

	final static ForkJoinPool pool = new ForkJoinPool();
	public static void main(String[] args) {
		
		ExecutorCompletionService<?> ecs;
		BigInteger bigInteger = BigInteger.ONE;
		bigInteger.nextProbablePrime();
		int pCount = Runtime.getRuntime().availableProcessors();
		ForkJoinWorkerThreadFactory factory;
		System.out.println(pCount);
		long st1 = System.currentTimeMillis();
		long m = Integer.MAX_VALUE;
		testForkJoin(m * 50);
		System.out.println("forkjoin总计耗时" + (System.currentTimeMillis() - st1) + "毫秒");
		
		long st2 = System.currentTimeMillis();
		testNormal(m * 50);
		System.out.println("normal总计耗时" + (System.currentTimeMillis() - st2) + "毫秒");
	}

	public static long testNormal(long m) {
		long sum = 0;
		for (long i = 0; i < m + 1; i++) {
			sum += i;
		}
		System.out.println(sum);
		return sum;
	}
	public static Long testForkJoin(long m) {
		ForkJoinPool forkjoinPool = new ForkJoinPool();
		// 生成一个计算任务，计算1+2+3+4
		CountTask task = new CountTask(1, m);

		// 执行一个任务
		Future<Long> result = forkjoinPool.submit(task);

		try {
			System.out.println(result.get());
			return result.get();
		} catch (Exception e) {
			System.out.println(e);
			return -1L;
		}
	}

}
