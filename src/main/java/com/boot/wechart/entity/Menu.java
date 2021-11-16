package com.boot.wechart.entity;

import lombok.Data;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 13:31
 */
@Data
public class Menu {
    private Button[] button;//一级菜单数组，个数应为1~3个
}
