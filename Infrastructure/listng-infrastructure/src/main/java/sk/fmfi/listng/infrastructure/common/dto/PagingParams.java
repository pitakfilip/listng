package sk.fmfi.listng.infrastructure.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PagingParams implements BaseDto {

    public int page;

    public int size;

    public List<SortParams> sort = new ArrayList<>();

    public PagingParams(){}
    
    public PagingParams(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PagingParams(int page, int size, List<SortParams> sortParams) {
        this.page = page;
        this.size = size;
        this.sort.addAll(sortParams);
    }

    public PageRequest compile() {
        List<Sort.Order> orders = SortParams.compileParams(sort);
        return PageRequest.of(page, size, Sort.by(orders));
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSort(List<SortParams> sort) {
        this.sort = sort;
    }
}
