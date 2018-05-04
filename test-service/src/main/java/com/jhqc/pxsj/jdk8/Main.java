package com.jhqc.pxsj.jdk8;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.jhqc.pxsj.jdk8.Task.TaskStatus;

public class Main {
	
	public static void main(String[] args) {
		
		String a = "12345";
		String b = "123a45";
		System.out.println(StringUtils.isNumeric(a));
		System.out.println(StringUtils.isNumeric(b));
		
		
		System.out.println(RandomStringUtils.randomAlphanumeric(16));
		System.out.println(RandomStringUtils.randomNumeric(8));
		
		String temp = "abc123好";
		byte[] base = Base64.getEncoder().encode(temp.getBytes());
		System.out.println(new String(base));
		byte[] baseNew = Base64.getDecoder().decode(base);
		System.out.println(new String(baseNew));
		String result1 = Base64.getUrlEncoder().encodeToString(temp.getBytes());
		System.out.println(result1);
		List<Task> list = Arrays.asList(
				new Task(TaskStatus.OPEN, 1),
				new Task(TaskStatus.CLOSED, 2),
				new Task(TaskStatus.OPEN, 3),
				new Task(TaskStatus.CLOSED,4));
		int total = list.stream().filter(task -> task.getPoint() > 1).mapToInt( Task::getPoint ).sum();
		System.out.println(total);
		list.parallelStream().parallel();
		
		/*System.out.println(total);
		Optional<String> ot = Optional.of("Tom");
		System.out.println(ot.get());
		
		List<String> list2 = Arrays.asList("1", "2", "3");
		list.forEach(e -> {
			if (e.equals("2")) {
				System.out.println("this is 2");
			} else {
				System.out.println("this is not 2");
			}
		});*/
		//list.sort( (a,b) -> a.compareTo(b) );
	}

}

class Person {
	private Task task;
	
	public Person(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
class Task {
	public static  enum TaskStatus{
		OPEN,CLOSED
	}
	private TaskStatus taskStatus;
	private int point;
	public Task(){}
	public Task(TaskStatus taskStatus,int point) {
		this.taskStatus = taskStatus;
		this.point = point;
	}
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Task [taskStatus=" + taskStatus + ", point=" + point + "]";
	}
	
}
