package com.jhqc.pxsj.rxjava;

import java.util.List;

import com.google.common.collect.Lists;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.functions.Action1;
import rx.functions.Func1;

public class Main {
	public static void main(String[] args) {
		OnSubscribe<?> os;
		Observable<String> o = Observable.just("helloworld1", "helloworld2");
		o.subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.println("the result is " + t);
			}
		});
		List<Course> list1 = Lists.newArrayList(new Course("s1c1", 11),new Course("s1c2", 12));
		Student s1 = new Student("s1", list1);
		List<Course> list2 = Lists.newArrayList(new Course("s2c1", 21),new Course("s2c2", 22));
		Student s2 = new Student("s2", list2);
		List<Student> students = Lists.newArrayList(s1, s2);
		Observable<Course> o2 = Observable.from(students)
				.flatMap(new Func1<Student, Observable<? extends Course>>() {
					@Override
					public Observable<? extends Course> call(Student t) {
						return Observable.from(t.getCourses());
					}
		});
		o2.subscribe(new Action1<Course>() {

			@Override
			public void call(Course t) {
				System.out.println(t.getCourseName());
			}
		});
	}

}

class Student {
	private String name;
	private List<Course> courses;
	public Student() {}
	public Student(String name, List<Course> courses) {
		this.name = name;
		this.courses = courses;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}

class Course {
	private String courseName;
	private int courseScore;
	
	public Course() {}
	public Course(String courseName, int courseScore) {
		this.courseName = courseName;
		this.courseScore = courseScore;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(int courseScore) {
		this.courseScore = courseScore;
	}
	
}
