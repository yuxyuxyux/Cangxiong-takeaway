package com.sky.mapper;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> findPage(CategoryPageQueryDTO categoryPageQueryDTO);

    @Insert("INSERT INTO category(name,sort,type,create_time,update_time) values(#{name},#{sort},#{type},#{createTime},#{updateTime}) ")
    void saveCategory(Category category);

    @Update("update category set status = #{status} , update_time = now() where id = #{id}")
    void updateStatus(Integer status, Integer id);

    @Delete("delete from category where id = #{id}")
    void deleteCategory(Integer id);

    @Update("update category set name = #{name},sort = #{sort},update_time = now() where id = #{id}")
    void update(Category category);

    @Select("select * from category where type = #{type}")
    List<Category> findAll(Integer type);
}
