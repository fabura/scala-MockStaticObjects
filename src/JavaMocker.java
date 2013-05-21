package ru.inn.rbac;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import ru.inn.rbac.dao.RbacDaoCache;
import ru.inn.rbac.dao.RbacDaoCache$;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by bulat.fattahov 2013
 */
//@PrepareForTest(RbacDaoCache$.class)
public class JavaMocker
{

//	static void setScalaObjectMock(Class clazz, Object newValue) throws Exception {
//		Field field = clazz.getField("MODULE$");
//		field.setAccessible(true);
//
//		// remove final modifier from field
//		Field modifiersField = Field.class.getDeclaredField("modifiers");
//		modifiersField.setAccessible(true);
//		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//
//		field.set(null, newValue);
//	}
//
//	public void setMock(final RbacDaoCache cacheMock) throws Exception
//	{
//		PowerMockito.mockStatic(RbacDaoCache$.class);
//		PowerMockito.when(RbacDaoCache$.MODULE$.apply()).thenReturn(cacheMock);
//	}

	static void setFinalStatic(Class clazz, Object newValue) throws Exception {
//		Class.forName("asdf").newInstance().

		Class scalaClazz = Class.forName(clazz.getName()+ "$");
		Field field = scalaClazz.getField("MODULE$");
		field.setAccessible(true);

		Field modifiersField = Method.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);
	}
}
