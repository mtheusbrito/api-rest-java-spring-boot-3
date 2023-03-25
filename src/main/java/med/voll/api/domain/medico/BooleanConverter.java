package med.voll.api.domain.medico;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Character>{

	
	@Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        if (attribute != null) {
            if (attribute) {
                return 'T';
            } else {
                return 'F';
            }
                 
        }
        return null;
    }
 
    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        if (dbData != null) {
            return dbData.equals('T');
        }
        return null;
    }
}
