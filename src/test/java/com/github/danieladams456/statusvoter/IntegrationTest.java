package com.github.danieladams456.statusvoter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {
    @Test
    void testSerialize() throws IOException {
        TestResponseDto data = new TestResponseDto();
        data.setA("test1");
        data.setB("test2");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mapper.writeValue(outputStream, data);
        assertThat(outputStream.toString()).isEqualTo("""
                {"a":"test1","b":"test2","voter":"INITIAL"}""");
    }

    @Data
    private static class TestResponseDto {
        private String a;
        private String b;
        private StatusVoter voter = new StatusVoter();
    }
}
