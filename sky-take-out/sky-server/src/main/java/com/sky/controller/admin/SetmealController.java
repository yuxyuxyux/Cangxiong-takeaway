package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    private SetMealService setMealService;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        Page<SetmealVO> setmeals = setMealService.findAllPage(setmealPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setRecords(setmeals.getResult());
        pageResult.setTotal(setmeals.getTotal());

        return Result.success(pageResult);
    }

    @PostMapping()
    public Result save(@RequestBody SetmealVO setmealVO) {
        setMealService.save(setmealVO);
        setmealVO.getSetmealDishes().forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealVO.getId());
            setmealDishMapper.insert(setmealDish);
        });
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealDTO> findById(@PathVariable("id") Integer id) {
        SetmealDTO setmealDTO = setMealService.findById(id);

        return Result.success(setmealDTO);
    }

    @PutMapping()
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info(setmealDTO.toString());
        setMealService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, @RequestParam Integer id) {
        setMealService.updateStatus(status, id);
        return Result.success();
    }

    @DeleteMapping
    public Result delects(@RequestParam List<Integer> ids) {
        if (ids.isEmpty()){
            return Result.success();
        }
        setMealService.delects(ids);
        return Result.success();
    }


}
