package by.sc.coffeeshop.order;

import by.sc.coffeeshop.dao.OrderDAO;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by User on 05.08.2016.
 */
@FacesConverter("orderConverter")
public class OrderConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        UIInput uiInputToTime = (UIInput) uiComponent.getAttributes().get("toTime");
        UIInput uiInputFromTime = (UIInput) uiComponent.getAttributes().get("fromTime");
        UIInput uiInputCoffeeType = (UIInput) uiComponent.getAttributes().get("coffeeType");
        UIInput uiInputDelivery = (UIInput) uiComponent.getAttributes().get("delivery");
        UIInput uiInputAmount = (UIInput) uiComponent.getAttributes().get("amount");
        int fromTime = Integer.parseInt(uiInputFromTime.getValue().toString());
        int toTime = Integer.parseInt(uiInputToTime.getValue().toString());
        double amount = Double.parseDouble(uiInputAmount.getValue().toString());
        String coffeeType = uiInputCoffeeType.getValue().toString();
        Boolean delivery;
        if (uiInputDelivery.getValue().toString().equals("true")) {
            delivery = true;
        } else {
            delivery = false;
        }
        CoffeeOrder coffeeOrder = new CoffeeOrder(coffeeType, amount, delivery, fromTime, toTime);
        return coffeeOrder;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
