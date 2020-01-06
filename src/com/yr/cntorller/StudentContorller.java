package com.yr.cntorller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yr.entity.Classes;
import com.yr.entity.Student;
import com.yr.service.ClassesService;
import com.yr.service.StudentService;
/**
    *    ѧ���־ò�
 * @author Administrator
 */
@Controller
public class StudentContorller {
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassesService classesService;
	private List<Student> studentList;
	/**
	 * ��ѯ����*/
	@RequestMapping("/queryStudent")
	@ResponseBody // ��ʾ����json��ʽ������
	public List<Student> queryStudent(){
		studentList = studentService.queryStudent();
		
		  for (Student student : studentList) {
		  System.out.println("contorller+3333333333333\t"+student);
		 }
		 
		return studentList;
	}
	
	
	/**
	   * ������ת��������ҳ��ʹ��*/
	@RequestMapping(value="/studentjspadd",method=RequestMethod.GET)
	public String input(Map<String,Object> map) {
		Map<String,String> genders  = new HashMap<String,String>();
		genders.put("1","��");
		genders.put("2","Ů");
		map.put("genders",genders); // ���Ա���뵽request����.
		return "addStudent";
	}
	
	/**
	 * ���ѧ������
	 * BindingResult result �����ʾ��֤����Ϣ�������ʵ�����еĺ���
	 */
	@ResponseBody
	@RequestMapping(value="/addStudents",method=RequestMethod.POST)
	public String addStudents(Student student,Map<String,Object> map,String className) {
		String mark=null;
		Map<String,String> genders  = new HashMap<String,String>();
		genders.put("1","��");
		genders.put("2","Ů");
		map.put("genders",genders); // ���Ա���뵽request����.
		student.setClasses(classesService.query(Integer.parseInt(className)).get(0));
		studentService.insert(student);
		studentList = queryStudent();
		mark = "success";
		return mark;
	}
	
	/**
	   * ѧ���������༶��������
	 * @param student
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addClassValue", method = RequestMethod.POST)
	public List<Classes> addClass()throws Exception{
		return classesService.queryClasses();
	}
	
	/**
	   * ɾ������
	 */
	@ResponseBody
	@RequestMapping(value="deleteStudent/{id}",method=RequestMethod.POST)
	public String deleteStudent(@PathVariable Integer id,ModelMap modelMap) {
		System.out.println(id+"---------------");
		boolean bool = studentService.delete(id);
		String mark = null;
		if(bool) {
			studentList = queryStudent();
			modelMap.addAttribute("deparList", studentList);
			mark="success";
		}else{
			mark="error";
		}
		return mark;
	}
	
	/**
	   * �޸Ļ�������
	 */
//	@RequestMapping("{id}/getStudentId")
//	public String getDataUpdate(@PathVariable Integer id,ModelMap modelMap){
//		studentList = studentService.query(id);
//		modelMap.addAttribute("deparList",studentList);
//		return "updateStudent";
//	}

	/**
	   * �޸Ļ�������
	 */
	@RequestMapping("{id}/getStudentId")
	public String input(@PathVariable("id")Integer id,Map<String, Object> map){
		map.put("deparList", studentService.query(id));
		// �Ա�
		Map<String, String> genders = new HashMap<String, String>();
		genders.put("1", "��");
		genders.put("0", "Ů");
		// ���뵽request����
		map.put("genders", genders);
//		student.setClasses(classesService.query(Integer.parseInt(className)).get(0));
		return "updateStudent";
	}
	
	/**
	   * �޸�ѧ������
	 */
	@RequestMapping("/updateStudents")
	public String updateClasses(Student student,ModelMap modelMap,String className) {
		System.out.println("�༶�ֶ�\tttttttttttttttttttttttttttttt\t"+className);
		System.out.println("�޸ĵ�����:\t111111111111111111111111111111" + student);
		student.setClasses(classesService.query(Integer.parseInt(className)).get(0));
		System.out.println(student.getClasses() +"\t333333333333333333333333333333333333333");
		boolean bool = studentService.update(student);
		if(bool) {
			studentList=queryStudent();
			modelMap.addAttribute("deparList", studentList);
			return "student";
		}else {
			modelMap.addAttribute("err", 2);
			return "updateStudent";
		}
	}
}