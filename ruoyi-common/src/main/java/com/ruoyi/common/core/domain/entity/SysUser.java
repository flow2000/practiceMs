package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.poi.hpsf.Decimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author tomorrow
 */
@ApiModel(value = "SysUser", description = "用户实体")
public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    @Excel(name = "用户序号")
    private Long userId;

    /** 专业ID */
    @ApiModelProperty("专业ID")
    private Long deptId;



    /** 用户账号 */
    @ApiModelProperty("账号")
    @Excel(name = "账号")
    private String userName;

    /** 用户昵称 */
    @ApiModelProperty("姓名")
    @Excel(name = "姓名")
    private String nickName;

    /** 用户邮箱 */
    @ApiModelProperty("用户邮箱")
    @Excel(name = "邮箱")
    private String email;

    /** 期望人数 */
    private int expectNumber;

    /** 指导学生 */
    private List<SysUser> guideStudent;

    /** 手机号码 */
    @ApiModelProperty("手机号码")
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @ApiModelProperty("性别")
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知", prompt = "男、女、未知")
    private String sex;

    /** 用户头像 */
    @ApiModelProperty(hidden = true)
//    @ApiModelProperty("用户头像")
    private String avatar;

    /** 密码 **/
    @ApiModelProperty("密码")
    private String password;

    /** 盐加密 */
    @ApiModelProperty(hidden = true)
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @ApiModelProperty("帐号状态（0正常 1停用）")
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用", prompt = "正常、停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /** 最后登录IP */
    @ApiModelProperty("最后登录IP")
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @ApiModelProperty("最后登录时间")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    /** 专业对象 */
    @ApiModelProperty("专业对象")
    @Excels({
        @Excel(name = "专业班级", targetAttr = "deptName", type = Type.EXPORT),
//        @Excel(name = "专业负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;

    /** 角色对象 */
    @ApiModelProperty("角色对象")
    private List<SysRole> roles;

    /** 角色名称*/
    @Excel(name = "角色", type = Type.IMPORT, prompt = "实习学生、指导老师")
    private String roleName;

    /** 角色组 */
    @ApiModelProperty("角色组")
    private Long[] roleIds;

    /** 岗位组 */
    @ApiModelProperty(hidden = true)
    private Long[] postIds;

    /** 角色ID */
    @ApiModelProperty("角色ID")
    private Long roleId;

    /** 专业名称 */
    @ApiModelProperty(hidden = true)
    @Excel(name = "专业班级", type = Type.IMPORT)
    private String deptName;

    public SysUser()
    {

    }



    public int getExpectNumber() {
        return expectNumber;
    }

    public void setExpectNumber(int expectNumber) {
        this.expectNumber = expectNumber;
    }

    public List<SysUser> getGuideStudent() {
        return guideStudent;
    }

    public void setGuideStudent(List<SysUser> guideStudent) {
        this.guideStudent = guideStudent;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public SysUser(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

	@JsonIgnore
    @JsonProperty
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }

    public List<SysRole> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRole> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dept", getDept())
            .append("roleName", getRoleName())
            .toString();
    }
}
