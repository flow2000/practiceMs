package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysBaseInfo;

import java.util.List;


/**
 * 基地信息管理Mapper接口
 * 
 * @author xieweiming
 * @date 2021-10-01
 */
public interface SysBaseInfoMapper 
{
    /**
     * 查询基地信息管理
     * 
     * @param baseId 基地信息管理ID
     * @return 基地信息管理
     */
    public SysBaseInfo selectSysBaseInfoById(Long baseId);

    /**
     * 查询基地信息管理列表
     * 
     * @param sysBaseInfo 基地信息管理
     * @return 基地信息管理集合
     */
    public List<SysBaseInfo> selectSysBaseInfoList(SysBaseInfo sysBaseInfo);

    /**
     * 新增基地信息管理
     * 
     * @param sysBaseInfo 基地信息管理
     * @return 结果
     */
    public int insertSysBaseInfo(SysBaseInfo sysBaseInfo);

    /**
     * 修改基地信息管理
     * 
     * @param sysBaseInfo 基地信息管理
     * @return 结果
     */
    public int updateSysBaseInfo(SysBaseInfo sysBaseInfo);

    /**
     * 删除基地信息管理
     * 
     * @param baseId 基地信息管理ID
     * @return 结果
     */
    public int deleteSysBaseInfoById(Long baseId);

    /**
     * 批量删除基地信息管理
     * 
     * @param baseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBaseInfoByIds(Long[] baseIds);
}