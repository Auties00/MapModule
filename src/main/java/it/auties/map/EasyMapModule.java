package it.auties.map;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class EasyMapModule extends SimpleModule {
    @Override
    public String getModuleName() {
        return "EasyMap";
    }

    @Override
    public void setupModule(SetupContext context) {
        addSerializer(new MapSerializer<>());
        setDeserializerModifier(new MapDeserializerModifier());
    }
}