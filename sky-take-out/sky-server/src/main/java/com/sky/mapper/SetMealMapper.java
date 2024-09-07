package com.sky.mapper;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealMapper {
    List<SetmealVO> findPage(SetmealPageQueryDTO setmealPageQueryDTO);

    void save(SetmealVO setmealVO);

    @Select("select * from setmeal where id = #{id}")
    SetmealDTO findById(Integer id);

    void update(SetmealDTO setmealDTO);

    void delects(List<Integer> ids);

    void updateStatus(Integer status, Integer id);
}
