package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public Result<PageResult> page(@ModelAttribute DishPageQueryDTO dishPageQueryDTO) {
        Page<DishVO> dishPage = dishService.findPage(dishPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(dishPage.getTotal());
        pageResult.setRecords(dishPage.getResult());
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {

        dishService.save(dishDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishDTO> getById(@PathVariable Long id) {
        DishDTO dishDTO = dishService.findById(id);

        return Result.success(dishDTO);
    }

    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.update(dishDTO);
        return Result.success();
    }

    @DeleteMapping()
    public Result delects(@RequestParam List<Integer> ids) {
        dishService.delects(ids);
        return Result.success();
    }

    @PostMapping("status/{status}")
    public Result updateStatus(@PathVariable Integer status, @RequestParam Integer id) {
        dishService.updateStatus(status, id);
        return Result.success();
    }
//    http://localhost/api/dish/list?categoryId=16

    @GetMapping("list")
    public Result<List<Dish>> list(@RequestParam Integer categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

}
