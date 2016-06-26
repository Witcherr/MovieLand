package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.enums.SortType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilTest {

    @Test
    public void checkEnumContainsValueTest(){
        assertTrue(Util.checkEnumContainsValue(SortType.class, "asc"));
        assertTrue(Util.checkEnumContainsValue(SortType.class, "desc"));
        assertFalse(Util.checkEnumContainsValue(SortType.class, "besc"));
        assertFalse(Util.checkEnumContainsValue(SortType.class, null));
    }
}
