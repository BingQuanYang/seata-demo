package com.smart.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.store.entity.Storage;
import com.smart.store.mapper.StorageMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StoreController {
    @Resource
    StorageMapper storageMapper;

    @PutMapping("/update")
    public int dec(long productId, int quantity) {
        int flag = -1;
        try {
            Storage storage = storageMapper.selectOne(new QueryWrapper<Storage>().lambda().eq(Storage::getProductId, productId));
            if (storage == null) {
                log.info("货都没有买个锤子啊");
                throw new RuntimeException("商品库存不存在");
            }
            if (storage.getResidue() < quantity) {
                log.info("没货了，买那么慢。不是我系统有bug!");
                throw new RuntimeException("库存不足");
            }
            //+已用库存
            storage.setUsed(storage.getUsed() + quantity);
            //-剩余库存
            storage.setResidue(storage.getResidue() - quantity);
            flag = storageMapper.updateById(storage);
        } catch (Exception e) {
            throw new RuntimeException("系统...");
        }finally {
            return flag;
        }

    }
}
