package sk.fmfi.listng.infrastructure.common;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;

public enum OperationResult implements BaseDto {
    SUCCESS,
    FAILURE, // Genericka chyba
    EXISTS, // Pri CREATE entity uz moze existovat (e.g. User s rovnakym email)
    NONEXISTENT // Pri operacii s entitou, ktora neexistuje
}
