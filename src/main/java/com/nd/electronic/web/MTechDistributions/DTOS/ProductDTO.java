package com.nd.electronic.web.MTechDistributions.DTOS;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
//    @NotNull
//    @NotBlank(message = "Invalid Product Id ")
    private String id;

    @Size(max = 200,min = 30,message = "product discription should be 30 to 200 ")
    private  String discriptions;


    @NotNull(message = "Product price is not valid")
    private int price;

    @NotNull(message = "Product title is invalid ")
    private String productTitle;

    @NotNull(message = "Discounted price is not valid ")
    private int discountPrice;

    @NotNull(message = "Availble Qunt is not valid ")
    private int availableQuantity;

    @NotNull(message = "Date is not valid")
    private Date productDate;


    @NotNull(message = "live status is not valid ")
    private boolean live;

    @NotNull(message = "Stock is not valid ")
    private boolean stock;

    private String fileName;
}
