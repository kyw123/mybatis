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
 * 持久层
 * @author Administrator
 */
@Repository
public class ClassesDao {
	private Session session;
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 获取和当前线程绑定的Session
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	/**
	   * 查询班级信息数据
	 */
	@SuppressWarnings("unchecked")
	public List<Classes> queryClasses() {
		session = getSession();
		String hql = "FROM Classes";
		Query query = session.createQuery(hql);
		List<Classes> list = query.list();
		for (Classes classes : list) {
			System.out.println("ClassesDao层查询数据\t" + classes.toString());
		}
		return list;
	}
	
	/**
	   * 增加数据
	 * @param classes
	 */
	public void insert(Classes classes) {
		session = getSession();
		session.save(classes);
		System.out.println("ClassesDAO层增加数据成功！");
	}
	
	/**
	    * 删除数据
	 */
	public void delete(Integer id) {
		session = getSession();
		Classes classes = session.get(Classes.class, id);
		session.delete(classes);
		System.out.println("删除成功!");
	}
	
	
	/**
	    * 通过id进行查询数据
	 * @param id
	 * @return
	 */
	public List<Classes> query(Integer id){
		session = getSession();
		String hql ="select c FROM Classes c WHERE c.id = ?";
		Query query = session.createQuery(hql); // 查询方法
		query.setInteger(0, id);
		Classes c = (Classes)(query.list().get(0));//用于执行select语句并返回结果集实体列表。
		List<Classes>list=new ArrayList<Classes>();
		list.add(c);
		return list;
	}
	
	/**
     * 修改数据
     * 步骤:关联对象也能进行修改
	 */
	public void update(Classes classes) {
		session = getSession();
		String name = classes.getName();
		Integer id = classes.getId();
		Classes newclasses = session.get(Classes.class, id);
		newclasses.setName(name);
	}
}
