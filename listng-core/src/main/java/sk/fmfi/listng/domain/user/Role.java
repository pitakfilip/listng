package sk.fmfi.listng.domain.user;

/**
 * Enum Role určuje úživateľov vzťah a jeho prístupy k danému kurzu.
 * ROOT - administrátor systému (role sa pripisuje množine administrátorom automaticky k všetkým kurzom)
 * TEACHER - vyučujúci s právom spravovať iba svoje kurzy a read-only práva k ostatným kurzom
 * EVALUATOR - rola určená pre zvolených študentov s prístupom IBA opravovať riešenia študentov na úlohy
 * STUDENT - študent zúčastnujúci sa vyučby
 * PENDING_STUDENT - študent čakajúci na prijatie do kurzu úživateľom s role ROOT alebo TEACHER
 * DENIED_STUDENT - študent s odmietnutým prístupom do kurzu
 */

public enum Role {
    ROOT,
    TEACHER,
    EVALUATOR,
    STUDENT,
    PENDING_STUDENT,
    DENIED_STUDENT
}
