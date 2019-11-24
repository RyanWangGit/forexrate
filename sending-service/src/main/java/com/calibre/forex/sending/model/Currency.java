package com.calibre.forex.sending.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Currency implements Serializable {
    private String code;
    private long timestamp;
    private int gmtoffset;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal previousClose;
    private long volume;
    private BigDecimal change;
    private BigDecimal change_p;

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", timeStamp=" + timestamp +
                ", gmtOffset=" + gmtoffset +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", previousClose=" + previousClose +
                ", volume=" + volume +
                ", change=" + change +
                ", change_p=" + change_p +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getGmtoffset() {
        return gmtoffset;
    }

    public void setGmtoffset(int gmtoffset) {
        this.gmtoffset = gmtoffset;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(BigDecimal previousClose) {
        this.previousClose = previousClose;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getChange_p() {
        return change_p;
    }

    public void setChange_p(BigDecimal change_p) {
        this.change_p = change_p;
    }
}
