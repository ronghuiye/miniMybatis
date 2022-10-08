package io.ronghuiye.mybatis.test.dao;


import io.ronghuiye.mybatis.test.po.Activity;

public interface IActivityDao {

    Activity queryActivityById(Long activityId);

    Integer insert(Activity activity);
}
