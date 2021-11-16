package com.boot.wechart.controller.material;

import com.boot.wechart.chartenum.MessageTypeEnum;
import com.boot.wechart.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * @Author bjiang
 * @Description //TODO
 * @Date 2021/7/28 11:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("/uploadMaterial")
    public void uploadMaterial(){
        File file=new File("D:\\msg\\mei.png");
        materialService.uploadMaterial(file,"media");
    }
}
