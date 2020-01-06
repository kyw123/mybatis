package com.yr.entity;
import java.io.Serializable;

import javax.persistence.Entity;
/**
 * 学生实体类
 * @author Administrator
 */
@Entity
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id; // 学生id
	private String name;// 学生姓名
	private String sex; // 学生性别
	private Integer age; // 学生年龄
	private Classes classes;// 班级对象
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", classes=" + classes + "]";
	}
}
