package com.ruoyi.location.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 地点信息对象 sys_location_info
 *
 * @author ph
 * @date 2021-10-04
 */
public class LocationInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地点ID */
    private Long locationId;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String companyName;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 地点经纬度 */
    private String tude;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 单位性质 */
    @Excel(name = "单位性质")
    private String nature;

    /** 法定代表（负责人） */
    @Excel(name = "法定代表")
    private String leader;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 搜索字段 */
    private String searchKey;

    public void setLocationId(Long locationId)
    {
        this.locationId = locationId;
    }

    public Long getLocationId()
    {
        return locationId;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setContacts(String contacts)
    {
        this.contacts = contacts;
    }

    public String getContacts()
    {
        return contacts;
    }
    public void setTude(String tude)
    {
        this.tude = tude;
    }

    public String getTude()
    {
        return tude;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setNature(String nature)
    {
        this.nature = nature;
    }

    public String getNature()
    {
        return nature;
    }
    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    public String getLeader()
    {
        return leader;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("locationId", getLocationId())
                .append("companyName", getCompanyName())
                .append("address", getAddress())
                .append("contacts", getContacts())
                .append("tude", getTude())
                .append("phone", getPhone())
                .append("nature", getNature())
                .append("leader", getLeader())
                .append("delFlag", getDelFlag())
                .append("createTime", getCreateTime())
                .append("searchKey", getSearchKey())
                .toString();
    }
}
