package it.auties.map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MapDeserializer<K, V> extends StdDeserializer<Map<K, V>> {
    private final CollectionType type;

    public MapDeserializer(MapType type, TypeFactory factory) {
        super(Map.class);
        var argument = factory.constructParametricType(MapEntry.class,
                type.getKeyType(), type.getContentType());
        this.type = factory.constructCollectionType(List.class, argument);
    }

    @Override
    public Map<K, V> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return context.<List<MapEntry<K, V>>>readValue(parser, type)
                .stream()
                .collect(Collectors.toMap(MapEntry::key, MapEntry::value));
    }
}
