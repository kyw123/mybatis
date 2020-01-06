package com.yr.cntorller;
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
import com.yr.service.ClassesService;
/**
    * 班级持久层
 * @author Administrator
 */
@Controller
public class ClassesContorller {
	@Autowired
	private ClassesService clasesService;
	private List<Classes> classesList;
	
	/**
	 * 查询方法*/
	@ResponseBody // 表示返回json格式的数据
	@RequestMapping("/queryClasses")
	public List<Classes> queryClasses(){
		classesList = clasesService.queryClasses();
		/*
		 * for (Classes classes : classesList) {
		 * System.out.println("controller查询方法：\t"+classes); }
		 */
		return classesList;
	}
	
	/**
	 * 新增,跳转到新增页面使用*/
	@RequestMapping(value="/classesSkip",method=RequestMethod.GET)
	public String input(Map<String,Object> map) {
		return "addClasses";
	}
	
	
	/**
	 * 添加数据
	 */
	@ResponseBody
	@RequestMapping(value = "/addClass", method = RequestMethod.POST)
	public String addClass(Classes classes, ModelMap modelMap)throws Exception{  
		System.out.println(classes.toString()); //打印班级的toString方法
		boolean bool = clasesService.insert(classes);
		System.out.println("bool的值为:\t"+bool);
		String mark = null;
		if (bool) {
			classesList = queryClasses();
			modelMap.addAttribute("deparList", classesList);
			mark = "success";
		} else {
			mark = "error";
		}
		return mark;//转发后浏览器的地址栏变为重定向后的地址，不共享之前请求的数据。转向查询页面
	}
	
	
	/**
	   * 删除数据
	 */
	@ResponseBody
	@RequestMapping(value="deleteClasses/{id}",method=RequestMethod.POST)
	public String deleteClass(@PathVariable Integer id, ModelMap modelMap) {
		boolean bool = clasesService.delete(id);
		String mark = null;
		if(bool) {
			classesList = queryClasses();
			modelMap.addAttribute("deparList", classesList);
			mark="success";
		}else {
			mark="error";
		}
		return mark;
	}
	
	/**
	   * 修改回显数据
	 */
	@RequestMapping("{id}/getClassId")
	public String getDataUpdate(@PathVariable Integer id,ModelMap modelMap){
		classesList = clasesService.query(id);
		modelMap.addAttribute("deparList",classesList);
		return "updateClasses";
	}
	
	
	/**
	   * 修改数据
	 */
	@RequestMapping("/updateClass")
	public String updateClasses(Classes classes,ModelMap modelMap) {
		System.out.println("班级的名称:\t"+classes);
		boolean bool = clasesService.update(classes);
		if(bool) {
			classesList=queryClasses();
			modelMap.addAttribute("deparList", classesList);
			return "classes";
		}else {
			modelMap.addAttribute("error", 2);// 修改失败返回到修改页面
			return "updateClasses";
		}
	}
}