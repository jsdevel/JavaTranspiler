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
import me.joespencer.guides.AnnotationGuide;
import me.joespencer.guides.ClassGuide;
import me.joespencer.guides.EnumGuide;
import me.joespencer.guides.GuideFactory;
import me.joespencer.guides.InterfaceGuide;
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
   GuideFactory mockGuideFactory;
   Class[] classes;

   @Before
   public void before(){
      classes=new Class[]{
         File.class,
         Serializable.class,
         TimeUnit.class,
         Override.class
      };
      mockGuideFactory=mock(GuideFactory.class);
      when(mockGuideFactory.createAnnotationGuide(any(Class.class)))
         .thenReturn(mock(AnnotationGuide.class));
      when(mockGuideFactory.createClassGuide(any(Class.class)))
         .thenReturn(mock(ClassGuide.class));
      when(mockGuideFactory.createEnumGuide(any(Class.class)))
         .thenReturn(mock(EnumGuide.class));
      when(mockGuideFactory.createInterfaceGuide(any(Class.class)))
         .thenReturn(mock(InterfaceGuide.class));
      transpiler=new JavaTranspiler(classes, mockGuideFactory);
      mockVisitor=mock(JavaTranspilerVisitor.class);
   }

   @Test(expected = IllegalArgumentException.class)
   public void constructor_should_not_accept_null_classes_array() {
      new JavaTranspiler(null, mockGuideFactory);
   }

   @Test(expected = IllegalArgumentException.class)
   public void constructor_should_not_accept_an_empty_array_of_classes() {
      new JavaTranspiler(new Class[]{}, mockGuideFactory);
   }

   @Test(expected = IllegalArgumentException.class)
   public void constructor_should_not_acctp_a_null_guide_factory(){
      new JavaTranspiler(classes, null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void transpiler_should_not_attempt_to_serve_null(){
      transpiler.serve(null);
   }

   @Test
   public void transpiler_should_be_able_to_serve_visitors(){
      transpiler.serve(mockVisitor);
      verify(mockVisitor).visitAnnotationGuide(any(AnnotationGuide.class));
      verify(mockVisitor).visitClassGuide(any(ClassGuide.class));
      verify(mockVisitor).visitEnumGuide(any(EnumGuide.class));
      verify(mockVisitor).visitInterfaceGuide(any(InterfaceGuide.class));
   }
}