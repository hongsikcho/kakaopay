package com.example.demo.kakao.KakaoApproval;

import lombok.Data;

import java.util.Date;
@Data
public class KakaoPayResponse {

    private String tid;
    private String next_redirect_pc_url;
    private Date create_at;
}
