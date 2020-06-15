package com.renato.controleFinancas.resources;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.controleFinancas.exception.ErroAutenticacao;
import com.renato.controleFinancas.exception.RegraNegocioException;
import com.renato.controleFinancas.model.DTO.UsuarioDTO;
import com.renato.controleFinancas.model.entity.Usuario;
import com.renato.controleFinancas.service.LancamentoService;
import com.renato.controleFinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResouce {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LancamentoService lancamentoService;

	

	@SuppressWarnings("rawtypes")
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		}catch(ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(dto.getSenha()).build();
		try {
			Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("{id}/saldo")
	public ResponseEntity obterSaldo(@PathVariable("id") Long id) {
Optional<Usuario> usuario = usuarioService.obterPorId(id);
		
		if(!usuario.isPresent()) {
			return new ResponseEntity( HttpStatus.NOT_FOUND );
		}
		
		BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
	}

}
