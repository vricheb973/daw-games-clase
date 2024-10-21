package com.daw.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Juego;
import com.daw.services.JuegoService;

@RestController
@RequestMapping("/juegos")
public class JuegoController {
	
	@Autowired
	private JuegoService juegoService;
	
	@GetMapping
	public ResponseEntity<List<Juego>> list(){
		List<Juego> juegos = this.juegoService.getAll();
		
		return ResponseEntity.ok(juegos);
	}
	
	@GetMapping("/{idJuego}")
	public ResponseEntity<Juego> findOne(@PathVariable("idJuego") int idJuego){
		Optional<Juego> juego = this.juegoService.getJuego(idJuego);
		
		if(juego.isEmpty()) {
			ResponseEntity<Juego> respuesta = new ResponseEntity<Juego>(HttpStatus.NOT_FOUND);
			
			return respuesta;
		}
		else {
			ResponseEntity<Juego> respuesta = new ResponseEntity<Juego>(juego.get() ,HttpStatus.OK);
			
			return respuesta;
		}
	}
	
	@PostMapping
	public ResponseEntity<Juego> create(@RequestBody Juego juego){
		Juego juegoBD = this.juegoService.create(juego);
		
		return new ResponseEntity<Juego>(juegoBD, HttpStatus.CREATED);
	}
	
	@PutMapping("/{idJuego}")
	public ResponseEntity<Juego> update(@PathVariable("idJuego") int idJuego, 
			@RequestBody Juego juego){
		if(idJuego != juego.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if(this.juegoService.getJuego(idJuego).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.juegoService.save(juego));
	}
	
	@DeleteMapping("/{idJuego}")
	public ResponseEntity<Juego> delete(@PathVariable("idJuego") int idJuego){
		if(this.juegoService.delete(idJuego)) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/completar/{idJuego}")
	public ResponseEntity<Juego> completar(@PathVariable("idJuego") int idJuego){
		Optional<Juego> juego = this.juegoService.getJuego(idJuego);
		
		if(juego.isEmpty()) {
			ResponseEntity<Juego> respuesta = new ResponseEntity<Juego>(HttpStatus.NOT_FOUND);
			
			return respuesta;
		}
		else {
			return ResponseEntity.ok(this.juegoService.completar(idJuego));
		}
		
	}
	
	@GetMapping("/genero")
	public ResponseEntity<List<Juego>> findByGenero(@RequestParam("genero") String genero){
		return ResponseEntity.ok(this.juegoService.getByGenero(genero));
	}
	
	@GetMapping("/tipo")
	public ResponseEntity<List<Juego>> findByTipo(@RequestParam("tipo") String tipo){
		return ResponseEntity.ok(this.juegoService.getByTipo(tipo));
	}
	
	@GetMapping("/expansiones")
	public ResponseEntity<List<Juego>> expansiones(){
		return ResponseEntity.ok(this.juegoService.getExpansiones());
	}
	
	
	
	
	
	
	
	
	
	
	
}
