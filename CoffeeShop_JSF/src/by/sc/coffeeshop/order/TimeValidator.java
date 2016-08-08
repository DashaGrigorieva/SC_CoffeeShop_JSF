package by.sc.coffeeshop.order;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by User on 05.08.2016.
 */
@FacesValidator("timeValidator")
public class TimeValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        int toTime = Integer.parseInt(o.toString());

        UIInput uiInputFromTime = (UIInput) uiComponent.getAttributes().get("fromTime");
        int fromTime = Integer.parseInt(uiInputFromTime.getValue().toString());

        if (fromTime < toTime) {
            return;
        } else {
            throw new ValidatorException(new FacesMessage("Введите корректное время"));
        }
    }
}

