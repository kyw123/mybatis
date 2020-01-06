package com.yr.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yr.entity.Classes;
import com.yr.entity.Student;
/**
   * 学生的持久层
 * @author Administrator
 */
@Repository
public class StudentDao {
	private Session session;
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 得到Session
	 * 
	 * @return
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	   * 查询数据
	 */
	public List<Student> queryStudent(){
		session = getSession();
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Student> resultList = query.list();
		for (Student student : resultList) {
			System.out.println("DAO层学生的查询结果：\t"+student);
		}
		System.out.println("查询成功!");
		return resultList;
	}
	
	

	/**
	   * 插入数据
	 * @param student
	 */
	public void insert(Student student) {
		session = getSession();
		session.save(student);
		System.out.println("Dao数据层插入成功！");
	}
	
	
	
	
	/**
	   * 删除数据
	 */
	public void delete(Integer id) {
		session = getSession();
		try {
			Student student = session.get(Student.class, id);
			session.delete(student);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	   * 修改数据
	 */
	public void update(Student student){
		session = getSession();
		String name=student.getName();
		Integer age = student.getAge();
		String sex = student.getSex();
		Classes classes = student.getClasses();
		Integer id = student.getId();
		System.out.println("修改数据\t:"+student.toString());
		
		Student newStudent = session.get(Student.class,id);
		newStudent.setName(name);
		newStudent.setAge(age);
		newStudent.setSex(sex);
		newStudent.setClasses(classes);
	}
	
	/**
	    * 通过id进行查询数据
	 * @param id
	 * @return
	 */
	public List<Student> query(Integer id){
		session = getSession();
		String jpql = "select s FROM Student s WHERE s.id = ?";
		Query query = session.createQuery(jpql); // 查询方法
		query.setInteger(0, id);
		Student student = (Student)(query.list().get(0));//用于执行select语句并返回结果集实体列表。
		List<Student>  list = new ArrayList<Student>();
		list.add(student);
		for (Student student2 : list) {
			System.out.println(student2);
		}
		return list;
	}
}