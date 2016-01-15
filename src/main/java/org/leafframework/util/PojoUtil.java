package org.leafframework.util;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class PojoUtil {

	public static Map<String, Object> getMapFromPojo(Object bean)
			throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())
					&& method.getParameterTypes().length == 0
					&& method.getReturnType() != void.class
					&& method.getName().matches("^(get|is).+")) {
				String name = method.getName().replaceAll("^(get|is)", "");
				name = Character.toLowerCase(name.charAt(0))
						+ (name.length() > 1 ? name.substring(1) : "");
				Object value = method.invoke(bean);
				properties.put(name, value);
			}
		}
		return properties;
	}
	public static Object getPojoFromMap(Class<?> cls,
			HashMap<String, Object> map, String prefix)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, ParseException {
		Object pojo = null;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null
					&& entry.getValue().toString().length() != 0) {
				try {
					String fieldName = entry.getKey();
					if (prefix != null && prefix.length() > 0) {
						if (fieldName.startsWith(prefix)) {
							fieldName = fieldName.replaceFirst(prefix, "");
						} else {
							continue;
						}
					}
					Field field = cls.getDeclaredField(fieldName);
					field.setAccessible(true);
					field.getType();

					if (pojo == null) {
						pojo = cls.newInstance();
					}
					if (field.getType() == Date.class) {
						if (entry.getValue() instanceof Date) {
							field.set(pojo, entry.getValue());
						}
						if (entry.getValue() instanceof String) {
							field.set(pojo,
									DateUtil.parse((String) entry.getValue()));
						}
					} else if (field.getType() == Long.class) {
						if (entry.getValue() instanceof Long) {
							field.set(pojo, (Long) entry.getValue());
						} else if (entry.getValue() instanceof Integer) {
							field.set(pojo, (Integer) entry.getValue());
						} else {
							field.set(pojo, Integer.getInteger((String) entry
									.getValue()));
						}
					} else if (field.getType() == Integer.class) {
						if (entry.getValue() instanceof Long) {
							field.set(pojo, (Long) entry.getValue());
						} else if (entry.getValue() instanceof Integer) {
							field.set(pojo, (Integer) entry.getValue());
						} else {
							field.set(pojo, Integer.parseInt((String) entry
									.getValue()));
						}
					} else if (field.getType() == BigDecimal.class) {
						field.set(pojo, new BigDecimal(entry.getValue().toString()));
					} else if (field.getType() == String.class) {
						if (entry.getValue() instanceof Long) {
							field.set(pojo,
									Long.toString((Long) entry.getValue()));
						} else if (entry.getValue() instanceof Integer) {
							field.set(pojo, Integer.toString((Integer) entry
									.getValue()));
						} else {
							field.set(pojo, (String) entry.getValue());
						}
					} else if(field.getType()== int.class){
						if(entry.getValue() instanceof Integer){
							field.set(pojo, (Integer) entry.getValue());
						}else{
							field.set(pojo, Integer.parseInt((String) entry.getValue()));
						}						
					}else {
						field.set(pojo, entry.getValue());
					}
				} catch (NoSuchFieldException e) {
					continue;
				}
			}
		}
		return pojo;
	}
	public static String JabxPojoToXml(Object obj) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders",
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		ByteArrayOutputStream osxml = new ByteArrayOutputStream();
		jaxbMarshaller.marshal(obj, osxml);
		return osxml.toString();
	}
	public static Object JabxXmlToPojo(Class clazz, String xmlText)
			throws JAXBException {
		JAXBContext jaxbContextResponse = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContextResponse
				.createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(new StringReader(xmlText));
	}

}
