package com.javatpoint.controllers;   
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;   
import com.javatpoint.beans.Project;  
import com.javatpoint.dao.ProjectDao;  
@Controller  
public class ProjectController {  
    @Autowired  
   ProjectDao dao;//will inject dao from xml file  
      
    /*It displays a form to input data, here "command" is a reserved request attribute 
     *which is used to display object data into form 
     */  
    @RequestMapping("/projectform")  
    public String showform(Model m){  
    	m.addAttribute("command", new Project());
    	return "projectform"; 
    }  
    /*It saves object into database. The @ModelAttribute puts request data 
     *  into model object. You need to mention RequestMethod.POST method  
     *  because default request is GET*/  
    @RequestMapping(value="/save",method = RequestMethod.POST)  
    public String save(@ModelAttribute("project") Project pro)
    {  
        dao.save(pro);  
        return "redirect:/viewpro";//will redirect to viewemp request mapping  
    }  
    /* It provides list of employees in model object */  
    @RequestMapping("/viewemp")  
    public String viewemp(Model m){  
        List<Project> list=dao.getProjects();  
        m.addAttribute("list",list);
        return "viewpro";  
    }  
    /* It displays object data into form for the given id.  
     * The @PathVariable puts URL data into variable.*/  
    @RequestMapping(value="/editpro/{id}")  
    public String edit(@PathVariable int id, Model m){  
        Project pro=dao.getProjectById(id);  
        m.addAttribute("command",pro);
        return "proeditform";  
    }  
    /* It updates model object. */  
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public String editsave(@ModelAttribute("emp") Project pro){  
        dao.update(pro);  
        return "redirect:/viewpro";  
    }  
    /* It deletes record for the given id in URL and redirects to /viewemp */  
    @RequestMapping(value="/deletepro/{id}",method = RequestMethod.GET)  
    public String delete(@PathVariable int id){  
        dao.delete(id);  
        return "redirect:/viewpro";  
    }   
}  