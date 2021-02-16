package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RestController /* Para a classe aceitar métodos REST */
@RequestMapping(value = "/usuario") /* Estou mapeando para /usuario */
public class IndexController {

	/* Injeção de dependencia do nosso repositorio de usuario no controller. */
	@Autowired
	private UsuarioRepository usuarioRepository;

	/* Método para consultar usuário por id do banco de dados. */
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json")
	public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id") Long id,
											 @PathVariable(value = "venda") Long venda) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		/* O retorno seria um relatório. */
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	/* Método para consultar usuário por id do banco de dados. */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> readById(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	/* Método para consultar todos os usuarios do banco de dados */
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> readAll() {
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

}
