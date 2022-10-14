package com.example.demo.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequiredArgsConstructor
public class kakaoController {
    private final KakaoPayService kakaoPayService;
    @GetMapping("kakaopay")
    public String doKakaoPay(@PathVariable Long orderId) {

        return "redirect:" + kakaoPayService.doKakaoPay();
    }

    @GetMapping("kakaopay")
    @ResponseBody
    public String kakaopay() {
        try {
            // 보내는 부분
            URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection connection = (HttpURLConnection) address.openConnection(); // 서버연결
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "KakaoAK ce1bd657a8a263e0f9665d7c65d49fef"); // 어드민 키
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true); // 서버한테 전달할게 있는지 없는지
            String parameter = "cid=TC0ONETIME" // 가맹점 코드
                    + "&partner_order_id=partner_order_id" // 가맹점 주문번호
                    + "&partner_user_id=partner_user_id" // 가맹점 회원 id
                    + "&item_name=초코파이" // 상품명
                    + "&quantity=1" // 상품 수량
                    + "&total_amount=5000" // 총 금액
                    + "&vat_amount=200" // 부가세
                    + "&tax_free_amount=0" // 상품 비과세 금액
                    + "&approval_url=http://localhost:8080/paySuccess" // 결제 성공 시
                    + "&fail_url=http://localhost:8080" // 결제 실패 시
                    + "&cancel_url=http://localhost:8080/"; // 결제 취소 시
            OutputStream send = connection.getOutputStream(); // 이제 뭔가를 를 줄 수 있다.
            DataOutputStream dataSend = new DataOutputStream(send); // 이제 데이터를 줄 수 있다.
            dataSend.writeBytes(parameter); // OutputStream은 데이터를 바이트 형식으로 주고 받기로 약속되어 있다. (형변환)
            dataSend.close(); // flush가 자동으로 호출이 되고 닫는다. (보내고 비우고 닫다)

            int result = connection.getResponseCode(); // 전송 잘 됐나 안됐나 번호를 받는다.
            InputStream receive; // 받다

            if(result == 200) {
                receive = connection.getInputStream();
            }else {
                receive = connection.getErrorStream();
            }
            // 읽는 부분
            InputStreamReader read = new InputStreamReader(receive); // 받은걸 읽는다.
            BufferedReader change = new BufferedReader(read); // 바이트를 읽기 위해 형변환 버퍼리더는 실제로 형변환을 위해 존제하는 클레스는 아니다.
            // 받는 부분
            return change.readLine(); // 문자열로 형변환을 알아서 해주고 찍어낸다 그리고 본인은 비워진다.

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/paySuccess")
    public String Success(){
        return "main";
    }
}
