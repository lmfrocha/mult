package br.com.gm.api.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Categoria {
	PERECIVEL("Perecível"),
	NAO_PERECIVEL("Não Perecível");
	
	private String descricao;
	
	private Categoria (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static List<Map<String, String>> getCategorias() {
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		for (Categoria categoria : Categoria.values()) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id", categoria.toString());
			map.put("descricao", categoria.getDescricao());
			maps.add(map);
		}
		return maps;
	}
		
}
