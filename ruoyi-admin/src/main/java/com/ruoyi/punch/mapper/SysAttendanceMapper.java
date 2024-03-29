package com.ruoyi.punch.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.punch.domain.SysAttendance;

/**
 * 打卡签到Mapper接口
 *
 * @author ruoyi
 * @date 2021-09-29
 */
public interface SysAttendanceMapper
{
    /**
     * 查询打卡签到
     *
     * @param attendanceId 打卡签到ID
     * @return 打卡签到
     */
    public SysAttendance selectSysAttendanceById(Long attendanceId);

    /**
     * 查询打卡签到
     *
     * @return 打卡签到
     */
    public List<Map<String,Object>> selectNowWeekAttendanceList();

    /**
     * 查询打卡签到列表
     *
     * @param sysAttendance 打卡签到
     * @return 打卡签到集合
     */
    public List<SysAttendance> selectSysAttendanceList(SysAttendance sysAttendance);

    /**
     * 新增打卡签到
     *
     * @param sysAttendance 打卡签到
     * @return 结果
     */
    public int insertSysAttendance(SysAttendance sysAttendance);

    /**
     * 修改打卡签到
     *
     * @param sysAttendance 打卡签到
     * @return 结果
     */
    public int updateSysAttendance(SysAttendance sysAttendance);

    /**
     * 删除打卡签到
     *
     * @param attendanceId 打卡签到ID
     * @return 结果
     */
    public int deleteSysAttendanceById(Long attendanceId);

    /**
     * 批量删除打卡签到
     *
     * @param attendanceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAttendanceByIds(Long[] attendanceIds);


    /**
     * 是否重复打卡
     * @param userName
     * @return
     */
    public SysAttendance isRepeatPunchTimeByUName(String userName);

    /**
     *获得当天所有打卡人数信息
     * @return
     */
    public int selectTodayPunchList();


}
