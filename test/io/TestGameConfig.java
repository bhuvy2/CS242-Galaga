package io;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class TestGameConfig {

	@Test
	public void testGet() {
		String path = "Yesh";
		String key = "bossAlienKey";
		Properties prop = mock(Properties.class);
		GameConfig.setProperties(prop);
		when(prop.getProperty(key)).thenReturn(path);
		assertEquals("Yesh", GameConfig.getBossKey());
	}

}
