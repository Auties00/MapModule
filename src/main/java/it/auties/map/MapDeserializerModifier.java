package it.auties.map;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import com.fasterxml.jackson.databind.type.MapType;

class MapDeserializerModifier extends BeanDeserializerModifier {
    @Override
    public JsonDeserializer<?> modifyMapDeserializer(DeserializationConfig config, MapType type, BeanDescription description, JsonDeserializer<?> deserializer) {
        var keyDeserializer = StdKeyDeserializer.forType(type.getKeyType().getRawClass());
        return keyDeserializer == null ? new MapDeserializer<>(type, config.getTypeFactory())
                : deserializer;
    }
}
