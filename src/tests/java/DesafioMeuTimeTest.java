import static org.junit.jupiter.api.Assertions.assertEquals;

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
		desafioMeuTimeService.incluirJogador((long)1, (long)1, "Lucas Silva", LocalDate.now(), 50, new BigDecimal(100000));
		desafioMeuTimeService.incluirJogador((long)2, (long)1, "Priscila Martins", LocalDate.now(), 80, new BigDecimal(1000000));
		
		desafioMeuTimeService.incluirTime((long) 1, "SC Internacional", LocalDate.now(), "Vermelho", "Branco", (long)2);
		
		//assertEquals(Long.valueOf(2), desafioMeuTimeService.definirCapitao(idJogador););
	}


}
