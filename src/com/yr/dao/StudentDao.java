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
   * ѧ���ĳ־ò�
 * @author Administrator
 */
@Repository
public class StudentDao {
	private Session session;
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * �õ�Session
	 * 
	 * @return
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	   * ��ѯ����
	 */
	public List<Student> queryStudent(){
		session = getSession();
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Student> resultList = query.list();
		for (Student student : resultList) {
			System.out.println("DAO��ѧ���Ĳ�ѯ�����\t"+student);
		}
		System.out.println("��ѯ�ɹ�!");
		return resultList;
	}
	
	

	/**
	   * ��������
	 * @param student
	 */
	public void insert(Student student) {
		session = getSession();
		session.save(student);
		System.out.println("Dao���ݲ����ɹ���");
	}
	
	
	
	
	/**
	   * ɾ������
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
	   * �޸�����
	 */
	public void update(Student student){
		session = getSession();
		String name=student.getName();
		Integer age = student.getAge();
		String sex = student.getSex();
		Classes classes = student.getClasses();
		Integer id = student.getId();
		System.out.println("�޸�����\t:"+student.toString());
		
		Student newStudent = session.get(Student.class,id);
		newStudent.setName(name);
		newStudent.setAge(age);
		newStudent.setSex(sex);
		newStudent.setClasses(classes);
	}
	
	/**
	    * ͨ��id���в�ѯ����
	 * @param id
	 * @return
	 */
	public List<Student> query(Integer id){
		session = getSession();
		String jpql = "select s FROM Student s WHERE s.id = ?";
		Query query = session.createQuery(jpql); // ��ѯ����
		query.setInteger(0, id);
		Student student = (Student)(query.list().get(0));//����ִ��select��䲢���ؽ����ʵ���б�
		List<Student>  list = new ArrayList<Student>();
		list.add(student);
		for (Student student2 : list) {
			System.out.println(student2);
		}
		return list;
	}
}