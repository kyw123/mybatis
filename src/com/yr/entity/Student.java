package com.yr.entity;
import java.io.Serializable;

import javax.persistence.Entity;
/**
 * ѧ��ʵ����
 * @author Administrator
 */
@Entity
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id; // ѧ��id
	private String name;// ѧ������
	private String sex; // ѧ���Ա�
	private Integer age; // ѧ������
	private Classes classes;// �༶����
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
