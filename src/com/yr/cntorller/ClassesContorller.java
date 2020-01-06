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
    * �༶�־ò�
 * @author Administrator
 */
@Controller
public class ClassesContorller {
	@Autowired
	private ClassesService clasesService;
	private List<Classes> classesList;
	
	/**
	 * ��ѯ����*/
	@ResponseBody // ��ʾ����json��ʽ������
	@RequestMapping("/queryClasses")
	public List<Classes> queryClasses(){
		classesList = clasesService.queryClasses();
		/*
		 * for (Classes classes : classesList) {
		 * System.out.println("controller��ѯ������\t"+classes); }
		 */
		return classesList;
	}
	
	/**
	 * ����,��ת������ҳ��ʹ��*/
	@RequestMapping(value="/classesSkip",method=RequestMethod.GET)
	public String input(Map<String,Object> map) {
		return "addClasses";
	}
	
	
	/**
	 * �������
	 */
	@ResponseBody
	@RequestMapping(value = "/addClass", method = RequestMethod.POST)
	public String addClass(Classes classes, ModelMap modelMap)throws Exception{  
		System.out.println(classes.toString()); //��ӡ�༶��toString����
		boolean bool = clasesService.insert(classes);
		System.out.println("bool��ֵΪ:\t"+bool);
		String mark = null;
		if (bool) {
			classesList = queryClasses();
			modelMap.addAttribute("deparList", classesList);
			mark = "success";
		} else {
			mark = "error";
		}
		return mark;//ת����������ĵ�ַ����Ϊ�ض����ĵ�ַ��������֮ǰ��������ݡ�ת���ѯҳ��
	}
	
	
	/**
	   * ɾ������
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
	   * �޸Ļ�������
	 */
	@RequestMapping("{id}/getClassId")
	public String getDataUpdate(@PathVariable Integer id,ModelMap modelMap){
		classesList = clasesService.query(id);
		modelMap.addAttribute("deparList",classesList);
		return "updateClasses";
	}
	
	
	/**
	   * �޸�����
	 */
	@RequestMapping("/updateClass")
	public String updateClasses(Classes classes,ModelMap modelMap) {
		System.out.println("�༶������:\t"+classes);
		boolean bool = clasesService.update(classes);
		if(bool) {
			classesList=queryClasses();
			modelMap.addAttribute("deparList", classesList);
			return "classes";
		}else {
			modelMap.addAttribute("error", 2);// �޸�ʧ�ܷ��ص��޸�ҳ��
			return "updateClasses";
		}
	}
}