package sk.fmfi.listng.infrastructure.common.dto;

import sk.fmfi.listng.domain.administration.MultiLangText;

public class MultiLangTextDto implements BaseDto {
    public String SK = "";
    public String EN = "";

    public MultiLangTextDto() {
    }

    public MultiLangTextDto(MultiLangText obj) {
        this.SK = obj.getSK();
        this.EN = obj.getEN();
    }

    public static MultiLangText fromDto(MultiLangTextDto dto) {
        return new MultiLangText(dto.SK, dto.EN);
    }
}
