package com.yr.entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
   * �༶ʵ����
 * @author Administrator
 */
@Entity
@JsonIgnoreProperties(value={"student"})
public class Classes implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id; // �༶id
	private String name; // �༶����
	private Set<Student> student; // ѧ������
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
	    * ��дtoString
	    *  ά����ϵ���������Ҫ��toString�е���һ������ɾ��,��Ȼ�ᵼ��ջ�������,��Ϊ��������toString�����������ѭ������ջ���
	 */
	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name +"]";
	}
}
