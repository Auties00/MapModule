package it.auties.map;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.List;
import java.util.Map;

public class SimpleMapModule extends SimpleModule {
    public SimpleMapModule(){
        init();
    }

    public SimpleMapModule(String name) {
        super(name);
        init();
    }

    public SimpleMapModule(Version version) {
        super(version);
        init();
    }

    public SimpleMapModule(String name, Version version) {
        super(name, version);
        init();
    }

    public SimpleMapModule(String name, Version version, Map<Class<?>, JsonDeserializer<?>> deserializers) {
        super(name, version, deserializers);
        init();
    }

    public SimpleMapModule(String name, Version version, List<JsonSerializer<?>> serializers) {
        super(name, version, serializers);
        init();
    }

    public SimpleMapModule(String name, Version version, Map<Class<?>, JsonDeserializer<?>> deserializers, List<JsonSerializer<?>> serializers) {
        super(name, version, deserializers, serializers);
        init();
    }

    @Override
    public String getModuleName() {
        return "SimpleMapModule";
    }

    private void init() {
        addSerializer(new MapSerializer<>());
        setDeserializerModifier(new MapDeserializerModifier());
    }
}