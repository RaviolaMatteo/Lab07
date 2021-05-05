package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;

	private List <PowerOutages> lista;
	private List <PowerOutages> soluzione;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public List<PowerOutages> trovaSequenza(Nerc ner,int anni,int ore){
		podao = new PowerOutageDAO();
		List <PowerOutages> lista = new LinkedList<>(podao.lista(ner));
		Collections.sort(lista);
		List<PowerOutages> aa=new LinkedList<PowerOutages>();
		cerca(aa,anni,ore);
		return soluzione;
	}

	private void cerca(List<PowerOutages> parziale,int anni, int ore) {
			//caso terminale
			int nummax = calcolaPiuAlto(parziale, anni, ore);
			if (soluzione==null ||nummax > calcolaPiuAlto(soluzione,anni,ore)) {
				soluzione = new ArrayList<>(parziale);
			}
			for (PowerOutages prova: lista) {
				if (!parziale.contains(prova)) {
					parziale.add(prova);
					if (aggiuntaValida(prova, parziale,anni,ore)) {
						cerca(parziale, anni, ore);
				
					}
					parziale.remove(prova);
				}
			}			
		}
	private int calcolaPiuAlto(List<PowerOutages> parziale, int anni, int ore) {
		int soggetti=0;
		for (PowerOutages po:parziale) {
			soggetti=soggetti+po.getAffectedPeople();
		}
		return soggetti;
	}
	
private boolean aggiuntaValida(PowerOutages pow, List<PowerOutages> parziale, int anni, int ore) {
	if (parziale.size() >=2 ) {
		int y1 = parziale.get(0).getYear();
		int y2 = parziale.get(parziale.size() - 1).getYear();
		if ((y2 - y1) > anni) {
			return false;
		}
	}
		long count=0;
		for (PowerOutages po:parziale) {
			count=count+po.getOutageDuration();
		}
		if (count>ore)
			return false;
			
		return true;
		
	}

}
