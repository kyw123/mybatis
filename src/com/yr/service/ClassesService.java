package com.yr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClassesDao;
import com.yr.entity.Classes;

/**
 * 逻辑层
 * @author Administrator
 *
 */
@Service
@Transactional  //添加数据时必须使用事务添加进去。不然会报错。org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
public class ClassesService {
	@Autowired
	private ClassesDao classesDao;
	
	/**
	   * 增加数据
	 * @param classes
	 */
	
	public boolean insert(Classes classes) {
		try{
			System.out.println(classes.getName());
			classesDao.insert(classes);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	   * 查询数据
	 */
	public List<Classes> queryClasses() {
		return classesDao.queryClasses();
	}
	
	
	/**
	 * 通过id进行查询数据
	 * @param id
	 * @return
	 */
	public List<Classes> query(Integer id){
		List<Classes> query = classesDao.query(id);
		return query;
	}
	
	
	/**
	 * 删除数据
	 */
	public boolean delete(Integer id) {
		try{
			classesDao.delete(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * 修改数据
	 * 步骤:关联对象也能进行修改
	 */
	@Transactional
	public boolean update(Classes classes) {
		if("".equals(classes.getName())) {
			return false;
		}else {
			classesDao.update(classes);
			return true;
		}
	}
}
