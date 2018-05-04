package com.jhqc.pxsj.ribbon;

public class NameAndAgeImpl implements NameAndAge {
	

	@Override
	public String getName() {
		return "defaultName";
	}

	@Override
	public int getAge() {
		return 15;
	}

}
