package ru.job4j.accident.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {

    @EqualsAndHashCode.Include
    private int id;

    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    private String text;

    @EqualsAndHashCode.Include
    private String address;

    private AccidentType type;

    private Set<Rule> rules;
}
