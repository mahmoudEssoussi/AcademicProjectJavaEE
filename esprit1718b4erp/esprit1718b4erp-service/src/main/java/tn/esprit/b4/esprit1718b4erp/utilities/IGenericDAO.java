package tn.esprit.b4.esprit1718b4erp.utilities;

import java.util.List;

import javafx.collections.ObservableList;
import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;

public interface IGenericDAO<T> {
	public void save(T entity);

	public void delete(T entity);

	public T update(T entity);

	public T find(int entityID);

	public List<T> findAll();


}
