package med.voll.api.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import jakarta.persistence.Transient;
import med.voll.api.domain.Entidade;

public class GsonUtils {
	
	
	public static GsonBuilder novoGsonBuilder() {
		GsonBuilder builder = new GsonBuilder();
		return builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	}
	
	
	 
	
	public Gson paraLog(final Object object) {
		GsonBuilder builder = novoGsonBuilder();
		
		Gson gson = builder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
		.registerTypeAdapterFactory(new TypeAdapterFactory() {
			public TypeAdapter<? extends Entidade> create(Gson gson, TypeToken type) {
		        if (!Entidade.class.isAssignableFrom(type.getRawType())) {
		            return null;
		        }

		        final TypeAdapter<Entidade> delegate = gson.getDelegateAdapter(this, type);

		        return new TypeAdapter<Entidade>() {

		        	Collection<Entidade> objetosAnalizadas = new HashSet<>();

		            @Override
		            public void write(JsonWriter out, Entidade value) throws IOException {
		            	if(value != null) {
		            		Boolean permiteSerializar = object.getClass().equals(value.getClass());

			            	if(permiteSerializar) {
			            		objetosAnalizadas.add(value);
			            		delegate.write(out, value);
			            	}else {
			            		out.beginObject();
			            		out.name("id").value(value.getId());
			            		out.endObject();
		            		}
		            	}else {
		            		delegate.write(out, null);
		            	}
		            }

		            @Override
		            public Entidade read(JsonReader in) throws IOException {return delegate.read(in);}
		        };
		    }
		}).setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes arg0) {
				if (arg0.getAnnotation(Transient.class) != null) {
					return true;
				}
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> arg0) {
				return false;

			}
		}).serializeNulls().create();
		return gson;
	}
}


