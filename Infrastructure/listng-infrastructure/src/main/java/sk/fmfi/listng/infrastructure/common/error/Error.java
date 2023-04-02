package sk.fmfi.listng.infrastructure.common.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public interface Error {
    
    String getErrorMessage();
}
