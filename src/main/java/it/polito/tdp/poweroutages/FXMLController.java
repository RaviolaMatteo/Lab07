/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbNerc"
    private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    @FXML
    void doRun(ActionEvent event) {
    	txtResult.clear();
    	Nerc language=cmbNerc.getSelectionModel().getSelectedItem();
    	int anni= Integer.parseInt(txtYears.getText()) ;
    	int ore= Integer.parseInt(txtHours.getText()) ;
    	List<PowerOutages> listino=new LinkedList<PowerOutages>(model.trovaSequenza(language,anni,ore));
    		int soggetti=0;
    		for (PowerOutages po:listino) {
    			soggetti=soggetti+po.getAffectedPeople();
    		}
    	txtResult.appendText("Tot people affected: "+soggetti+"\n");
    	long oretot=0;
    	for (PowerOutages po:listino) {
			oretot=oretot+po.getOutageDuration();
		}
    	txtResult.appendText("Tot hours of outgage: "+oretot+"\n");
    	
    	StringBuilder sb = new StringBuilder();
    	for(PowerOutages pow:listino) {
    		sb.append(String.format("%-23s ", pow.getOutageStart() ));
    		sb.append(String.format("%-23s ", pow.getOutageEnd()));
    		sb.append(String.format("%-5d ", pow.getOutageDuration()));
    		sb.append(String.format("%-9d\n", pow.getAffectedPeople()));
    	}
    	txtResult.appendText(sb.toString());
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	ObservableList <Nerc> language=FXCollections.observableArrayList(model.getNercList());
        cmbNerc.setItems(language);
    }
}
