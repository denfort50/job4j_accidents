package ru.job4j.accident.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccidentType {

    @EqualsAndHashCode.Include
    @NonNull
    private int id;

    private String name;
}
