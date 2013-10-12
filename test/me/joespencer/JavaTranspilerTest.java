/*
 * Copyright 2013 Joseph Spencer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.joespencer;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import me.joespencer.visitors.JavaTranspilerVisitor;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Joseph Spencer
 */
public class JavaTranspilerTest {
   JavaTranspiler transpiler;
   JavaTranspilerVisitor mockVisitor;
   Class[] classes;

   @Before
   public void before(){
      classes=new Class[]{
         File.class,
         Serializable.class,
         TimeUnit.class,
         Override.class
      };
      transpiler=new JavaTranspiler(classes);
      mockVisitor=mock(JavaTranspilerVisitor.class);
   }

   @Test(expected = NullPointerException.class)
   public void constructor_should_not_accept_a_null_array() {
      new JavaTranspiler(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void constructor_should_not_accept_an_empty_array_of_classes() {
      new JavaTranspiler(new Class[]{});
   }

   @Test(expected = NullPointerException.class)
   public void transpiler_should_not_attempt_to_serve_null(){
      transpiler.serve(null);
   }

   @Test
   public void transpiler_should_be_able_to_serve_visitors(){
      transpiler.serve(mockVisitor);
      verify(mockVisitor).visitAnnotation(any(Class.class));
      verify(mockVisitor).visitClass(any(Class.class));
      verify(mockVisitor).visitEnum(any(Class.class));
      verify(mockVisitor).visitInterface(any(Class.class));
   }
}