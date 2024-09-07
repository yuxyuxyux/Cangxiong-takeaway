package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CateService categoryService;

    @GetMapping("/page")
    public Result<PageResult> categoryList(CategoryPageQueryDTO categoryPageQueryDTO) {
        Page<Category> page = categoryService.findPage(categoryPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setRecords(page.getResult());
        pageResult.setTotal(page.getTotal());

        return Result.success(pageResult);
    }

    @PostMapping
    public Result<Void> saveCategory(@RequestBody Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryService.saveCategory(category);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable() Integer status, @RequestParam("id") Integer id) {
        categoryService.updateStatus(status, id);
        return Result.success();
    }

    @PutMapping("")
    public Result update(Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategory(@RequestParam("id") Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<List<Category>> list(@RequestParam Integer type){
        List<Category> list= categoryService.findAll(type);

        return Result.success(list);
    }



}
