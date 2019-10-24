package ru.otus.kasymbekovPN.HW13.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NonFinal
    long id;

    @NonFinal
    String name;
}
