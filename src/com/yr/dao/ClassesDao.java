package com.yr.dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.yr.entity.Classes;
/**
 * �־ò�
 * @author Administrator
 */
@Repository
public class ClassesDao {
	private Session session;
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * ��ȡ�͵�ǰ�̰߳󶨵�Session
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	/**
	   * ��ѯ�༶��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public List<Classes> queryClasses() {
		session = getSession();
		String hql = "FROM Classes";
		Query query = session.createQuery(hql);
		List<Classes> list = query.list();
		for (Classes classes : list) {
			System.out.println("ClassesDao���ѯ����\t" + classes.toString());
		}
		return list;
	}
	
	/**
	   * ��������
	 * @param classes
	 */
	public void insert(Classes classes) {
		session = getSession();
		session.save(classes);
		System.out.println("ClassesDAO���������ݳɹ���");
	}
	
	/**
	    * ɾ������
	 */
	public void delete(Integer id) {
		session = getSession();
		Classes classes = session.get(Classes.class, id);
		session.delete(classes);
		System.out.println("ɾ���ɹ�!");
	}
	
	
	/**
	    * ͨ��id���в�ѯ����
	 * @param id
	 * @return
	 */
	public List<Classes> query(Integer id){
		session = getSession();
		String hql ="select c FROM Classes c WHERE c.id = ?";
		Query query = session.createQuery(hql); // ��ѯ����
		query.setInteger(0, id);
		Classes c = (Classes)(query.list().get(0));//����ִ��select��䲢���ؽ����ʵ���б�
		List<Classes>list=new ArrayList<Classes>();
		list.add(c);
		return list;
	}
	
	/**
     * �޸�����
     * ����:��������Ҳ�ܽ����޸�
	 */
	public void update(Classes classes) {
		session = getSession();
		String name = classes.getName();
		Integer id = classes.getId();
		Classes newclasses = session.get(Classes.class, id);
		newclasses.setName(name);
	}
}
