package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;

public interface OrderService {
    Page<Orders> findPage(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO statistics();
}
