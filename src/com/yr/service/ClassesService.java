package com.yr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClassesDao;
import com.yr.entity.Classes;

/**
 * �߼���
 * @author Administrator
 *
 */
@Service
@Transactional  //�������ʱ����ʹ��������ӽ�ȥ����Ȼ�ᱨ��org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
public class ClassesService {
	@Autowired
	private ClassesDao classesDao;
	
	/**
	   * ��������
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
	   * ��ѯ����
	 */
	public List<Classes> queryClasses() {
		return classesDao.queryClasses();
	}
	
	
	/**
	 * ͨ��id���в�ѯ����
	 * @param id
	 * @return
	 */
	public List<Classes> query(Integer id){
		List<Classes> query = classesDao.query(id);
		return query;
	}
	
	
	/**
	 * ɾ������
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
	 * �޸�����
	 * ����:��������Ҳ�ܽ����޸�
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
