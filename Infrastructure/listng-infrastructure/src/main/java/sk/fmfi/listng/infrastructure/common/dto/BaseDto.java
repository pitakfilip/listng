package sk.fmfi.listng.infrastructure.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public interface BaseDto extends Serializable {
}
