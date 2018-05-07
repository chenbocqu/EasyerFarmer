package com.cqu.stuexpress.bean;


import java.io.Serializable;

public class Order implements Serializable {

    String id;
    String userId;
    String deliverId;
    int receiveAddressId;
    String payWayId;

    String price;

    String category;            // 任务类别，取寄快递，还书，其他
    String type;                // 快递类型
    String weight;
    String taskDesc;            // 任务描述

    Contact contact;            // 用户的联系方式
    ContactAddress fromAdd;  // 收货地址， 取快递时
    ContactAddress toAdd;     // 发出地址

    String remark;              // 备注

    String orderState;

    // TIME
    String payTime;             // 发布时间
    String placeTime;           // 处置时间
    String predictReceiveTime; // 预计收货时间
    String orderReceiveTime;   // 实际收货时间
    String completeTime;        // 完成时间

    String expressageNo;
    String expressageContent;


    // 评价
    String orderEvaluateLevel;
    String orderEvaluateContent;
    String orderAddressContent;
    String orderUserImageUrl;

    //抢单人员信息
    String exuserNickname; //昵称
    String exuserName; //姓名
    String exuserUrl; //姓名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(String deliverId) {
        this.deliverId = deliverId;
    }

    public int getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(int receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public String getPayWayId() {
        return payWayId;
    }

    public void setPayWayId(String payWayId) {
        this.payWayId = payWayId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ContactAddress getFromAdd() {
        return fromAdd;
    }

    public void setFromAdd(ContactAddress fromAdd) {
        this.fromAdd = fromAdd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }

    public String getPredictReceiveTime() {
        return predictReceiveTime;
    }

    public void setPredictReceiveTime(String predictReceiveTime) {
        this.predictReceiveTime = predictReceiveTime;
    }

    public String getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(String orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getExpressageNo() {
        return expressageNo;
    }

    public void setExpressageNo(String expressageNo) {
        this.expressageNo = expressageNo;
    }

    public String getExpressageContent() {
        return expressageContent;
    }

    public void setExpressageContent(String expressageContent) {
        this.expressageContent = expressageContent;
    }

    public String getOrderEvaluateLevel() {
        return orderEvaluateLevel;
    }

    public void setOrderEvaluateLevel(String orderEvaluateLevel) {
        this.orderEvaluateLevel = orderEvaluateLevel;
    }

    public String getOrderEvaluateContent() {
        return orderEvaluateContent;
    }

    public void setOrderEvaluateContent(String orderEvaluateContent) {
        this.orderEvaluateContent = orderEvaluateContent;
    }

    public String getOrderAddressContent() {
        return orderAddressContent;
    }

    public void setOrderAddressContent(String orderAddressContent) {
        this.orderAddressContent = orderAddressContent;
    }

    public String getOrderUserImageUrl() {
        return orderUserImageUrl;
    }

    public void setOrderUserImageUrl(String orderUserImageUrl) {
        this.orderUserImageUrl = orderUserImageUrl;
    }

    public String getExuserNickname() {
        return exuserNickname;
    }

    public void setExuserNickname(String exuserNickname) {
        this.exuserNickname = exuserNickname;
    }

    public String getExuserName() {
        return exuserName;
    }

    public void setExuserName(String exuserName) {
        this.exuserName = exuserName;
    }

    public String getExuserUrl() {
        return exuserUrl;
    }

    public void setExuserUrl(String exuserUrl) {
        this.exuserUrl = exuserUrl;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", deliverId='" + deliverId + '\'' +
                ", receiveAddressId=" + receiveAddressId +
                ", payWayId='" + payWayId + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", weight='" + weight + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", contact=" + contact +
                ", fromAdd=" + fromAdd +
                ", remark='" + remark + '\'' +
                ", orderState='" + orderState + '\'' +
                ", payTime='" + payTime + '\'' +
                ", placeTime='" + placeTime + '\'' +
                ", predictReceiveTime='" + predictReceiveTime + '\'' +
                ", orderReceiveTime='" + orderReceiveTime + '\'' +
                ", completeTime='" + completeTime + '\'' +
                ", expressageNo='" + expressageNo + '\'' +
                ", expressageContent='" + expressageContent + '\'' +
                ", orderEvaluateLevel='" + orderEvaluateLevel + '\'' +
                ", orderEvaluateContent='" + orderEvaluateContent + '\'' +
                ", orderAddressContent='" + orderAddressContent + '\'' +
                ", orderUserImageUrl='" + orderUserImageUrl + '\'' +
                ", exuserNickname='" + exuserNickname + '\'' +
                ", exuserName='" + exuserName + '\'' +
                ", exuserUrl='" + exuserUrl + '\'' +
                '}';
    }
}
