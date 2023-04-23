package sk.fmfi.listng.infrastructure.common.dto;

import sk.fmfi.listng.domain.administration.MultiLangText;

public class MultiLangTextDto implements BaseDto{
    public String SK;
    public String EN;
    
    public static MultiLangTextDto toDto(MultiLangText obj) {
        MultiLangTextDto dto = new MultiLangTextDto();
        dto.SK = obj.SK;
        dto.EN = obj.EN;
        return dto;
    }
    
    public static MultiLangText fromDto(MultiLangTextDto dto){
        return new MultiLangText(dto.SK, dto.EN);
    }
}
