package com.mengzhou.trashrecycling.common.upload;

import com.mengzhou.trashrecycling.common.Dto.UploadFileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 图片上传工具类
 *
 * @author CC
 * @since 2019-10-14
 */
@RestController
public class UploadController {

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    @Value("${file.basePhysicPath}")
    private String staticAccessPath;

    @Value("${file.baseLogicPath}")
    private String baseLogicPath;

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("/api/upload")
    public Map<String, Object> upload(MultipartFile[] file) {
        Map<String, Object> modelMap = new HashMap<>();

        if (file == null) {
            modelMap.put("success", false);
            modelMap.put("msg", "流文件为空");
            return modelMap;
        }

        // 判断上传路径是否存在 如果不存在则创建
        File fileIO = new File(baseLogicPath);
        if (!fileIO.exists()) {
            fileIO.mkdirs();
        }

        try {
            //获取文件后缀名
            List list = new ArrayList();
            for (int i = 0; i < file.length; i++) {
                String suffix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf(".") + 1);

                String realFileName = getRandomFileName() + "." + suffix; // 随机名+拓展名
                String destFileName = staticAccessPath + realFileName;

                File destFile = new File(destFileName);
                destFile.getParentFile().mkdirs();
                file[i].transferTo(destFile);

                list.add(destFileName.substring(2));
            }

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