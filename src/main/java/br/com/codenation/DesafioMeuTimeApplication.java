package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private DesafioMeuTimeService desafioMeuTimeService = new DesafioMeuTimeService();
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		desafioMeuTimeService.incluirTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		desafioMeuTimeService.incluirJogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		desafioMeuTimeService.definirCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		return desafioMeuTimeService.buscarCapitaoDoTime(idTime);
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return desafioMeuTimeService.buscarNomeJogador(idJogador);
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return desafioMeuTimeService.buscarNomeTime(idTime);
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return desafioMeuTimeService.buscarJogadoresDoTime(idTime);
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return desafioMeuTimeService.buscarMelhorJogadorDoTime(idTime);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		return desafioMeuTimeService.buscarJogadorMaisVelho(idTime);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return desafioMeuTimeService.buscarTimes();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		return desafioMeuTimeService.buscarJogadorMaiorSalario(idTime);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return desafioMeuTimeService.buscarSalarioDoJogador(idJogador);
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return desafioMeuTimeService.buscarTopJogadores(top);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		return desafioMeuTimeService.buscarCorCamisaTimeDeFora(timeDaCasa, timeDeFora);
	}

}
