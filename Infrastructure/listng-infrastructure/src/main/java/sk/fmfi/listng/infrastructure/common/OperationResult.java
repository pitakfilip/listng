package sk.fmfi.listng.infrastructure.common;

public enum OperationResult implements BaseDto {
    SUCCESS,
    FAILURE, // Genericka chyba
    EXISTS, // Pri CREATE entity uz moze existovat (e.g. User s rovnakym email)
    NONEXISTENT // Pri operacii s entitou, ktora neexistuje
}
