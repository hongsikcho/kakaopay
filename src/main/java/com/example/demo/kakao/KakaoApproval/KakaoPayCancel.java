package com.example.demo.kakao.KakaoApproval;

import com.example.demo.kakao.KakaoApproval.Amount;
import com.example.demo.kakao.KakaoApproval.ApprovedCancelAmount;
import com.example.demo.kakao.KakaoApproval.CancelAvailableAmount;
import com.example.demo.kakao.KakaoApproval.CanceledAmount;
import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayCancel {
    private String aid, tid, cid;
    private String status;
    private String partner_order_id, partner_user_id, payment_method_type;
    private Amount amount;
    private ApprovedCancelAmount approved_cancel_amount;
    private CanceledAmount canceled_amount;
    private CancelAvailableAmount cancel_available_amount;
    private String item_name, item_code;
    private Integer quantity;
    private Date created_at, approved_at, canceled_at;
    private String payload;
}
