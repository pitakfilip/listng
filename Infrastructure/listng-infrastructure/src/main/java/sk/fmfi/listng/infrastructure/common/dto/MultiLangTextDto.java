package sk.fmfi.listng.infrastructure.common.dto;

public class MultiLangTextDto implements BaseDto {
    public String SK = "";
    public String EN = "";

    public MultiLangTextDto() {
    }

    public MultiLangTextDto(MultiLangText obj) {
        this.SK = obj.getSK();
        this.EN = obj.getEN();
    }

    public MultiLangTextDto(String sk, String en) {
        this.SK = sk;
        this.EN = en;
    }
    
    public static MultiLangText fromDto(MultiLangTextDto dto) {
        return new MultiLangText(dto.SK, dto.EN);
    }
}
