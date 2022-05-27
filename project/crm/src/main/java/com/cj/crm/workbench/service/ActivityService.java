package com.cj.crm.workbench.service;

import com.cj.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

    int queryCountOfActivityByCondition(Map<String,Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveEditActivity(Activity activity);

    List<Activity> queryAllActivities();

    /**
     * 外部导入市场活动
     *
     * @param activityList 活动列表
     * @return int
     */
    int saveActivityByList(List<Activity> activityList);

    Activity queryActivityForDetailById(String id);
}
