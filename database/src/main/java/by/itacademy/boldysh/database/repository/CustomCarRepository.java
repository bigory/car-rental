//package by.itacademy.boldysh.database.repository;
//
//import javafx.beans.binding.BooleanExpression;
//import lombok.Data;
//
//import java.util.function.Function;
//
//
//@Data
//public class CustomCarRepository {
//
//    private BooleanExpression predicate;
//
//    public CustomCarRepository(BooleanExpression predicate) {
//        this.predicate = predicate;
//    }
//
//    public <T> CustomCarRepository notNullAll(Function<T, BooleanExpression> expressionFunction, T value) {
//        if (value != null) {
//            return new CustomCarRepository(predicate.and(expressionFunction.apply(value)));
//        }
//        return this;
//    }
//}
