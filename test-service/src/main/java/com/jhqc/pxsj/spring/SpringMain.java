package com.jhqc.pxsj.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javassist.bytecode.StackMap.Walker;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

public class SpringMain {
	private Map<String, List<String>> map;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Locale lc = Locale.CHINA;
		System.out.println(lc.getLanguage());
		ResourceBundle rb;
		Man man = new Man();
		man.walk();
		ResolvableType rt2 = ResolvableType.forInstance(man);
		System.out.println(rt2);
		
		ApplicationEvent aEvent;
		ApplicationEventPublisher aepApplicationEventPublisher;
		ApplicationEventMulticaster aeMulticaster;
		AutowiredAnnotationBeanPostProcessor aabPostProcessor;
		
		ResolvableType rt = ResolvableType.forField(SpringMain.class.getDeclaredField("map"));
		
		
	}

}

interface Person {
	void walk();
}
class Man implements Person {

	@Override
	public void walk() {
		System.out.println("man is walking");
	}
	
}