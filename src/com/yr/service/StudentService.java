package com.yr.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yr.dao.StudentDao;
import com.yr.entity.Student;
/**
 * �߼���
 * @author Administrator
 */
@Service
@Transactional  //�������ʱ����ʹ��������ӽ�ȥ����Ȼ�ᱨ��org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	/**
	   * ��������
	 * @param student
	 */
	public void insert(Student student) {
		studentDao.insert(student);
	}
	
	/**
	   * ɾ������
	 */
	public boolean delete(Integer id) {
		System.out.println("-----------------"+id);
		try{
			studentDao.delete(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	    * �޸�����
	 */
	public boolean update(Student student) {
		if("".equals(student.getName())) {
			return false;
		}else {
			studentDao.update(student);
			return true;
		}
	}
	
	/**
	 * ͨ��id���в�ѯ����
	 * @param id
	 * @return
	 */
	public List<Student> query(Integer id){
		List<Student> query = studentDao.query(id);
		return query;
	}
	
	
	
	/**
	 * ��ѯ����
	 */
	public List<Student> queryStudent(){
		return studentDao.queryStudent();
	} 
}
