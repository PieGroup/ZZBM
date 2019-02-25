package com.piegroup.zzbm.VO.SubC;

import java.io.Serializable;

public class DataPageSubc<T> implements Serializable {

    private static final long serialVersionUID = -7180842969824368104L;

    private T data;         //数据结果
    private PaginationSubC paginationSubC; //分页信息

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PaginationSubC getPaginationSubC() {
        return paginationSubC;
    }

    public void setPaginationSubC(PaginationSubC paginationSubC) {
        this.paginationSubC = paginationSubC;
    }

    @Override
    public String toString() {
        return "DataSub{" +
                "data=" + data +
                ", r_pagination=" + paginationSubC +
                '}';
    }
}
