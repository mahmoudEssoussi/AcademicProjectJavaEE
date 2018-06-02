package tn.esprit.b4.esprit1718b4erp.mBeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.el.ValueExpression;


import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;








@FacesConverter(value = "beerConverter")
public class PmsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String IdStock) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{primaryStock}", PrimaryStock.class);

        PrimaryStock beers = (PrimaryStock)vex.getValue(ctx.getELContext());
        System.out.println(beers.getBeer(Integer.valueOf(IdStock)));
        return beers.getBeer(Integer.valueOf(IdStock));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object beer) {
 
        return ((PrimaryMaterialsStock)beer).getIdStock().toString();
    }
}