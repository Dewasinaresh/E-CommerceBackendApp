package com.nd.electronic.web.MTechDistributions.Entitys;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CATEGORIES")
public class CategoryEntity {
    @Id
    @Column(name = "ID",unique = true,updatable = false,nullable = false)
    private String categoryId;
    @Column(name = "CAT_TITLE",nullable = false)
    private String title;
    @Column(name = "CAT_DESC",nullable = false)
    private String description;
    @Column(name = "IMAGE_PATH",nullable = false)
    private String categoryImage;

    @OneToMany(mappedBy = "categoty",cascade = CascadeType.ALL)
    List<ProductEntity> productList=new ArrayList<ProductEntity>();


}
