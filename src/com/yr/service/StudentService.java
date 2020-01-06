package com.yr.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yr.dao.StudentDao;
import com.yr.entity.Student;
/**
 * 逻辑层
 * @author Administrator
 */
@Service
@Transactional  //添加数据时必须使用事务添加进去。不然会报错。org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	/**
	   * 插入数据
	 * @param student
	 */
	public void insert(Student student) {
		studentDao.insert(student);
	}
	
	/**
	   * 删除数据
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
	    * 修改数据
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
	 * 通过id进行查询数据
	 * @param id
	 * @return
	 */
	public List<Student> query(Integer id){
		List<Student> query = studentDao.query(id);
		return query;
	}
	
	
	
	/**
	 * 查询数据
	 */
	public List<Student> queryStudent(){
		return studentDao.queryStudent();
	} 
}
