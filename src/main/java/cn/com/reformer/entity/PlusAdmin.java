package cn.com.reformer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class PlusAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String userPassword;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
