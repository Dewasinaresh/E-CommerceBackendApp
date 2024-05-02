package com.nd.electronic.web.MTechDistributions.Entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
    @Column(name = "PRODUCT_TITILE")
    private  String productTitle;
    @Column(name = "DISCRIPTIONS")
    @Length(max = 200)
    private  String discriptions;
    @Column(name = "PRICE")
    private int price;
    @Column(name ="Dis_Price")
    private int discountPrice;
    @Column(name = "AVAILBLE_QNT")
    private int availableQuantity;
    @Column(name = "PRODUCT_DATE")
    private Date productDate;
    @Column(name = "PRD_LIVE_STATUS")
    private boolean live;
    @Column(name="PRD_AVBL_STOCK")
    private boolean stock;
    @Column(name="FILE_NAME")
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    CategoryEntity categoty;




}
