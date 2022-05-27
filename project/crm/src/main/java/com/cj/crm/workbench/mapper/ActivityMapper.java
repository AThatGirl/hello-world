package com.cj.crm.workbench.mapper;

import com.cj.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {

    /**
     * 按主键删除
     *
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(String id);

    /**
     * 选择性插入
     *
     * @param record 记录
     * @return int
     */
    int insertSelective(Activity record);

    /**
     * 选择通过主键
     *
     * @param id id
     * @return {@link Activity}
     */
    Activity selectByPrimaryKey(String id);

    /**
     * 选择性更新主键
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * 更新主键
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKey(Activity record);


    /**
     * 保存市场活动
     *
     * @param activity 市场活动
     * @return int
     */
    int insertActivity(Activity activity);

    /**
     * 根据条件选择活动页面
     * 根据条件分页查询市场活动列表
     *
     * @param map 地图
     * @return {@link List}<{@link Activity}>
     */
    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询市场活动总条数
     *
     * @param map 地图
     * @return int
     */
    int selectCountOfActivityByCondition(Map<String,Object> map);

    /**
     * 通过id批量删除市场活动
     *
     * @param ids
     * @return int
     */
    int deleteActivityByIds(String[] ids);

    /**
     * 选择活动id
     * 根据id查询市场活动
     *
     * @param id id
     * @return {@link Activity}
     */
    Activity selectActivityById(String id);

    /**
     * 更新市场活动
     *
     * @param activity 活动
     * @return int
     */
    int updateActivity(Activity activity);

    /**
     * 查询所有活动
     *
     * @return {@link List}<{@link Activity}>
     */
    List<Activity> selectAllActivities();


    /**
     * 外部导入活动
     *
     * @param activityList 活动列表
     * @return int
     */
    int insertActivityByList(List<Activity> activityList);

    /**
     * 根据id查询市场活动明细信息
     *
     * @param id id
     * @return {@link Activity}
     */
    Activity selectActivityForDetailById(String id);
}