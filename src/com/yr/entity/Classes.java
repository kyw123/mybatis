package com.yr.entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
   * 班级实体类
 * @author Administrator
 */
@Entity
@JsonIgnoreProperties(value={"student"})
public class Classes implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id; // 班级id
	private String name; // 班级名称
	private Set<Student> student; // 学生对象
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
	public Set<Student> getStudent() {
		return student;
	}
	public void setStudent(Set<Student> student) {
		this.student = student;
	}
	/**
	    * 重写toString
	    *  维护关系不在这端需要将toString中的另一个集合删除,不然会导致栈溢出报错,因为两方都有toString互相调陷入死循环导致栈溢出
	 */
	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name +"]";
	}
}
