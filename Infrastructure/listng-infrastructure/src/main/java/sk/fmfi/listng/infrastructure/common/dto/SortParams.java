package sk.fmfi.listng.infrastructure.common.dto;

import org.springframework.data.domain.Sort;

import java.util.List;

public class SortParams implements BaseDto {

    public String field;

    public Sort.Direction direction = Sort.Direction.DESC;

    public SortParams() {
    }

    public SortParams(String field) {
        this.field = field;
    }

    public SortParams(String field, boolean ascending) {
        this.field = field;
        this.direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public Sort.Order compile() {
        return new Sort.Order(direction, field);
    }

    public static List<Sort.Order> compileParams(List<SortParams> params) {
        return params.stream()
                .filter(param -> param.direction != null && param.field != null)
                .map(SortParams::compile)
                .toList();
    }
}

