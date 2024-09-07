package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO, HttpSession session) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //将登录数据员工id存储到session会话中
        session.setAttribute("employee", employee.getId());

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(employee.getId().toString())
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(@RequestParam() Integer page,
                                   @RequestParam() Integer pageSize,
                                   @RequestParam(value = "name", required = false) String name
                                    ) {

        Page<Employee> employees = employeeService.findPage(page, pageSize,name);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(employees.getTotal());
        pageResult.setRecords(employees.getResult());
        return Result.success(pageResult);

    }

    @PostMapping()
    public Result save(@RequestBody Employee employee) {
        employeeService.saveBatch(employee);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Employee> delete(@PathVariable() Long id) {
        Employee employee = employeeService.showById(id);
        return Result.success(employee);
    }
    @PutMapping
    public Result update(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.success();
    }
//    http://localhost/api/employee/status/0?id=4
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable() Integer status,@RequestParam("id")Integer id){
        employeeService.updateStatus(status,id);
        return Result.success();
    }
}
