package org.devlive.authx.common.page;

import org.devlive.authx.common.support.PageSupport;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

    /**
     * order by default create time property and desc
     *
     * @param page page number
     * @param size current page size
     * @return current page config
     */
    public static Pageable orderByCreateTimeAndDesc(Integer page, Integer size) {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,
                PageSupport.DEFAULT_ORDER_PROPERTY_CREATE_TIME));
    }
}