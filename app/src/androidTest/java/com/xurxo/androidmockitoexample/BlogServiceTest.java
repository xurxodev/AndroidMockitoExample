package com.xurxo.androidmockitoexample;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by xurxo on 16/11/2014.
 */
public class BlogServiceTest extends AndroidTestCase {

    public void testCreateInstance() {
        Logger loggerMock = mock(Logger.class);
        HtmlValidator htmlValidatorMock = mock(HtmlValidator.class);

        BlogService blog = new BlogService(loggerMock, htmlValidatorMock);

        Assert.assertNotNull(blog);
    }

    public void testPublishPostShouldThrowExceptionForNotValidHtml() {

        try {
            Logger loggerMock = mock(Logger.class);

            HtmlValidator htmlValidatorMock = mock(HtmlValidator.class);
            when(htmlValidatorMock.isValid(any(String.class))).thenReturn(false);

            BlogService blog = new BlogService(loggerMock, htmlValidatorMock);

            blog.publishPost("<html");

            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expectedException) {
            String expectedMessage = "html not valid";
            Assert.assertTrue("error message should contain message " + expectedMessage,
                    expectedException.getMessage().contains(expectedMessage));
        }
    }

    public void testPublishPostShouldReturnTrue() {
        Logger loggerMock = mock(Logger.class);

        HtmlValidator htmlValidatorMock = mock(HtmlValidator.class);
        when(htmlValidatorMock.isValid(any(String.class))).thenReturn(true);

        BlogService blog = new BlogService(loggerMock, htmlValidatorMock);

        boolean published = blog.publishPost("valid html");

        verify(htmlValidatorMock).isValid(any(String.class));
        Assert.assertTrue("Excepted return true in publishPost method",published);
    }
}
