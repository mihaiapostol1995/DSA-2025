//package org.example;
//
//import jakarta.persistence.Entity;
//import jakarta.validation.Valid;
//import jakarta.validation.Validation;
//import jakarta.validation.ValidatorFactory;
//import jakarta.validation.constraints.*;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//@Builder(toBuilder = true)
//@Entity
//@Data
//public class DecimalAnnotation {
//
//    @DecimalMin("0")
//    @DecimalMax("1")
//    @Digits(integer = 1, fraction = 1)
//    public BigDecimal number;
//
//    public void setNumber(@Valid BigDecimal number) {
//        this.number = number;
//    }
//}
//
//class TestMe {
//    public static void main(String[] args) {
//        DecimalAnnotation decimalAnnotation = DecimalAnnotation.builder().build();
//        decimalAnnotation.setNumber(BigDecimal.valueOf(3));
//        System.out.println(decimalAnnotation.number);
//
//        System.out.println(Validation.buildDefaultValidatorFactory().getValidator().validateProperty(decimalAnnotation, "number"));
//
//        var user = UserInfo.builder()
//                .id(1)
//                .build();
//        System.out.println(user.getNick());
//    }
//}
//
//@Getter
//@NoArgsConstructor
//class UserInfo {
//
//    private int id;
//    private String nick = "nick";
//    private boolean isEmailConfirmed = true;
//
//    @Builder
//    //@SuppressWarnings("unused")
//    private UserInfo(int id, String nick, Boolean isEmailConfirmed) {
//        this.id = id;
//        this.nick = nick;
//        this.isEmailConfirmed = Optional.ofNullable(isEmailConfirmed).orElse(this.isEmailConfirmed);
//    }
//}