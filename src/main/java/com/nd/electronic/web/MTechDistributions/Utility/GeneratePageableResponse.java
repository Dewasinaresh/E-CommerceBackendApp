package com.nd.electronic.web.MTechDistributions.Utility;

import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class GeneratePageableResponse
{

       public static <U,V> PageableResponse<V> generatePageResponse(Page<U> page,Class<V> className) {
           final List<U> obj = page.getContent();
           List<V> objectInfo = obj.stream().map(objectData -> new ModelMapper().map(objectData,className)).collect(Collectors.toList());
           PageableResponse<V> response=new PageableResponse<V>();
           response.setContent(objectInfo);
           response.setPageNumber(page.getNumber());
           response.setPageSize(page.getSize());
           response.setTotalElement(page.getNumberOfElements());
           response.setLastPage(page.isLast());

        return response;

       }

}
