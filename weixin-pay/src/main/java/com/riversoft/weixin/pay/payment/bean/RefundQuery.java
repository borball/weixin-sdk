package com.riversoft.weixin.pay.payment.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.pay.base.BaseResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @borball on 5/17/2016.
 */
@JsonIgnoreProperties
public class RefundQuery extends BaseResponse {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("out_trade_no")
    private String tradeNumber;

    @JsonProperty("total_fee")
    private int totalFee;

    @JsonProperty("settlement_total_fee")
    private int settlementTotalFee;

    @JsonProperty("fee_type")
    private String feeType;

    @JsonProperty("cash_fee")
    private int cashFee;

    @JsonProperty("refund_count")
    private int refundCount;

    @JsonProperty("refund_account")
    private String refundAccount;

    protected Map<String,Object> others = new HashMap<>();

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(int settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    @JsonIgnore
    public List<Refund> getRefunds(){
        List<Refund> refunds = new ArrayList<>();
        if(others.size() > 0) {
            for (int i = 0; i < refundCount; i++) {
                Refund refund = new Refund();

                if(others.containsKey("out_refund_no_" + i)) {
                    refund.setOutRefundNo((String)others.get("out_refund_no_" + i));
                }
                if(others.containsKey("refund_id_" + i)) {
                    refund.setRefundId((String)others.get("refund_id_" + i));
                }
                if(others.containsKey("refund_channel_" + i)) {
                    refund.setRefundChannel((String)others.get("refund_channel_" + i));
                }
                if(others.containsKey("refund_fee_" + i)) {
                    refund.setRefundFee(Integer.valueOf(others.get("refund_fee_" + i).toString()));
                }
                if(others.containsKey("settlement_refund_fee_" + i)) {
                    refund.setSettlementRefundFee(Integer.valueOf(others.get("settlement_refund_fee_" + i).toString()));
                }
                if(others.containsKey("coupon_type_" + i)) {
                    refund.setCouponType((String)others.get("coupon_type_" + i));
                }
                if(others.containsKey("coupon_refund_fee_" + i)) {
                    refund.setCouponRefundFee(Integer.valueOf(others.get("coupon_refund_fee_" + i).toString()));
                }
                if(others.containsKey("coupon_refund_count_" + i)) {
                    int count = Integer.valueOf(others.get("coupon_refund_count_" + i).toString());
                    refund.setCouponRefundCount(count);
                    List<CouponRefund> couponRefunds = new ArrayList();
                    for (int j = 0; j < count; j++) {
                        CouponRefund couponRefund = new CouponRefund();
                        if(others.containsKey("coupon_refund_id_" + i + "_" + j)) {
                            couponRefund.setCouponRefundId((String)others.get("coupon_refund_id_" + i + "_" + j));
                        }
                        if(others.containsKey("coupon_refund_fee_" + i + "_" + j)) {
                            couponRefund.setCouponRefundFee(Integer.valueOf(others.get("coupon_refund_fee_" + i + "_" + j).toString()));
                        }

                        couponRefunds.add(couponRefund);
                    }

                    refund.setCouponRefunds(couponRefunds);
                }
                if(others.containsKey("refund_status_" + i)) {
                    refund.setRefundStatus((String)others.get("refund_status_" + i));
                }
                if(others.containsKey("refund_recv_accout_" + i)) {
                    refund.setRefundReceiveAccount((String)others.get("refund_recv_accout_" + i));
                }

                refunds.add(refund);
            }
        }
        return refunds;
    }

    public class Refund {

        private String outRefundNo;
        private String refundId;
        private String refundChannel;
        private int refundFee;
        private int settlementRefundFee;
        private String couponType;
        private int couponRefundFee;
        private int couponRefundCount;
        private String refundStatus;
        private String refundReceiveAccount;

        private List<CouponRefund> couponRefunds;

        public String getOutRefundNo() {
            return outRefundNo;
        }

        public void setOutRefundNo(String outRefundNo) {
            this.outRefundNo = outRefundNo;
        }

        public String getRefundId() {
            return refundId;
        }

        public void setRefundId(String refundId) {
            this.refundId = refundId;
        }

        public String getRefundChannel() {
            return refundChannel;
        }

        public void setRefundChannel(String refundChannel) {
            this.refundChannel = refundChannel;
        }

        public int getRefundFee() {
            return refundFee;
        }

        public void setRefundFee(int refundFee) {
            this.refundFee = refundFee;
        }

        public int getSettlementRefundFee() {
            return settlementRefundFee;
        }

        public void setSettlementRefundFee(int settlementRefundFee) {
            this.settlementRefundFee = settlementRefundFee;
        }

        public String getCouponType() {
            return couponType;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public int getCouponRefundFee() {
            return couponRefundFee;
        }

        public void setCouponRefundFee(int couponRefundFee) {
            this.couponRefundFee = couponRefundFee;
        }

        public int getCouponRefundCount() {
            return couponRefundCount;
        }

        public void setCouponRefundCount(int couponRefundCount) {
            this.couponRefundCount = couponRefundCount;
        }

        public String getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }

        public String getRefundReceiveAccount() {
            return refundReceiveAccount;
        }

        public void setRefundReceiveAccount(String refundReceiveAccount) {
            this.refundReceiveAccount = refundReceiveAccount;
        }

        public List<CouponRefund> getCouponRefunds() {
            return couponRefunds;
        }

        public void setCouponRefunds(List<CouponRefund> couponRefunds) {
            this.couponRefunds = couponRefunds;
        }
    }

    public class CouponRefund {

        private String couponRefundId;
        private int couponRefundFee;

        public String getCouponRefundId() {
            return couponRefundId;
        }

        public void setCouponRefundId(String couponRefundId) {
            this.couponRefundId = couponRefundId;
        }

        public int getCouponRefundFee() {
            return couponRefundFee;
        }

        public void setCouponRefundFee(int couponRefundFee) {
            this.couponRefundFee = couponRefundFee;
        }
    }
}
