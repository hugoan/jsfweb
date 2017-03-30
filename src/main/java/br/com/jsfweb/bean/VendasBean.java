package br.com.jsfweb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.jsfweb.dao.DAO;
import br.com.jsfweb.model.Livro;
import br.com.jsfweb.model.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		
		model.setAnimate(true);
		
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2017");

		List<Venda> vendas = getVendas(1234);
		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		ChartSeries vendaSerie2016 = new ChartSeries();
		vendaSerie2016.setLabel("Vendas 2016");
		
		vendas = getVendas(4321);
		for (Venda venda : vendas) {
			vendaSerie2016.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie);
		model.addSeries(vendaSerie2016);

		return model;
	}

	public List<Venda> getVendas(long seed) {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(seed);
		
		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

}
