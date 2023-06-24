package sk.fmfi.listng.infrastructure.common.dto;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> implements BaseDto {

    public List<T> data = new ArrayList<>();

    public int page;

    public long totalEntries;

    public int totalPages;

    public PageResponse() {
    }

    public PageResponse(Page<T> page) {
        this.data = page.getContent();
        this.page = page.getNumber();
        this.totalEntries = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public PageResponse(Page page, List<T> data) {
        this.data = data;
        this.page = page.getNumber();
        this.totalEntries = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
    
    public PageResponse(PageResponse copy, List<T> assembled) {
        this.data = assembled;
        this.page = copy.page;
        this.totalEntries = copy.totalEntries;
        this.totalPages = copy.totalPages;
    }
    
    public static PageResponse empty() {
        PageResponse response = new PageResponse();
        response.page = 0;
        response.totalEntries = 0;
        return response;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalEntries(long totalEntries) {
        this.totalEntries = totalEntries;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
