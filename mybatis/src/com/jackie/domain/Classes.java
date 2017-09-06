package com.jackie.domain;

import java.util.List;

public class Classes {
	//定义实体类中的属性，与class表中的字段一一对应
	private int id;//id==>c_id
	private String name;//name==>c_name

	/**
	 * class表中有一个个teacher_id字段，所以在classes类中定义了一个teacher属性
	 * 用于维护teacher和class之间的一对一关系，通过这个teacher属性就可以知道这个班级是由哪个老师负责的
	 * @return
	 */
	private Teacher teacher;
	//使用一个List<Student>集合属性表示班级拥有的学生
	private List<Student> students;
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name + ", teacher=" + teacher
				+ ", students=" + students + "]";
	}
	 
}

