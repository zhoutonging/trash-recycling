package com.mengzhou.trashrecycling.common.upload;

import com.mengzhou.trashrecycling.common.Dto.UploadFileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 图片上传工具类
 *
 * @author CC
 * @since 2019-08-06
 */
@RestController
public class UploadController {

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    @Value("${file.basePhysicPath}")
    private String staticAccessPath;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            //获取文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            String realFileName = getRandomFileName() + "." + suffix; // 随机名+拓展名
            String destFileName = staticAccessPath + realFileName;

            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);

            UploadFileDto uploadFileDto = new UploadFileDto();
            uploadFileDto.setFileName(realFileName);
            uploadFileDto.setFilePath(destFileName.substring(2));
            List list = Arrays.asList(uploadFileDto.getFilePath());
            modelMap.put("data", list);
            modelMap.put("errno", 0);

            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("上传失败", e.getMessage());
            return modelMap;
        }

    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        // 获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }
}