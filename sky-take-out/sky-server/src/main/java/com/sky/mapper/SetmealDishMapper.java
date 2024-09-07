package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    void insert(SetmealDish setmealDish);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> findBySetMealId(Integer id);

    void delectBysetmealDTOId(Long id);


    void delectBysetmealIds(List<Integer> ids);
}
