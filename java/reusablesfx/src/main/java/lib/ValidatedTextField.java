package lib;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.TextField;

import java.util.function.Predicate;

public class ValidatedTextField extends TextField {

    private Predicate<String> validator = value -> true;

    private final BooleanProperty valid =
            new SimpleBooleanProperty(true);

    private static final PseudoClass INVALID =
            PseudoClass.getPseudoClass("invalid");

    public ValidatedTextField() {
        textProperty().addListener(
                (observable, oldValue, newValue) -> validate()
        );

        getStyleClass().add("validated-text");
    }

    public void setValidator(Predicate<String> validator) {
        this.validator = validator;
        validate();
    }

    public boolean isValid() {
        return valid.get();
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    private void validate() {
        String value = getText() == null ? "" : getText();

        boolean result = validator.test(value);

        valid.set(result);

        pseudoClassStateChanged(
                INVALID,
                !result
        );
    }
}