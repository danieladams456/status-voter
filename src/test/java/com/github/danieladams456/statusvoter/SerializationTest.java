package com.github.danieladams456.statusvoter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SerializationTest {
    @Test
    void testSerializeObject() throws IOException {
        TestObject data = new TestObject();
        data.setA("test1");
        data.setB("test2");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mapper.writeValue(outputStream, data);
        assertThat(outputStream.toString()).isEqualTo("""
                {"a":"test1","b":"test2","voter":"INITIAL"}""");
    }

    @Test
    void testSerializeRecord() throws IOException {
        TestRecord record = new TestRecord("test1", "test2", new StatusVoter());
        // test some variety in status
        record.voter.merge(StatusClassification.CUSTOMER_DATA_VALIDATION_ERROR);
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mapper.writeValue(outputStream, record);
        assertThat(outputStream.toString()).isEqualTo("""
                {"a":"test1","b":"test2","voter":"CUSTOMER_DATA_VALIDATION_ERROR"}""");
    }

    @Data
    private static class TestObject {
        private String a;
        private String b;
        private StatusVoter voter = new StatusVoter();
    }

    private record TestRecord(
            String a,
            String b,
            StatusVoter voter
    ) {
    }
}
