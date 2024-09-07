package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public Page<SetmealVO> findAllPage(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        List<SetmealVO> setmealList = setMealMapper.findPage(setmealPageQueryDTO);
//        setmealList.forEach(setmealVO -> setmealVO.setSetmealDishes(dishMapper.findById());
        Page<SetmealVO> setmeals = (Page<SetmealVO>) setmealList;
        return setmeals;
    }

    @Transactional
    @Override
    public void save(SetmealVO setmealVO) {
        setMealMapper.save(setmealVO);
        List<SetmealDish> setmealDishes = setmealVO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealVO.getId());
            setmealDishMapper.insert(setmealDish);
        });


    }

    @Override
    public SetmealDTO findById(Integer id) {
        SetmealDTO setmealDTO = setMealMapper.findById(id);
        setmealDTO.setSetmealDishes(setmealDishMapper.findBySetMealId(id));
        return setmealDTO;
    }

    @Transactional
    @Override
    public void update(SetmealDTO setmealDTO) {
        setMealMapper.update(setmealDTO);
        setmealDishMapper.delectBysetmealDTOId(setmealDTO.getId());

        setmealDTO.getSetmealDishes().forEach(setmealDish -> setmealDish.setSetmealId(setmealDTO.getId()));
        setmealDTO.getSetmealDishes().forEach(setmealDish -> setmealDishMapper.insert(setmealDish));
    }

    @Transactional
    @Override
    public void delects(List<Integer> ids) {
        setMealMapper.delects(ids);
        setmealDishMapper.delectBysetmealIds(ids);
    }

    @Override
    public void updateStatus(Integer status, Integer id) {
        setMealMapper.updateStatus(status, id);
    }
}
