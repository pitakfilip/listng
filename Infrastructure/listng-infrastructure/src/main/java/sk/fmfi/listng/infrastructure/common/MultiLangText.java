package sk.fmfi.listng.infrastructure.common;

import java.io.Serializable;

public class MultiLangText implements Serializable {
    
    private String SK;

    private String EN;
    
    public MultiLangText(){}
    
    public MultiLangText(String sk, String en) {
        this.SK = sk;
        this.EN = en;
    }
    
    public MultiLangText(MultiLangText from){
        this.SK = from.SK;
        this.EN = from.EN;
    }

    public String getSK() {
        return SK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    public String getEN() {
        return EN;
    }

    public void setEN(String EN) {
        this.EN = EN;
    }
}
