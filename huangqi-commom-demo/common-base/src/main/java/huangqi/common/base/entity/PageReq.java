package huangqi.common.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 无
 *
 * @author "黄骐"
 * @date 2023/07/16 18:56
 **/
@Data
public class PageReq implements Serializable {
    private Long pageNo;
    private Long pageSize;
}
