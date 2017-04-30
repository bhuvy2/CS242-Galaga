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
	
	@InjectMocks GameConfig conf;
	
	@Mock Properties prop;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGet() {
		String path = "Yesh";
		String key = "bossAlienKey";
		when(prop.getProperty(key)).thenReturn(path);
		assertEquals(null, conf.getBossKey());
	}

}
