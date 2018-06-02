package tn.esprit.b4.esprit1718b4erp.mBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote;
@ManagedBean
public class billstat implements Serializable {
	private BarChartModel barModel;
	protected List <Caisse> caissestat=new ArrayList<Caisse>();
	@EJB
	private CaisseServiceRemote CaisseServices;
	 @PostConstruct
	    public void init() {
	        createBarModels();
	    }
	 public BarChartModel getBarModel() {
	        return barModel;
	    }
	 private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	        caissestat=CaisseServices.findAll();
	    	for (Caisse C:caissestat){
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Cash-desk");
	        boys.set(C.getDate(), C.getSomme_caisse());
	        model.addSeries(boys);
	        }
	 
	      
	 
	       
	        
	         
	        return model;
	    }
	 
	 private void createBarModels() {
	        createBarModel();
	     
	    }
	 private void createBarModel() {
	        barModel = initBarModel();
	         
	        barModel.setTitle("Bar Chart");
	        barModel.setLegendPosition("ne");
	         
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Gender");
	         
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Births");
	        yAxis.setMin(0);
	        yAxis.setMax(200);
	    }
	
	
}
