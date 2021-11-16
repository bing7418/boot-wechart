package com.boot.wechart.controller.menu;

import com.boot.wechart.entity.Menu;
import com.boot.wechart.service.MenuService;
import com.boot.wechart.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 11:48
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/createMenu")
    public String createMenu(){
        Menu menu= MenuUtil.me().getSyStemMenu();//获取文档示例菜单
        return menuService.creatMenu(menu);
    }
    //删除菜单
    @RequestMapping("/deleteMenu")
    public String deleteMenu(){
        return menuService.deleteMenu();
    }
    //查询菜单
    @RequestMapping("/getMenu")
    public String getMenu(){
        return menuService.getMenu();
    }
}
