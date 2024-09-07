package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.mapper.FlavorMapper;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private FlavorMapper flavorMapper;

    @Override
    public Page<DishVO> findPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        List<DishVO> list = dishMapper.findPage(dishPageQueryDTO);
        Page<DishVO> dishPage = (Page<DishVO>) list;
        return dishPage;
    }
    @Transactional
    @Override
    public void save(DishDTO dishDTO) {
        dishMapper.save(dishDTO);
        long id = Math.toIntExact(dishDTO.getId());
        if (dishDTO.getFlavors() == null) {
            return;
        }
        dishDTO.getFlavors().forEach(s->s.setDishId(id));
        flavorMapper.save(dishDTO.getFlavors());
    }

    @Transactional
    @Override
    public DishDTO findById(Long id) {
        DishDTO dishDTO = dishMapper.findById(id);

        return dishDTO;
    }

    @Transactional
    @Override
    public void update(DishDTO dishDTO) {
        dishMapper.update(dishDTO);
        flavorMapper.deleteByDishId(dishDTO.getId());
        if (dishDTO.getFlavors() == null||dishDTO.getFlavors().isEmpty()) {
            return;
        }
        dishDTO.getFlavors().forEach(s->s.setDishId(dishDTO.getId()));
        flavorMapper.save(dishDTO.getFlavors());
    }

    @Override
    public void delects(List<Integer> ids) {
        dishMapper.delects(ids);
    }

    @Override
    public void updateStatus(Integer status, Integer id) {
        dishMapper.updateStatus(status,id);
    }

    @Override
    public List<Dish> list(Integer categoryId) {
        List<Dish> list = dishMapper.list(categoryId);
        return list;
    }
}
