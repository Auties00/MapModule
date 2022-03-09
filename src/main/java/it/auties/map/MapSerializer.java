package it.auties.map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

public class MapSerializer<K, V> extends StdSerializer<Map<K, V>> {
    public MapSerializer() {
        super(Map.class, true);
    }

    @Override
    public void serialize(Map<K, V> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        var values = value.entrySet()
                .stream()
                .map(entry -> new MapEntry<>(entry.getKey(), entry.getValue()))
                .toList();
        provider.defaultSerializeValue(values, gen);
    }
}
