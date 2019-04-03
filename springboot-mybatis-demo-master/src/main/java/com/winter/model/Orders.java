package com.winter.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders {
    private Long id;

    private String merchantUnionIds;

    private String sn;

    private Long platformId;

    private String sellerType;

    private Long sellerId;

    private Long storeId;

    private String buyerType;

    private Long buyerId;

    private Long buyerAccountId;

    private Long agentBuyerId;

    private Long agentEmployeeId;

    private String tradeSceneCode;

    private String payMode;

    private BigDecimal payAmount;

    private BigDecimal amount;

    private BigDecimal discount;

    private BigDecimal cardAmount;

    private BigDecimal freeRate;

    private BigDecimal payFreeRate;

    private Integer status;

    private Date finishTime;

    private Date deliverTime;

    private Date payTime;

    private Date receiveTime;

    private String fromType;

    private Long fromId;

    private String parcelIds;

    private String logisticSns;

    private Long createAccountId;

    private Date createdAt;

    private String logisticsCompCode;

    private Long providerId;

    private Integer hasReceive;

    private String openId;

    private String cardCode;

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    private List<OrderItems> orderItemsList;

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    private OrderItems orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantUnionIds() {
        return merchantUnionIds;
    }

    public void setMerchantUnionIds(String merchantUnionIds) {
        this.merchantUnionIds = merchantUnionIds == null ? null : merchantUnionIds.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType == null ? null : sellerType.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType == null ? null : buyerType.trim();
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(Long buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public Long getAgentBuyerId() {
        return agentBuyerId;
    }

    public void setAgentBuyerId(Long agentBuyerId) {
        this.agentBuyerId = agentBuyerId;
    }

    public Long getAgentEmployeeId() {
        return agentEmployeeId;
    }

    public void setAgentEmployeeId(Long agentEmployeeId) {
        this.agentEmployeeId = agentEmployeeId;
    }

    public String getTradeSceneCode() {
        return tradeSceneCode;
    }

    public void setTradeSceneCode(String tradeSceneCode) {
        this.tradeSceneCode = tradeSceneCode == null ? null : tradeSceneCode.trim();
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public BigDecimal getFreeRate() {
        return freeRate;
    }

    public void setFreeRate(BigDecimal freeRate) {
        this.freeRate = freeRate;
    }

    public BigDecimal getPayFreeRate() {
        return payFreeRate;
    }

    public void setPayFreeRate(BigDecimal payFreeRate) {
        this.payFreeRate = payFreeRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType == null ? null : fromType.trim();
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getParcelIds() {
        return parcelIds;
    }

    public void setParcelIds(String parcelIds) {
        this.parcelIds = parcelIds == null ? null : parcelIds.trim();
    }

    public String getLogisticSns() {
        return logisticSns;
    }

    public void setLogisticSns(String logisticSns) {
        this.logisticSns = logisticSns == null ? null : logisticSns.trim();
    }

    public Long getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(Long createAccountId) {
        this.createAccountId = createAccountId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLogisticsCompCode() {
        return logisticsCompCode;
    }

    public void setLogisticsCompCode(String logisticsCompCode) {
        this.logisticsCompCode = logisticsCompCode == null ? null : logisticsCompCode.trim();
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Integer getHasReceive() {
        return hasReceive;
    }

    public void setHasReceive(Integer hasReceive) {
        this.hasReceive = hasReceive;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

}