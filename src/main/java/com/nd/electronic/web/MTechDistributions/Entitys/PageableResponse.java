package com.nd.electronic.web.MTechDistributions.Entitys;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalElement;
    private boolean lastPage;
}