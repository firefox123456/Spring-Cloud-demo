package huangqi.common.base.entity;

import lombok.Data;

import java.util.List;

/**
 * 无
 *
 * @author "黄骐"
 * @date 2023/07/16 18:54
 **/
@Data
public class APage<T> {
    /**
     * 条数
     */
    private Long total;
    /**
     * 页数
     */
    private Long pageNo;
    /**
     * 页面条数
     */
    private Long pageSize;
    /**
     * 数据
     */
    private List<T> list;
}
