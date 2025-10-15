//package com.cardgame.cardgameserver.card;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//public class CardType {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @NotNull
//    @NotEmpty
//    @Size(min = 1, max = 100)
//    private String name;
//}
