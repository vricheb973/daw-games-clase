package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.JuegoCrudRepository;
import com.daw.persistence.entities.Juego;
import com.daw.persistence.entities.enums.Tipo;

@Service
public class JuegoService {
	
	@Autowired
	private JuegoCrudRepository juegoCrudRepository;
	
	public List<Juego> getAll() {
		return (List<Juego>) this.juegoCrudRepository.findAll();
	}
	
	public Optional<Juego> getJuego(int idJuego){
		return this.juegoCrudRepository.findById(idJuego);
	}
	
	public Juego create(Juego juego) {
		juego.setCompletado(false);
		
		return this.juegoCrudRepository.save(juego);
	}

	public Juego save(Juego juego) {
		return this.juegoCrudRepository.save(juego);
	}
	
	public boolean delete(int idJuego) {
		boolean res = false;
		
		if(this.juegoCrudRepository.findById(idJuego).isPresent()) {
			this.juegoCrudRepository.deleteById(idJuego);
			res = true;
		}
		
		return res;
	}
	
	public Juego completar(int idJuego) {
		Juego juego = this.juegoCrudRepository.findById(idJuego).get();
		
		if(juego.getCompletado()) {
			juego.setCompletado(false);
		}
		else {
			juego.setCompletado(true);
		}
		
		Juego juegoBD = this.juegoCrudRepository.save(juego);
		
		return juegoBD;
	}
	
	public List<Juego> getByGenero(String genero){
		return this.juegoCrudRepository.findByGeneroContaining(genero);
	}
	
	public List<Juego> getByTipo(String tipo){
		Tipo tipoEnum = Tipo.valueOf(tipo);
		
		return this.juegoCrudRepository.findByTipo(tipoEnum);
	}
	
	public List<Juego> getExpansiones(){
		return this.juegoCrudRepository.findByTipo(Tipo.EXPANSION);
	}
	
	
	
	
	
	
	
	
	
	
}
