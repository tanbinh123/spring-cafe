package com.springbucks.sc.waiter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
