package cn.com.reformer.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Nipppppp
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员id
     */
    private Integer adminId;

    private String permissionCode;

    private String permissionRemark;

    /**
     * NOW()
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
