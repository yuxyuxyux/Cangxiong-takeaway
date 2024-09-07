package com.sky.mapper;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    List<DishVO> findPage(DishPageQueryDTO dishPageQueryDTO);

    void save(DishDTO dishDTO);

    DishDTO findById(Long id);

    void update(DishDTO dishDTO);

    void delects(List<Integer> ids);

    void updateStatus(Integer status, Integer id);

    List<Dish> list(Integer categoryId);
}
