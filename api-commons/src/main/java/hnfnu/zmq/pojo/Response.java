package hnfnu.zmq.pojo;

import lombok.Data;

@Data
public class Response {

    private Object data;
    private Integer status;
    private String msg="error";

    public Response() {
    }

    public Response(Integer status, Object data) {
        this.data = data;
        this.status = status;
    }

    public Response(Integer status, Object data, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }


}