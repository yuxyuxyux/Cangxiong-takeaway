package com.sky.mapper;

import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    List<Employee> findPage(String name);

    @Insert("insert into employee(id_number,name,phone,sex,username,password,create_time,update_time) " +
            "values(#{idNumber},#{name},#{phone},#{sex},#{username},#{password},#{createTime},#{updateTime})")
    void save(Employee employee);


    @Select("select * from employee where id = #{id}")
    Employee showById(Long id);

    void update(Employee employee);

    void updateStatus(Integer status, Integer id);
}
