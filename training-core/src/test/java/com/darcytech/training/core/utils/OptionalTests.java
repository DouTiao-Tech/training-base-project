package com.darcytech.training.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class OptionalTests {

    @Test
    public void testOptionalNull() throws Exception {
        Optional<String> name = Optional.of("name");
        Optional<String> empty = Optional.<String>empty();
        List<Optional<String>> optionalList = Arrays.asList(empty, name);
        optionalList.stream().filter(Optional::isPresent);
    }

}
