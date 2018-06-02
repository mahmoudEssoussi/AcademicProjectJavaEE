package tn.esprit.b4.esprit1718b4erp.mBeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.el.ValueExpression;


import tn.esprit.b4.esprit1718b4erp.entities.Product;


@FacesConverter(value = "ProductConverter")
public class ProductConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String Id) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{productBean}", ProductBean.class);

        ProductBean ProductBean = (ProductBean)vex.getValue(ctx.getELContext());
    
        return ProductBean.getProduct(Integer.valueOf(Id));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object Pro) {
 
        return ((Product)Pro).getIdProduct().toString();
    }
}