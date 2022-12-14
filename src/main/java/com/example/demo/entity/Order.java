package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;
    @Enumerated(value = EnumType.STRING)
    private PayType payType;
    @Enumerated(value = EnumType.STRING)
    private PayStatus payStatus;

    private LocalDateTime orderedAt;
    private int totalPrice;
    @Lob
    private String requests;

    private String userNickName;
    private String storeSerialNumber;

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.COOKING) || orderStatus.equals(OrderStatus.COMPLETE)) {
            this.payStatus = PayStatus.COMPLETE;
        }
        this.orderStatus = orderStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;

    }
}

