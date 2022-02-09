package com.simon.october.cloud.admin.api.dto;

import com.simon.october.cloud.admin.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends SysUser {
    private List<Integer> role;

    private Integer deptId;

    /**
     * 新密码
     * */
    private String newPassword;
}
