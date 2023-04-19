package digitalinnovation.one.padroesprojetospring.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import digitalinnovation.one.padroesprojetospring.model.Cliente;
import digitalinnovation.one.padroesprojetospring.model.Endereco;
import digitalinnovation.one.padroesprojetospring.repository.ClienteRepository;
import digitalinnovation.one.padroesprojetospring.repository.EnderecoRepository;
import digitalinnovation.one.padroesprojetospring.service.ClienteService;
import digitalinnovation.one.padroesprojetospring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ViaCepService viaCepService;

	@Override
	public Iterable<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub
		Optional<Cliente> buscarPorId = clienteRepository.findById(id);
		return buscarPorId.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		// TODO Auto-generated method stub
		salvarClienteComCep(cliente);

	}

	private void salvarClienteComCep(Cliente cliente) {
		// TODO Auto-generated method stub
		
		String cep = cliente.getEndereco().getCep();
		Endereco consultarCep = viaCepService.consultarCep(cep);
		if(consultarCep.getCep() != null) {
			cliente.setEndereco(consultarCep);
			enderecoRepository.save(consultarCep);
			clienteRepository.save(cliente);
		}
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
		if (clienteEncontrado.isPresent()) {
			cliente.setId(clienteEncontrado.get().getId());
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

}
