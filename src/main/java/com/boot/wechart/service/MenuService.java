package com.boot.wechart.service;

import com.boot.wechart.entity.Menu;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 11:50
 */
public interface MenuService {
    /*
     * @Author bjiang
     * @Description //创建菜单
     * @Date 13:43 2020/7/21
     * @Param [menu]
     * @return
     **/
    String creatMenu(Menu menu);
    /*
     * @Author bjiang
     * @Description //删除菜单
     * @Date 13:43 2020/7/21
     * @return
     **/
    String deleteMenu();
    /*
     * @Author bjiang
     * @Description //查询菜单
     * @Date 13:43 2020/7/21
     * @return
     **/
    String getMenu();
}
