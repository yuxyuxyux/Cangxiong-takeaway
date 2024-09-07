package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {
    Page<SetmealVO> findAllPage(SetmealPageQueryDTO setmealPageQueryDTO);

    void save(SetmealVO setmealVO);

    SetmealDTO findById(Integer id);

    void update(SetmealDTO setmealDTO);

    void delects(List<Integer> ids);

    void updateStatus(Integer status, Integer id);
}
