package com.javatpoint.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;  
import com.javatpoint.beans.Project;  
  
public class ProjectDao {  
JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  
public int save(Project p){  
    String sql="insert into project(name,description) values('"+p.getName()+"','"+p.getDescription()+"')";  
    return template.update(sql);  
}  
public int update(Project p){  
    String sql="update project set name='"+p.getName()+"', description='"+p.getDescription()+ "' where id="+p.getId()+"";  
    return template.update(sql);  
}  
public int delete(int id){  
    String sql="delete from project where id="+id+"";  
    return template.update(sql);  
}  
public Project getProjectById(int id){  
    String sql="select * from project where id=?";  
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Project>(Project.class));  
}  
public List<Project> getProjects(){  
    return template.query("select * from project",new RowMapper<Project>(){  
        public Project mapRow(ResultSet rs, int row) throws SQLException {  
        	Project p=new Project();  
            p.setId(rs.getInt(1));  
            p.setName(rs.getString(2));  
            p.setDescription(rs.getString(3));  
            return p;  
        }  
    });  
}  
}  