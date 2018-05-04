package com.jhqc.pxsj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;


@Service
public class TestService {
	
	public String getName(){
		return "test name";
	}
	private static int count = 100;
	private static int money = 100;
	static List<Person> list = new ArrayList<Person>(count);
	
	static {
		for (int i = 0; i < count; i++) {
			list.add(new Person(i, money));
		}
	}
	public static void main(String[] args) {
		ImageIO  ii;
		for (int i = 0; i < 1000000; i++) {
			for (int j = 0; j < count; j++) {
				giveMoney(j, list);
			}
		}
		
		
		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				if (o1.getMoney() < o2.getMoney()) {
					return -1;
				} else if (o1.getMoney() > o2.getMoney()) {
					return 1;
				}
				return 0;
			}
		});
		
		int sum = 0;
		for (int i = 0; i < count; i++) {
			System.out.println(i + "=>" + list.get(i).getMoney());
			sum += list.get(i).getMoney();
		}
		System.out.println(sum);
	}
	
	private static void giveMoney(int personIndex, List<Person> list) {
		Person person = list.get(personIndex);
		if (person.getMoney() > 0) {
			person.setMoney(person.getMoney() - 1);
			
			int randIndex = new Random().nextInt(99);
			if (randIndex < personIndex) {
				Person person2 = list.get(randIndex);
				person2.setMoney(person2.getMoney() + 1);
			} else {
				Person person2 = list.get(randIndex + 1);
				person2.setMoney(person2.getMoney() + 1);
			}
		}
		
	}

}

class Person {
	private int id;
	private int money;
	public Person() {}
	public Person(int id, int money) {
		this.id = id;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
