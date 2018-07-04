package com.fuyi.data.redis.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ObjectRedisSerializer implements RedisSerializer<Object> {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();

	private static final byte[] EMPTY_ARRAY = new byte[0];
	
	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		if (obj == null) {
			return EMPTY_ARRAY;
		}
		try {
			return serializer.convert(obj);
		} catch (Exception e) {
			throw new SerializationException("cannot serialize", e);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null || bytes.length == 0) {
			return null;
		}
		try {
			return deserializer.convert(bytes);
		} catch (Exception e) {
			throw new SerializationException("cannot deserialize", e);
		}
	}
}
