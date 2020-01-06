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
    *    学生持久层
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
	 * 查询方法*/
	@RequestMapping("/queryStudent")
	@ResponseBody // 表示返回json格式的数据
	public List<Student> queryStudent(){
		studentList = studentService.queryStudent();
		
		  for (Student student : studentList) {
		  System.out.println("contorller+3333333333333\t"+student);
		 }
		 
		return studentList;
	}
	
	
	/**
	   * 新增跳转到新增的页面使用*/
	@RequestMapping(value="/studentjspadd",method=RequestMethod.GET)
	public String input(Map<String,Object> map) {
		Map<String,String> genders  = new HashMap<String,String>();
		genders.put("1","男");
		genders.put("2","女");
		map.put("genders",genders); // 将性别放入到request里面.
		return "addStudent";
	}
	
	/**
	 * 添加学生数据
	 * BindingResult result 对象表示验证的信息必须放在实体类中的后面
	 */
	@ResponseBody
	@RequestMapping(value="/addStudents",method=RequestMethod.POST)
	public String addStudents(Student student,Map<String,Object> map,String className) {
		String mark=null;
		Map<String,String> genders  = new HashMap<String,String>();
		genders.put("1","男");
		genders.put("2","女");
		map.put("genders",genders); // 将性别放入到request里面.
		student.setClasses(classesService.query(Integer.parseInt(className)).get(0));
		studentService.insert(student);
		studentList = queryStudent();
		mark = "success";
		return mark;
	}
	
	/**
	   * 学生的所属班级回显数据
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
	   * 删除数据
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
	   * 修改回显数据
	 */
//	@RequestMapping("{id}/getStudentId")
//	public String getDataUpdate(@PathVariable Integer id,ModelMap modelMap){
//		studentList = studentService.query(id);
//		modelMap.addAttribute("deparList",studentList);
//		return "updateStudent";
//	}

	/**
	   * 修改回显数据
	 */
	@RequestMapping("{id}/getStudentId")
	public String input(@PathVariable("id")Integer id,Map<String, Object> map){
		map.put("deparList", studentService.query(id));
		// 性别
		Map<String, String> genders = new HashMap<String, String>();
		genders.put("1", "男");
		genders.put("0", "女");
		// 放入到request里面
		map.put("genders", genders);
//		student.setClasses(classesService.query(Integer.parseInt(className)).get(0));
		return "updateStudent";
	}
	
	/**
	   * 修改学生数据
	 */
	@RequestMapping("/updateStudents")
	public String updateClasses(Student student,ModelMap modelMap,String className) {
		System.out.println("班级字段\tttttttttttttttttttttttttttttt\t"+className);
		System.out.println("修改的名称:\t111111111111111111111111111111" + student);
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