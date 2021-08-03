package com.url.shortner;

import com.url.shortner.core.EncodeDecodeTinyURL;
import com.url.shortner.repository.URLRepository;
import com.url.shortner.repository.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.assertEquals;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(SpringJUnit4ClassRunner.class)
public class EncodeDecodeTinyURLTest {
    @InjectMocks
    EncodeDecodeTinyURL encodeDecodeTinyURL;
    @Mock
    URLRepository urlRepository;

    @Test
    public void testEncode() {
        when(urlRepository.save(any(Url.class))).thenReturn(null);
        when(urlRepository.count()).thenReturn(1L);
        String val =  encodeDecodeTinyURL.encode("https://stackoverflow.com/questions/35550019/delete-records-from-more-than-1-year-ago");
       assertEquals(val, "Dcc");
    }

    @Test
    public void testDecode() {
        Url url = new Url();
        url.setId("Dcc");
        url.setUrl("https://stackoverflow.com/questions/35550019/delete-records-from-more-than-1-year-ago");
        when(urlRepository.getById(any(String.class))).thenReturn(url);
        String val =  encodeDecodeTinyURL.decode("Dcc");
        assertEquals(val, "https://stackoverflow.com/questions/35550019/delete-records-from-more-than-1-year-ago");
    }

}
