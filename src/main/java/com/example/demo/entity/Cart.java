package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart extends BaseEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;

    private String userNickName;
    private int count;
    private boolean isOrdered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartAndOption> cartAndOptions = new ArrayList<>();

    public void updateCartAndOptions(List<CartAndOption> cartAndOptions) {
        this.cartAndOptions = new ArrayList<>();
        for (CartAndOption cartAndOption : cartAndOptions) {
            this.cartAndOptions.add(cartAndOption);
        }
    }
}
