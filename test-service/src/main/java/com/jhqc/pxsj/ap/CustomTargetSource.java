package com.jhqc.pxsj.ap;

import org.springframework.aop.TargetSource;

public class CustomTargetSource implements TargetSource {
	private IAction action;
	public CustomTargetSource(IAction action) {
		this.action = action;
	}
	public CustomTargetSource() {
	}

	@Override
	public Class<?> getTargetClass() {
		return action.getClass();
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public Object getTarget() throws Exception {
		return this.action;
	}

	@Override
	public void releaseTarget(Object target) throws Exception {
		
	}

}
