package com.cj.crm.workbench.service;

import com.cj.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * 活动备注Service
 *
 * @author 杰瑞
 * @date 2022/05/25
 */
public interface ActivityRemarkService {

    List<ActivityRemark> queryActivityForDetailByActivityId(String activityId);

    int saveCreateActivityRemark(ActivityRemark remark);

    int deleteActivityRemarkById(String id);

    int editActivityRemark(ActivityRemark remark);
}
