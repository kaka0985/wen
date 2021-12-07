package com.tledu.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;
    private String oid;
    private Integer bid;
    private Integer count;//购买数量
    private BigDecimal totalprice;
    private Date createdate;//创建时间
    private Integer status; //状态
    private Integer uid;//用户id
    private BigDecimal sumprice;

    public BigDecimal getSumprice() {
        return sumprice;
    }

    public void setSumprice(BigDecimal sumprice) {
        this.sumprice = sumprice;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Order(Integer id, String oid, Integer bid, Integer count, BigDecimal totalprice, Date createdate, Integer status, Integer uid) {
        this.id = id;
        this.oid = oid;
        this.bid = bid;
        this.count = count;
        this.totalprice = totalprice;
        this.createdate = createdate;
        this.status = status;
        this.uid = uid;
    }

    public Order() {
    }
}
