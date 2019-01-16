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

		Time resultTime = buscarTime(idTime);

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
		Time time = buscarTime(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		if (time.getIdJogador() == null) {
			throw new CapitaoNaoInformadoException();
		}

		return time.getIdJogador();
	}

	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);

		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogador.getNome();
	}

	public String buscarNomeTime(Long idTime) {
		Time time = buscarTime(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		return time.getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = buscarTime(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		return buscarIdJogadoresPorTime(idTime);
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = buscarTime(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		return buscarMelhorJogadorPorTime(idTime);
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = buscarTime(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		return buscarJogadorMaisVelhoPorTime(idTime);
	}

	public List<Long> buscarTimes() {
		return listaTimes.stream().sorted(Comparator.comparingLong(Time::getId)).map(Time::getId)
				.collect(Collectors.toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = buscarTime(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		return buscarJogadorComMaiorSalarioPorTime(idTime);
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);

		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		return buscarSalarioJogador(idJogador);
	}

	public List<Long> buscarTopJogadores(Integer top) {
		return listaJogadores.stream().sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
				.map(Jogador::getId).limit(top).collect(Collectors.toList());
	}

	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = buscarTime(timeDaCasa);
		Time timeFora = buscarTime(timeDeFora);
		
		if (timeCasa == null) {
			throw new TimeNaoEncontradoException();
		}

		if (timeFora == null) {
			throw new TimeNaoEncontradoException();
		}
		
		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getCorUniformeSecundario();
		}
		
		return timeFora.getCorUniformePrincipal();

	}

	private Time buscarTime(Long idTime) {
		return listaTimes.stream().filter(x -> x.getId().equals(idTime)).findFirst().orElse(null);
	}

	private Jogador buscarJogador(Long idJogador) {
		return listaJogadores.stream().filter(x -> x.getId().equals(idJogador)).findFirst().orElse(null);
	}

	private List<Long> buscarIdJogadoresPorTime(Long idTime) {
		return listaJogadores.stream().filter(x -> x.getIdTime().equals(idTime)).map(Jogador::getId).sorted()
				.collect(Collectors.toList());
	}

	private Long buscarMelhorJogadorPorTime(Long idTime) {
		return listaJogadores.stream().filter(x -> x.getIdTime().equals(idTime))
				.max(Comparator.comparingInt(Jogador::getNivelHabilidade)).map(Jogador::getId).get();
	}

	private Long buscarJogadorMaisVelhoPorTime(Long idTime) {
		return listaJogadores.stream().filter(x -> x.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getId))
				.max(Comparator.comparing(Jogador::getDataNascimento).reversed()).map(Jogador::getId).get();
	}

	private Long buscarJogadorComMaiorSalarioPorTime(Long idTime) {
		return listaJogadores.stream().filter(x -> x.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getId)).max(Comparator.comparing(Jogador::getSalario))
				.map(Jogador::getId).get();
	}

	private BigDecimal buscarSalarioJogador(Long idJogador) {
		return listaJogadores.stream().filter(x -> x.getId().equals(idJogador)).map(Jogador::getSalario).findFirst()
				.get();
	}
}
