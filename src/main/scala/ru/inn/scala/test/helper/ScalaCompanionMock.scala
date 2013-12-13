package ru.inn.scala.test.helper

import org.powermock.api.easymock.PowerMock
import org.powermock.reflect.Whitebox

/** Created by bfattahov 2013 */
object ScalaCompanionMock {
  /** Create EasyMock's mock for Scala Companion object */
  def mock[T](clazz: Class[T]): T = {
    if (!clazz.getName.endsWith("$") || clazz.getField("MODULE$") == null) {
      throw new IllegalArgumentException("%s is not a scala companion object!".format(clazz.getName))
    }
    try {
      val mock: T = PowerMock.createNiceMock(clazz)
      Whitebox.setInternalState(clazz, "MODULE$", mock)
      mock
    }
    catch {
      case ex: Exception => throw new IllegalArgumentException("Some exception occured!\n Have you add " +
        "@PrepareForTest(fullyQualifiedNames=Array(\"%s\")) in the top of your test class?".format(clazz.getName), ex)
    }
  }

  def mock[T](obj: T): T = {
    mock(obj.getClass)
  }


  def mock[T](clazzName: String): T = {
    mock[T](Class.forName(clazzName).asInstanceOf[Class[T]])
  }
}
