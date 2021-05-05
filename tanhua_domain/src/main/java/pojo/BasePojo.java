package pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-04 08:35
 **/
@Data
public abstract class BasePojo implements Serializable {
    @TableField(fill = FieldFill.INSERT)
    private Date created;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;
}