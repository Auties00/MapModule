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
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

class MapDeserializer<K, V> extends StdDeserializer<Map<K, V>> {
    private final CollectionType type;
    private final MapType mapType;

    public MapDeserializer(MapType type, TypeFactory factory) {
        super(Map.class);
        this.mapType = type;
        var argument = factory.constructParametricType(MapEntry.class,
                type.getKeyType(), type.getContentType());
        this.type = factory.constructCollectionType(List.class, argument);
    }

    @Override
    public Map<K, V> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return context.<List<MapEntry<K, V>>>readValue(parser, type)
                .stream()
                .collect(Collectors.toMap(MapEntry::key, MapEntry::value,
                        this::handleDuplicates, this::initMap));
    }

    @SuppressWarnings("unchecked")
    private Map<K, V> initMap() {
        try {
            return (Map<K, V>) mapType.getRawClass()
                    .getConstructor()
                    .newInstance();
        }catch (ReflectiveOperationException exception){
            throw new NoSuchElementException("Cannot initialize map with type %s"
                    .formatted(mapType.getRawClass().getName()), exception);
        }
    }

    private V handleDuplicates(V first, V second) {
        return second;
    }
}
