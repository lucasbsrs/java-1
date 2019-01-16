package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeService {

	List<Time> listaTimes;
	List<Jogador> listaJogadores;

	public DesafioMeuTimeService() {
		listaTimes = new ArrayList<>();
		listaJogadores = new ArrayList<>();
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario, Long idJogador) {
		Time result = buscarTime(id);

		if (result != null) {
			throw new IdentificadorUtilizadoException();
		}

		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, idJogador);
		listaTimes.add(time);
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		Jogador resultJogador = buscarJogador(id);

		if (resultJogador != null) {
			throw new IdentificadorUtilizadoException();
		}

		Time resultTime = buscarTime(id);

		if (resultTime == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		listaJogadores.add(jogador);
	}

	public void definirCapitao(Long idJogador) {
		Jogador resultJogador = buscarJogador(idJogador);

		if (resultJogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		Time resultTime = buscarTime(resultJogador.getIdTime());
		listaTimes.stream().filter(x -> x.getId().equals(resultTime.getId())).findFirst().get().setIdJogador(idJogador);
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		Time resultTime = buscarTime(idTime);

		if (resultTime == null) {
			throw new TimeNaoEncontradoException();
		}

		Long idJogadorCapitao = buscarIdJogadorCapitao(idTime);
		if (idJogadorCapitao == null) {
			throw new CapitaoNaoInformadoException();
		}

		return idJogadorCapitao;
	}

	public List<Long> buscarTimes() {
		return listaTimes.stream().sorted(Comparator.comparingLong(Time::getId)).map(Time::getId)
				.collect(Collectors.toList());
	}

	private Time buscarTime(Long idTime) {
		return listaTimes.stream().filter(x -> x.getId().equals(idTime)).findFirst().orElse(null);
	}

	private Jogador buscarJogador(Long idJogador) {
		return listaJogadores.stream().filter(x -> x.getId().equals(idJogador)).findFirst().orElse(null);
	}

	private Long buscarIdJogadorCapitao(Long idTime) {
		return listaTimes.stream().filter(x -> x.getId().equals(idTime)).map(Time::getIdJogador).findFirst().get();
	}

}
