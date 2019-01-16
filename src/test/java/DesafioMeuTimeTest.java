import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.codenation.DesafioMeuTimeService;

public class DesafioMeuTimeTest {

	private DesafioMeuTimeService desafioMeuTimeService = new DesafioMeuTimeService();

	@Test
	public void buscarTimes() {
		List<Long> listaTimes = new ArrayList<>();

		listaTimes.add((long) 1);

		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		assertEquals(listaTimes, desafioMeuTimeService.buscarTimes());
	}

	@Test
	public void buscarCapitao() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.now(), 50,
				new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long) 2, (long) 1, "Priscila Martins", LocalDate.now(), 80,
				new BigDecimal(1000000));

		desafioMeuTimeService.definirCapitao((long) 2);

		assertEquals(Long.valueOf(2), desafioMeuTimeService.buscarCapitaoDoTime((long) 1));
	}

	@Test
	public void buscarNomeJogador() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);
		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.now(), 50,
				new BigDecimal(100000));

		assertEquals("Lucas Silva", desafioMeuTimeService.buscarNomeJogador((long) 1));
	}

	@Test
	public void buscarNomeTime() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		assertEquals("SC Internacional", desafioMeuTimeService.buscarNomeTime((long) 1));
	}

	@Test
	public void buscarJogadoresDoTime() {
		List<Long> listaIdJogadoresEsperados = new ArrayList<>();
		listaIdJogadoresEsperados.add((long)1);
		listaIdJogadoresEsperados.add((long)2);
		listaIdJogadoresEsperados.add((long)3);
		
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.now(), 50, new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long) 2, (long) 1, "Priscila Martins", LocalDate.now(), 80, new BigDecimal(1000000));
		desafioMeuTimeService.incluirJogador((long) 3, (long) 1, "Isadora", LocalDate.now(), 60, new BigDecimal(1000000));
		
		assertEquals(listaIdJogadoresEsperados, desafioMeuTimeService.buscarJogadoresDoTime((long)1));
	}
	
	@Test
	public void buscarMelhorJogadorDoTime() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.now(), 50, new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long) 2, (long) 1, "Priscila Martins", LocalDate.now(), 80, new BigDecimal(1000000));
		desafioMeuTimeService.incluirJogador((long) 3, (long) 1, "Isadora", LocalDate.now(), 60, new BigDecimal(1000000));
		
		assertEquals(Long.valueOf(2), desafioMeuTimeService.buscarMelhorJogadorDoTime((long)1));
	}
	
	@Test
	public void buscarJogadorMaisVelho() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.of(1980, 8, 15), 50, new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long) 2, (long) 1, "Priscila Martins", LocalDate.of(1980, 8, 15), 80, new BigDecimal(1000000));
		desafioMeuTimeService.incluirJogador((long) 3, (long) 1, "Isadora", LocalDate.of(1980, 8, 15), 60, new BigDecimal(1000000));
		
		assertEquals(Long.valueOf(1), desafioMeuTimeService.buscarJogadorMaisVelho((long) 1));
	}
	
	@Test
	public void buscarJogadorMaiorSalario() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.of(1980, 8, 15), 50, new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long) 2, (long) 1, "Priscila Martins", LocalDate.of(1980, 8, 15), 80, new BigDecimal(200000));
		desafioMeuTimeService.incluirJogador((long) 3, (long) 1, "Isadora", LocalDate.of(1980, 8, 15), 60, new BigDecimal(300000));
		
		assertEquals(Long.valueOf(3), desafioMeuTimeService.buscarJogadorMaiorSalario((long) 1));
	}
	
	@Test
	public void buscarSalarioDoJogador() {
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", null);

		desafioMeuTimeService.incluirJogador((long) 1, (long) 1, "Lucas Silva", LocalDate.of(1980, 8, 15), 50, new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long) 2, (long) 1, "Priscila Martins", LocalDate.of(1980, 8, 15), 80, new BigDecimal(200000));
		desafioMeuTimeService.incluirJogador((long) 3, (long) 1, "Isadora", LocalDate.of(1980, 8, 15), 60, new BigDecimal(300000));
		
		assertEquals(new BigDecimal(300000), desafioMeuTimeService.buscarSalarioDoJogador((long) 3));
	}

}
