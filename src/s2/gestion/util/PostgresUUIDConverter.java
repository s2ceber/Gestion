package s2.gestion.util;

import java.util.UUID;

import javax.persistence.AttributeConverter;
@javax.persistence.Converter(autoApply=true)
public class PostgresUUIDConverter implements AttributeConverter<UUID, Object> {

    @Override
    public UUID convertToDatabaseColumn(UUID attribute) {
	return attribute;
    }

    @Override
    public UUID convertToEntityAttribute(Object dbData) {
	return (UUID) dbData;
    }

}
