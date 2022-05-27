package com.cj.crm.workbench.mapper;

import com.cj.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {

    int deleteByPrimaryKey(String id);


    int insert(ActivityRemark record);


    int insertSelective(ActivityRemark record);

     
    ActivityRemark selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(ActivityRemark record);

     
    int updateByPrimaryKey(ActivityRemark record);


    /**
     * 根据activityId查询所有备注的明细信息
     *
     * @param ActivityId 活动id
     * @return {@link List}<{@link ActivityRemark}>
     */
    List<ActivityRemark> selectActivityForDetailByActivityId(String ActivityId);

    /**
     * 保存的市场活动备注
     *
     * @param remark 备注
     * @return int
     */
    int insertActivityRemark(ActivityRemark remark);

    /**
     * 通过Id删除活动备注
     *
     * @param id id
     * @return int
     */
    int deleteActivityRemarkById(String id);

    /**
     * 更新活动备注
     *
     * @param remark 备注
     * @return int
     */
    int updateActivityRemark(ActivityRemark remark);
}