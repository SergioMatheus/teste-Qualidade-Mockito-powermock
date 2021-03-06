package br.uscsal.testequalidade20162.radio.business;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.uscsal.testequalidade20162.radio.domain.Album;
import br.uscsal.testequalidade20162.radio.domain.Musica;
import br.uscsal.testequalidade20162.radio.enums.TipoMidia;
import br.uscsal.testequalidade20162.radio.exceptions.RegistroDuplicadoException;
import br.uscsal.testequalidade20162.radio.exceptions.RegistroNaoEncontradoException;
import br.uscsal.testequalidade20162.radio.persistence.AlbumDao;
import br.uscsal.testequalidade20162.radio.persistence.MusicaDao;
import br.uscsal.testequalidade20162.radio.util.DateUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AlbumDao.class, MusicaDao.class })
public class RadioBOUnitarioTest {

	private final String NOME_ALBUM = "A arte do insulto";
	private final String NOME_MUSICA = "Estamos todos bebados";
	
	// Metodo a ser testado: public static void adicionarMusica(String
	// tituloAlbum, String nomeMusica) throws
	// RegistroNaoEncontradoException,
	// Verificar se a inclusÃ£o de uma musica duplicada em um album retorna a
	// exceção adequada.
	// LEMBRE-SE DE QUE ESTE TESTE DEVE SER UNITÁRIO!!!

	@Before
	public void setup() throws Exception {

		PowerMockito.mockStatic(AlbumDao.class);
		PowerMockito.mockStatic(MusicaDao.class);
		
		String datas = "06/06/2006";
		DateUtil dateutil = new DateUtil();
		Date data = dateutil.parse(datas);
		
		
		Musica m1 = new Musica(NOME_MUSICA, 3, "Matanza");		
		Album a1 = new Album(NOME_ALBUM, data, TipoMidia.DIGITAL);
		
		PowerMockito.when(AlbumDao.class, "buscarPorTitulo", NOME_ALBUM ).thenReturn(a1);
		PowerMockito.when(MusicaDao.class, "buscarPorNome" , NOME_MUSICA).thenReturn(m1);
		
		RadioBO.adicionarMusica(NOME_ALBUM, NOME_MUSICA);
	}

	@Test(expected = RegistroDuplicadoException.class)
	public void testeUnitarioAdicionarMusicaRegistroDuplicado()
			throws RegistroNaoEncontradoException, RegistroDuplicadoException {
		
		RadioBO.adicionarMusica(NOME_ALBUM, NOME_MUSICA);
		
	}
}