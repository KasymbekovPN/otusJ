package ru.otus.kasymbekovPN.HW13.server.domain;

import lombok.*;
import lombok.experimental.NonFinal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//<
@ToString
//<
public class User {

    @NonFinal
    long id;

    @NonFinal
    String login;

    @NonFinal
    String password;
}
//<
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.NonFinal;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//
//    @NonFinal
//    long id;
//
//    @NonFinal
//    String name;
//}
