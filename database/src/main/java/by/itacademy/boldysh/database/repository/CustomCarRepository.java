package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.Car;
import com.sun.javafx.scene.control.behavior.OptionalBoolean;
import javafx.beans.binding.BooleanExpression;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.function.Function;


@Data
public class CustomCarRepository {

    private BooleanExpression predicate;

    public CustomCarRepository(BooleanExpression predicate) {
        this.predicate = predicate;
    }

    public <T> CustomCarRepository notNullAll(Function<T, BooleanExpression> expressionFunction, T value) {
        if (value != null) {
            return new CustomCarRepository(predicate.and(expressionFunction.apply(value)));
        }
        return this;
    }



}
