package sk.fmfi.listng.infrastructure.common.dto;

public class PagingRequest {
    
    private int limit;

    private int offset;

    public PagingRequest() {
    }

    public PagingRequest(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PagingRequest{" +
                "limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
