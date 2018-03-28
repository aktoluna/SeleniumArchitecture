package com.saha.slnarch.di;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.codejargon.feather.Feather;
import org.codejargon.feather.Provides;
import org.junit.Assert;
import org.junit.Test;

public class InjectionHelperTest {

  @Test
  public void testInject() {
      InjectionHelper.getInstance().setFeather(Feather.with(new MyModule()));
      B b=new B();
    Assert.assertNotNull(b.a);
    Assert.assertEquals(b.a.getName(),"A");
  }

  @Test
  public void testInjectWithInterface() {
    InjectionHelper.getInstance().setFeather(Feather.with(new MyModule()));
    C c=new C();
    Assert.assertNotNull(c.a);
    Assert.assertEquals(c.a.getName(),"A");
  }

  @Test
  public void testInjectWithInstance() {
    InjectionHelper.getInstance().setFeather(Feather.with(new MyModule()));
    D d=InjectionHelper.getInstance().getFeather().instance(D.class);
    Assert.assertNotNull(d.a);
    Assert.assertEquals(d.a.getName(),"A");
  }


  static class A {

    String getName() {
      return "A";
    }

  }

  static class B {

    @Inject
    public A a;

    public B() {
      InjectionHelper.getInstance().getFeather().injectFields(this);
    }

    String getName() {
      return "B";
    }

  }

  static class C implements Injectable {

    @Inject
    public A a;

    public C(){
      inject();
    }

    String getName() {
      return "C";
    }

  }

  static class D  {
    public A a;

    @Inject
    public D(A a){
      this.a=a;
    }

    String getName() {
      return "D";
    }

  }

  static class MyModule {

    @Provides
    @Singleton
    public A provideA() {
      return new A();
    }
  }
}