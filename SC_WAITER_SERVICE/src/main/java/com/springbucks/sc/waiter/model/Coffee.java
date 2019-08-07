package com.springbucks.sc.waiter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.money.Money;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "T_COFFEE")
@Data
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Coffee extends BaseEntity implements Serializable {
    private String name;
    private Money price;
}
