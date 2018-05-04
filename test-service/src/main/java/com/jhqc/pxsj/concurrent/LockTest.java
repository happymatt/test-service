package com.jhqc.pxsj.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class LockTest {
	
	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		lock.tryLock();
		lock.lock();
		ReentrantReadWriteLock rrWriteLock;
		ReadLock rlLock;
		WriteLock wLock;
		ReadWriteLock rwl;
		AbstractQueuedSynchronizer aqs;
		lock.newCondition().await();
	}

}

class LockCache {
	private Map<String, String> cacheMap = new HashMap<String, String>();
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public String getValue(String key) {
		ReadLock rl = lock.readLock();
		rl.lock();
		try {
			return cacheMap.get(key);
		} finally {
			rl.unlock();
		}
	}
	public void setValue(String key, String value) {
		
	}
	
	
}
