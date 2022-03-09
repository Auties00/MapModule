package it.auties.map;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import com.fasterxml.jackson.databind.type.MapType;

public class MapDeserializerModifier extends BeanDeserializerModifier {
    @Override
    public JsonDeserializer<?> modifyMapDeserializer(DeserializationConfig config, MapType type, BeanDescription description, JsonDeserializer<?> deserializer) {
        var keyDeserializer = StdKeyDeserializer.forType(type.getKeyType().getRawClass());
        var valueDeserializer = StdKeyDeserializer.forType(type.getContentType().getRawClass());
        return keyDeserializer != null && valueDeserializer != null ? deserializer
                : new MapDeserializer<>(type, config.getTypeFactory());
    }
}
