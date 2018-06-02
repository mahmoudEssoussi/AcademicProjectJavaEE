package tn.esprit.b4.esprit1718b4erp.mBeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.el.ValueExpression;

import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.Product;


@FacesConverter(value = "OrderConverter")
public class OrderConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String Id) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{orderBean}", OrderBean.class);

        OrderBean OrderBean = (OrderBean)vex.getValue(ctx.getELContext());
    
        return OrderBean.getOrder(Integer.valueOf(Id));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object ord) {
 
        return ((Order)ord).getId().toString();
    }
}