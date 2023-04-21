package com.medium.shazinsadakath.pii.data.protection.demo.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProtectDataSerializer extends StdSerializer<Object> implements ContextualSerializer {

    private String[] allowedRoles;

    public ProtectDataSerializer(String[] allowedRoles) {
        this();
        this.allowedRoles = allowedRoles;
    }

    public ProtectDataSerializer() {
        super(Object.class);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Optional<ProtectData> annotation = Optional.ofNullable(property).map(prop -> prop.getAnnotation(ProtectData.class));
        return new ProtectDataSerializer(annotation.map(ProtectData::allowedRoles).orElse(new String[] {"ADMIN"}));

    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String piiData = value.toString();
        if (authentication != null) {
            final List<String> allowedRolesList = Arrays.asList(this.allowedRoles);
            long count = authentication.getAuthorities().stream().filter(ga -> allowedRolesList.contains(ga.getAuthority().substring(5))).count();
            if (count == 0) {
                piiData = piiData.replaceAll("\\w(?=\\w{4})", "x");
            }
        }
        gen.writeString(piiData);
    }
}
