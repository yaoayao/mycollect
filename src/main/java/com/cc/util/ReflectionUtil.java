package com.cc.util;

import com.cc.exception.ReflectionException;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public final class ReflectionUtil {
	
	private ReflectionUtil() { }
	
	public static <T> T newInstance(final Class<T> clazz) {
		T result;
		try {
			result = clazz.newInstance();
		} catch (final ReflectiveOperationException e) {
			throw new ReflectionException(e.getMessage());
		}
		return result;
	}
	
	public static Collection<Field> getAllNonStaticFields(final Class<?> clazz) {
		Collection<Field> result = new LinkedHashSet<Field>();
		result.addAll(getNonStaticFields(ClassUtils.getAllSuperclasses(clazz)));
		result.addAll(getNonStaticFields(clazz));
		return result;
	}
	
	private static Collection<Field> getNonStaticFields(final Collection<Class<?>> classes) {
		Collection<Field> result = new ArrayList<Field>();
		for (Class<?> each : classes) {
			result.addAll(getNonStaticFields(each));
		}
		return result;
	}
	
	private static Collection<Field> getNonStaticFields(final Class<?> clazz) {
		Collection<Field> result = new ArrayList<Field>();
		for (Field each : clazz.getDeclaredFields()) {
			if (!Modifier.isStatic(each.getModifiers())) {
				result.add(each);
			}
		}
		return result;
	}
	
	public static String[] getNullPropertyNames(final Object source) {
		Set<String> result = new HashSet<String>();
		for (Field each : getAllNonStaticFields(source.getClass())) {
			each.setAccessible(true);
			Object value = null;
			try {
				value = each.get(source);
			} catch (final IllegalArgumentException e) {
				throw new ReflectionException(e.getMessage());
			}catch (IllegalAccessException e){
				throw new ReflectionException(e.getMessage());
			}
			if (null == value) {
				result.add(each.getName());
			}
		}
		return result.toArray(new String[result.size()]);
	}
}
