package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.Dto.CabinetlocationDto;
import com.mengzhou.trashrecycling.model.Cabinetlocation;
import com.mengzhou.trashrecycling.mapper.CabinetlocationMapper;
import com.mengzhou.trashrecycling.service.CabinetlocationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.AddressUtil;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-10-22
 */
@Service
@Slf4j
public class CabinetlocationServiceImpl extends ServiceImpl<CabinetlocationMapper, Cabinetlocation> implements CabinetlocationService {

    @Autowired
    private CabinetlocationMapper cabinetlocationMapper;

    @Override
    public LayuiResult save(Cabinetlocation cabinetlocation) {
        try {
            if (cabinetlocation.getCabinetName() == null || cabinetlocation.getLat() == null
                    || cabinetlocation.getLon() == null) {

                log.error("添加柜机位置出现错误:必填项为空");
                return LayuiResult.fail("必填项不能为空");
            }

            cabinetlocation.setCreateTime(new Date());
            cabinetlocationMapper.insert(cabinetlocation);
            return LayuiResult.success("添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加柜机位置出现异常:" + e.getMessage());
            return LayuiResult.fail("添加失败");
        }
    }

    @Override
    public LayuiResult deleteById(Integer id) {
        try {
            if (id == null) {
                log.error("删除柜机位置出现错误:id为空");
                return LayuiResult.fail("必填项不能为空");
            }

            cabinetlocationMapper.deleteById(id);
            return LayuiResult.success("删除成功");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除柜机位置出现异常:" + e.getMessage());
            return LayuiResult.fail("删除失败");
        }
    }

    @Override
    public LayuiResult modifyById(Cabinetlocation cabinetlocation) {
        try {
            if (cabinetlocation.getCabinetName() == null || cabinetlocation.getLat() == null
                    || cabinetlocation.getLon() == null || cabinetlocation.getId() == null) {

                log.error("更新柜机位置出现错误:必填项为空");
                return LayuiResult.fail("必填项不能为空");
            }

            cabinetlocation.setCreateTime(new Date());
            cabinetlocationMapper.updateById(cabinetlocation);
            return LayuiResult.success("更新成功");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新柜机位置出现异常:" + e.getMessage());
            return LayuiResult.fail("更新失败");
        }
    }

    @Override
    public LayuiResult findById(Integer id) {
        try {
            if (id == null) {
                log.error("根据id查询柜机位置出现错误:id为空");
                return LayuiResult.fail("查询失败");
            }

            Cabinetlocation cabinetlocation = cabinetlocationMapper.selectById(id);
            return LayuiResult.success(cabinetlocation);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查询柜机位置出现异常:" + e.getMessage());
            return LayuiResult.fail("查询失败");
        }
    }

    @Override
    public List<Cabinetlocation> findAll(String cabinetName) {
        if (cabinetName == null) {
            List<Cabinetlocation> cabinetlocationList = cabinetlocationMapper.selectList(new EntityWrapper<Cabinetlocation>()
                    .orderBy("createTime", false));
            return cabinetlocationList;
        }

        List<Cabinetlocation> cabinetlocationList = cabinetlocationMapper.selectList(new EntityWrapper<Cabinetlocation>()
                .orderBy("createTime", false).like("cabinetName", cabinetName));
        return cabinetlocationList;
    }

    @Override
    public Map<String, Object> findNearby(Double lon, Double lag) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            List<Cabinetlocation> cabinetlocationList = cabinetlocationMapper.selectList(new EntityWrapper<Cabinetlocation>());

            List<CabinetlocationDto> cabinetlocationDtoList = new ArrayList();

            //把实体距离和信息放入DTO对象中
            for (Cabinetlocation cabinetlocation : cabinetlocationList) {
                CabinetlocationDto cabinetlocationDto = new CabinetlocationDto();
                BeanUtils.copyProperties(cabinetlocation, cabinetlocationDto);
                double dist = AddressUtil.GetDistance(lon, lag, Double.valueOf(cabinetlocation.getLon())
                        , Double.valueOf(cabinetlocation.getLat()));

                cabinetlocationDto.setDist(dist);
                cabinetlocationDtoList.add(cabinetlocationDto);
            }

            // 获取距离最近的数据
            Optional<CabinetlocationDto> cabinetlocationDtoOptional = cabinetlocationDtoList.stream()
                    .min(Comparator.comparingDouble(CabinetlocationDto::getDist));
            CabinetlocationDto cabinetlocationDto = cabinetlocationDtoOptional.get();


            modelMap.put("data", cabinetlocationDto);
            modelMap.put("success", true);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("msg", "查询柜子距离时出现异常:" + e.getMessage());
            modelMap.put("success", true);
            return modelMap;
        }
    }
}
