package digitalinnovation.one.padroesprojetospring.service;

import org.springframework.stereotype.Service;

import digitalinnovation.one.padroesprojetospring.model.Cliente;

@Service
public interface ClienteService {

	Iterable<Cliente> buscarTodos();

	Cliente buscarPorId(Long id);

	void inserir(Cliente cliente);

	void atualizar(Long id, Cliente cliente);

	void deletar(Long id);

}
